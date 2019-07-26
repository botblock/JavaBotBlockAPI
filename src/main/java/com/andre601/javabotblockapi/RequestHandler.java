/*
 * Copyright 2019 Andre601
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package com.andre601.javabotblockapi;

import com.andre601.javabotblockapi.exceptions.RatelimitedException;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.JDA;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class to handle post-requests to the <a href="https://botblock.org" target="_blank">BotBlock API</a>.
 *
 * <p>The class can currently do the following things:
 * <ul>
 *     <li>Posting Guild counts ({@link #postGuilds(ShardManager, BotBlockAPI) manually} and {@link #startAutoPosting(ShardManager, BotBlockAPI) automatically}).</li>
 *     <li>{@link #getBotlists() Getting botlists}.</li>
 *     <li>{@link #getBotlist(String) Getting a single Botlist}.</li>
 *     <li>{@link #getBotInfos(ShardManager) Getting lists a bot is on}.</li>
 *     <li>{@link #getBotInfo(ShardManager, String) Getting a single list a bot is on}.</li>
 *     <li>{@link #getOwners(ShardManager) Getting the owners of a bot.}</li>
 * </ul>
 */
public class RequestHandler {
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final OkHttpClient CLIENT = new OkHttpClient();

    private final String BASE_URL = "https://botblock.org/api/";

    private String id = null;

    private JSONObject json = new JSONObject();

    /**
     * Empty constructor to get the class.
     */
    public RequestHandler(){}

    /**
     * Posts guilds from the provided {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager}.
     *
     * @param  shardManager
     *         The {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance} that should be used.
     * @param  botBlockAPI
     *         The {@link com.andre601.javabotblockapi.BotBlockAPI BotBlockAPI instance} that should be used.
     *
     * @throws IOException
     *         When the post request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the Bot (IP or ID) got ratelimited.
     * @throws NullPointerException
     *         When the ShardManager gives an invalid shard (Shard id 0 is null).
     */
    public void postGuilds(@NotNull ShardManager shardManager, @NotNull BotBlockAPI botBlockAPI) throws IOException, RatelimitedException{
        this.id = Objects.requireNonNull(shardManager.getShardById(0), "Received invalid shard.")
                .getSelfUser().getId();

        json.put("server_count", shardManager.getGuilds().size())
                .put("bot_id", id)
                .put("shard_count", shardManager.getShards().size());

        List<Integer> shards = new ArrayList<>();
        for(JDA jda : shardManager.getShards())
            shards.add(jda.getGuilds().size());

        json.put("shards", new JSONArray(Arrays.deepToString(shards.toArray())));

        botBlockAPI.getAuthTokens().forEach(json::put);

        postRequest();
    }

    /**
     * Posts the guilds from the provided {@link net.dv8tion.jda.api.JDA JDA}.
     * <br>If the bot is part of sharding and the shard count is bigger than 1, then {@code shard_id} and
     * {@code shard_count} are added too. Those values are not supported by all sites!
     *
     * <p>If you use this on a sharded bot, better use {@link #postGuilds(ShardManager, BotBlockAPI)}.
     *
     * @param  jda
     *         The {@link net.dv8tion.jda.api.JDA JDA instance} that should be used.
     * @param  botBlockAPI
     *         The {@link com.andre601.javabotblockapi.BotBlockAPI BotBlockAPI instance} that should be used.
     *
     * @throws IOException
     *         When the post request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the Bot (IP or ID) got ratelimited.
     */
    public void postGuilds(@NotNull JDA jda, @NotNull BotBlockAPI botBlockAPI) throws IOException, RatelimitedException{
        this.id = jda.getSelfUser().getId();

        json.put("server_count", jda.getGuildCache().size())
                .put("bot_id", id);

        if(jda.getShardInfo().getShardTotal() > 1)
            json.put("shard_id", jda.getShardInfo().getShardId())
                    .put("shard_count", jda.getShardInfo().getShardTotal());

        botBlockAPI.getAuthTokens().forEach(json::put);

        postRequest();
    }

    /**
     * Posts the provided guilds from the provided Bot id.
     *
     * @param  botId
     *         The ID (as long) of the bot.
     * @param  guilds
     *         The guilds the bot is in.
     * @param  botBlockAPI
     *         The {@link com.andre601.javabotblockapi.BotBlockAPI BotBlockAPI instance} that should be used.
     *
     * @throws IOException
     *         When the post request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the Bot (IP or ID) got ratelimited.
     */
    public void postGuilds(Long botId, int guilds, @NotNull BotBlockAPI botBlockAPI) throws IOException, RatelimitedException{
        postGuilds(Long.toString(botId), guilds, botBlockAPI);
    }

    /**
     * Posts the provided guilds from the provided Bot id.
     *
     * @param  botId
     *         The ID (as String) of the bot.
     * @param  guilds
     *         The guilds the bot is in.
     * @param  botBlockAPI
     *         The {@link com.andre601.javabotblockapi.BotBlockAPI BotBlockAPI instance} that should be used.
     *
     * @throws IOException
     *         When the post request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the Bot (IP or ID) got ratelimited.
     */
    public void postGuilds(@NotNull String botId, int guilds, @NotNull BotBlockAPI botBlockAPI) throws IOException, RatelimitedException{
        Check.notEmpty(botId, "ID may not be empty.");

        this.id = botId;

        json.put("server_count", guilds)
                .put("bot_id", botId);

        botBlockAPI.getAuthTokens().forEach(json::put);

        postRequest();
    }

    /**
     * Starts a scheduler that posts the guilds from the provided {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager}
     * every X minutes.
     *
     * @param shardManager
     *         The {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance} that should be used.
     * @param  botBlockAPI
     *         The {@link com.andre601.javabotblockapi.BotBlockAPI BotBlockAPI instance} that should be used.
     */
    public void startAutoPosting(@NotNull ShardManager shardManager, @NotNull BotBlockAPI botBlockAPI){
        scheduler.scheduleAtFixedRate(() -> {
            try{
                postGuilds(shardManager, botBlockAPI);
            }catch(IOException | RatelimitedException ex){
                ex.printStackTrace();
            }
        }, botBlockAPI.getUpdateInterval(), botBlockAPI.getUpdateInterval(), TimeUnit.MINUTES);
    }

    /**
     * Starts a scheduler that posts the guilds from the provided {@link net.dv8tion.jda.api.JDA JDA} every X minutes.
     *
     * @param jda
     *         The {@link net.dv8tion.jda.api.JDA JDA instance} that should be used.
     * @param  botBlockAPI
     *         The {@link com.andre601.javabotblockapi.BotBlockAPI BotBlockAPI instance} that should be used.
     */
    public void startAutoPosting(@NotNull JDA jda, @NotNull BotBlockAPI botBlockAPI){
        scheduler.scheduleAtFixedRate(() -> {
            try{
                postGuilds(jda, botBlockAPI);
            }catch(IOException | RatelimitedException ex){
                ex.printStackTrace();
            }
        }, botBlockAPI.getUpdateInterval(), botBlockAPI.getUpdateInterval(), TimeUnit.MINUTES);
    }

    /**
     * Starts a scheduler that posts the provided guilds of the provided bot id every X minutes.
     *
     * @param  botId
     *         The ID (as Long) of the bot.
     * @param  guilds
     *         The guilds the bot is in.
     * @param  botBlockAPI
     *         The {@link com.andre601.javabotblockapi.BotBlockAPI BotBlockAPI instance} that should be used.
     */
    public void startAutoPosting(Long botId, int guilds, @NotNull BotBlockAPI botBlockAPI){
        scheduler.scheduleAtFixedRate(() -> {
            try{
                postGuilds(botId, guilds, botBlockAPI);
            }catch(IOException | RatelimitedException ex){
                ex.printStackTrace();
            }
        }, botBlockAPI.getUpdateInterval(), botBlockAPI.getUpdateInterval(), TimeUnit.MINUTES);
    }

    /**
     * Starts a scheduler that posts the provided guilds of the provided bot id every X minutes.
     *
     * @param  botId
     *         The ID (as String) of the bot.
     * @param  guilds
     *         The guilds the bot is in.
     * @param  botBlockAPI
     *         The {@link com.andre601.javabotblockapi.BotBlockAPI BotBlockAPI instance} that should be used.
     */
    public void startAutoPosting(@NotNull String botId, int guilds, @NotNull BotBlockAPI botBlockAPI){
        scheduler.scheduleAtFixedRate(() -> {
            try{
                postGuilds(botId, guilds, botBlockAPI);
            }catch(IOException | RatelimitedException ex){
                ex.printStackTrace();
            }
        }, botBlockAPI.getUpdateInterval(), botBlockAPI.getUpdateInterval(), TimeUnit.MINUTES);
    }

    /**
     * Stops the auto-posting by shutting down the scheduler.
     */
    public void stopAutoPosting(){
        scheduler.shutdown();
    }

    /**
     * Gets the owners of a bot as a list.
     *
     * @param  shardManager
     *         The {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance} that should be used.
     *
     * @return The owners as a list.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public List<String> getOwners(@NotNull ShardManager shardManager) throws IOException, RatelimitedException{
        return getOwners(Objects.requireNonNull(shardManager.getShardById(0), "Received invalid Shard")
                .getSelfUser().getId());
    }


    /**
     * Gets the owners of a bot as a list.
     *
     * @param  jda
     *         The {@link net.dv8tion.jda.api.JDA JDA instance} that should be used.
     *
     * @return The owners as a list.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public List<String> getOwners(@NotNull JDA jda) throws IOException, RatelimitedException{
        return getOwners(jda.getSelfUser().getId());
    }

    /**
     * Gets the owners of a bot as a list.
     *
     * @param  id
     *         The id of the bot.
     *
     * @return The owners as a list.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public List<String> getOwners(Long id) throws IOException, RatelimitedException{
        return getOwners(Long.toString(id));
    }

    /**
     * Gets the owners of a bot as a List.
     *
     * @param  id
     *         The ID of the bot to get information from.
     *
     * @return The owners as a list.
     *
     * @throws IOException
     *         When the post request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the Bot (IP or ID) got ratelimited.
     *
     * @since v2.0.0
     */
    public List<String> getOwners(@NotNull String id) throws IOException, RatelimitedException{
        JSONObject json = getAll(id);

        JSONArray array = json.getJSONArray("owners");

        List<String> owners = new ArrayList<>();
        for(int i = 0; i < array.length(); i++)
            owners.add(array.getString(i));

        return owners;
    }

    /**
     * Gets all the available Botlists as JSONObject.
     * <br>The data of each Botlist depends on the site.
     *
     * <p>The JSONObject will look something like this:
     * <br><pre><code>
     * {
     *   "somebotlist.com": [
     *    {@literal <botlist data>},
     *     200
     *   ],
     *   "otherlist.org": [
     *    {@literal <botlist data>},
     *     404
     *   ]
     * }
     * </code></pre>
     *
     * @param  shardManager
     *         The {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance} that should be used.
     *
     * @return The Botlists as JSONObject.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public JSONObject getBotInfos(@NotNull ShardManager shardManager) throws IOException, RatelimitedException{
        return getBotInfos(Objects.requireNonNull(shardManager.getShardById(0), "Received invalid shard.")
                .getSelfUser().getId());
    }

    /**
     * Gets all the available Botlists as JSONObject.
     * <br>The data of each Botlist depends on the site.
     *
     * <p>The JSONObject will look something like this:
     * <br><pre><code>
     * {
     *   "somebotlist.com": [
     *    {@literal <botlist data>},
     *     200
     *   ],
     *   "otherlist.org": [
     *    {@literal <botlist data>},
     *     404
     *   ]
     * }
     * </code></pre>
     *
     * @param  jda
     *         The {@link net.dv8tion.jda.api.JDA jda instance} that should be used.
     *
     * @return The Botlists as JSONObject.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public JSONObject getBotInfos(@NotNull JDA jda) throws IOException, RatelimitedException{
        return getBotInfos(jda.getSelfUser().getId());
    }

    /**
     * Gets all the available Botlists as JSONObject.
     * <br>The data of each Botlist depends on the site.
     *
     * <p>The JSONObject will look something like this:
     * <br><pre><code>
     * {
     *   "somebotlist.com": [
     *    {@literal <botlist data>},
     *     200
     *   ],
     *   "otherlist.org": [
     *    {@literal <botlist data>},
     *     404
     *   ]
     * }
     * </code></pre>
     *
     * @param  id
     *         The id of the bot.
     *
     * @return The Botlists as JSONObject.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public JSONObject getBotInfos(Long id) throws IOException, RatelimitedException{
        return getBotInfos(Long.toString(id));
    }

    /**
     * Gets all the available Botlists as JSONObject.
     * <br>The data of each Botlist depends on the site.
     *
     * <p>The JSONObject will look something like this:
     * <br><pre><code>
     * {
     *   "somebotlist.com": [
     *    {@literal <botlist data>},
     *     200
     *   ],
     *   "otherlist.org": [
     *    {@literal <botlist data>},
     *     404
     *   ]
     * }
     * </code></pre>
     *
     * @param  id
     *         The id of the bot
     *
     * @return The Botlists as JSONObject.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public JSONObject getBotInfos(@NotNull String id) throws IOException, RatelimitedException{
        return getAll(id).getJSONObject("list_data");
    }

    /**
     * Gets the specific information from a single Botlist.
     * <br>The returned data depends on the Botlist.
     *
     * <p>The JSONObject will look something like this:
     * <br><pre><code>
     * {[
     *  {@literal <botlist data>},
     *   200
     * ]}
     * </code></pre>
     *
     * @param  shardManager
     *         The {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance} that should be used.
     * @param  site
     *         The sites name to get information from.
     *         <br>A list of supported sites can be found <a href="https://botblock.org/api/docs#count" target="_blank">here</a>.
     *
     * @return The sites information as JSONArray.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public JSONArray getBotInfo(@NotNull ShardManager shardManager, @NotNull String site) throws IOException, RatelimitedException{
        return getBotInfo(Objects.requireNonNull(shardManager.getShardById(0), "Received invalid shard.")
                .getSelfUser().getId(), site);
    }

    /**
     * Gets the specific information from a single Botlist.
     * <br>The returned data depends on the Botlist.
     *
     * <p>The JSONObject will look something like this:
     * <br><pre><code>
     * {[
     *  {@literal <botlist data>},
     *   200
     * ]}
     * </code></pre>
     *
     * @param  id
     *         The id of the bot.
     * @param  site
     *         The sites name to get information from.
     *         <br>A list of supported sites can be found <a href="https://botblock.org/api/docs#count" target="_blank">here</a>.
     *
     * @return The sites information as JSONArray.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public JSONArray getBotInfo(Long id, @NotNull String site) throws IOException, RatelimitedException{
        return getBotInfo(Long.toString(id), site);
    }

    /**
     * Gets the specific information from a single Botlist.
     * <br>The returned data depends on the Botlist.
     *
     * <p>The JSONObject will look something like this:
     * <br><pre><code>
     * {[
     *  {@literal <botlist data>},
     *   200
     * ]}
     * </code></pre>
     *
     * @param  jda
     *         The {@link net.dv8tion.jda.api.JDA JDA instance} that should be used.
     * @param  site
     *         The sites name to get information from.
     *         <br>A list of supported sites can be found <a href="https://botblock.org/api/docs#count" target="_blank">here</a>.
     *
     * @return The sites information as JSONArray.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public JSONArray getBotInfo(@NotNull JDA jda, String site) throws IOException, RatelimitedException{
        return getBotInfo(jda.getSelfUser().getId(), site);
    }

    /**
     * Gets the specific information from a single Botlist.
     * <br>The returned data depends on the Botlist.
     *
     * <p>The JSONObject will look something like this:
     * <br><pre><code>
     * {[
     *  {@literal <botlist data>},
     *   200
     * ]}
     * </code></pre>
     *
     * @param  id
     *         The id of the bot.
     * @param  site
     *         The sites name to get information from.
     *         <br>A list of supported sites can be found <a href="https://botblock.org/api/docs#count" target="_blank">here</a>.
     *
     * @return The sites information as JSONArray.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public JSONArray getBotInfo(@NotNull String id, @NotNull String site) throws IOException, RatelimitedException{
        return getAll(id).getJSONObject("list_data").getJSONArray(site);
    }

    /**
     * Gets information from BotBlock about the provided Bot.
     * <br>The information can contain:
     * <ul>
     *     <li>Bot id</li>
     *     <li>Username</li>
     *     <li>Discriminator</li>
     *     <li>Botowners*</li>
     *     <li>Server count*</li>
     *     <li>OAuth invite*</li>
     *     <li>Data of the botlists**</li>
     * </ul>
     * *Based on most appearances on the botlists.
     * <br>**The provided data depends on the Botlist and can be different.
     *
     * <p>A response could look like this:
     * <br><pre><code>
     * {
     *     "id": "123456789012345678",
     *     "usernam": "MyBot",
     *     "discriminator": "1234",
     *     "owners": [
     *         "234567890123456789"
     *     ],
     *     "server_count": 100,
     *     "invite":{@literal "https://discordapp.com/oauth2/authorize?client_id=123456789012345678&scope=bot"},
     *     "list_data": {
     *         "somebotlist.com": [
     *            {@literal <list data>},
     *             200
     *         ],
     *         "otherlist.org": [
     *            {@literal <list data>},
     *             404
     *         ]
     *     }
     * }
     * </code></pre>
     *
     * @param  shardManager
     *         The instance of {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager} to use.
     *
     * @return The Bot information as JSONObject.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public JSONObject getAll(@NotNull ShardManager shardManager) throws IOException, RatelimitedException{
        return getAll(Objects.requireNonNull(shardManager.getShardById(0), "Received invalid shard.")
                .getSelfUser().getId());
    }

    /**
     * Gets information from BotBlock about the provided Bot.
     * <br>The information can contain:
     * <ul>
     *     <li>Bot id</li>
     *     <li>Username</li>
     *     <li>Discriminator</li>
     *     <li>Botowners*</li>
     *     <li>Server count*</li>
     *     <li>OAuth invite*</li>
     *     <li>Data of the botlists**</li>
     * </ul>
     * *Based on most appearances on the botlists.
     * <br>**The provided data depends on the Botlist and can be different.
     *
     * <p>A response could look like this:
     * <br><pre><code>
     * {
     *     "id": "123456789012345678",
     *     "usernam": "MyBot",
     *     "discriminator": "1234",
     *     "owners": [
     *         "234567890123456789"
     *     ],
     *     "server_count": 100,
     *     "invite":{@literal "https://discordapp.com/oauth2/authorize?client_id=123456789012345678&scope=bot"},
     *     "list_data": {
     *         "somebotlist.com": [
     *            {@literal <list data>},
     *             200
     *         ],
     *         "otherlist.org": [
     *            {@literal <list data>},
     *             404
     *         ]
     *     }
     * }
     * </code></pre>
     *
     * @param  jda
     *         The instance of {@link net.dv8tion.jda.api.JDA JDA} to use.
     *
     * @return The Bot information as JSONObject.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public JSONObject getAll(@NotNull JDA jda) throws IOException, RatelimitedException{
        return getAll(jda.getSelfUser().getId());
    }

    /**
     * Gets information from BotBlock about the provided Bot.
     * <br>The information can contain:
     * <ul>
     *     <li>Bot id</li>
     *     <li>Username</li>
     *     <li>Discriminator</li>
     *     <li>Botowners*</li>
     *     <li>Server count*</li>
     *     <li>OAuth invite*</li>
     *     <li>Data of the botlists**</li>
     * </ul>
     * *Based on most appearances on the botlists.
     * <br>**The provided data depends on the Botlist and can be different.
     *
     * <p>A response could look like this:
     * <br><pre><code>
     * {
     *     "id": "123456789012345678",
     *     "usernam": "MyBot",
     *     "discriminator": "1234",
     *     "owners": [
     *         "234567890123456789"
     *     ],
     *     "server_count": 100,
     *     "invite":{@literal "https://discordapp.com/oauth2/authorize?client_id=123456789012345678&scope=bot"},
     *     "list_data": {
     *         "somebotlist.com": [
     *            {@literal <list data>},
     *             200
     *         ],
     *         "otherlist.org": [
     *            {@literal <list data>},
     *             404
     *         ]
     *     }
     * }
     * </code></pre>
     *
     * @param  id
     *         The id of the bot.
     *
     * @return The Bot information as JSONObject.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public JSONObject getAll(Long id) throws IOException, RatelimitedException{
        return getAll(Long.toString(id));
    }

    /**
     * Gets information from BotBlock about the provided Bot.
     * <br>The information can contain:
     * <ul>
     *     <li>Bot id</li>
     *     <li>Username</li>
     *     <li>Discriminator</li>
     *     <li>Botowners*</li>
     *     <li>Server count*</li>
     *     <li>OAuth invite*</li>
     *     <li>Data of the botlists**</li>
     * </ul>
     * *Based on most appearances on the botlists.
     * <br>**The provided data depends on the Botlist and can be different.
     *
     * <p>A response could look like this:
     * <br><pre><code>
     * {
     *     "id": "123456789012345678",
     *     "usernam": "MyBot",
     *     "discriminator": "1234",
     *     "owners": [
     *         "234567890123456789"
     *     ],
     *     "server_count": 100,
     *     "invite":{@literal "https://discordapp.com/oauth2/authorize?client_id=123456789012345678&scope=bot"},
     *     "list_data": {
     *         "somebotlist.com": [
     *            {@literal <list data>},
     *             200
     *         ],
     *         "otherlist.org": [
     *            {@literal <list data>},
     *             404
     *         ]
     *     }
     * }
     * </code></pre>
     *
     * @param  id
     *         The id of the bot.
     *
     * @return The Bot information as JSONObject.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public JSONObject getAll(@NotNull String id) throws IOException, RatelimitedException{
        String url = BASE_URL + "bots/" + id;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", id)
                .build();

        try(Response response = CLIENT.newCall(request).execute()){
            Check.notNull(response.body(), "Received empty response body from BotBlcok API.");
            ResponseBody responseBody = response.body();

            Check.notEmpty(responseBody.string(), "Received empty response body from BotBlock API.");

            if(!response.isSuccessful()){
                if(response.code() == 429)
                    throw new RatelimitedException(responseBody.string());

                throw new IOException(String.format(
                        "Couldn't get Bot information. Site responded with error code %d (%s)",
                        response.code(),
                        response.message()
                ));
            }

            return new JSONObject(responseBody.string());
        }
    }

    /**
     * Returns the provided botlist info that is saved in BotBlock.
     *
     * <p>A response could look like this:
     * <br><pre><code>
     * {
     *     "api_docs": "https://somebotlist.com/docs",
     *     "api_post": "https://somebotlist.com/api/v1/bots/:id/post",
     *     "api_field": "server_count",
     *     "api_shard_id": "shard_id",
     *     "api_shard_count": "shard_count",
     *     "api_shards": "shards",
     *     "api_get": "https://somebotlist.com/api/v1/bots/:id"
     * }
     * </code></pre>
     *
     * @param  name
     *         The name of the botlist.
     *
     * @return The botlist as JSONObject.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public JSONObject getBotlist(@NotNull String name) throws IOException, RatelimitedException{
        return getBotlists().getJSONObject(name);
    }

    /**
     * Returns the current botlists that BotBlock supports.
     *
     * <p>A response could look like this:
     * <br><pre><code>
     * {
     *     "somebotlist.com": {
     *         "api_docs": "https://somebotlist.com/docs",
     *         "api_post": "https://somebotlist.com/api/v1/bots/:id/post",
     *         "api_field": "server_count",
     *         "api_shard_id": "shard_id",
     *         "api_shard_count": "shard_count",
     *         "api_shards": "shards",
     *         "api_get": "https://somebotlist.com/api/v1/bots/:id"
     *     },
     *     "otherlist.org": {
     *         "api_docs": "https://docs.otherlist.org",
     *         "api_post": null,
     *         "api_field": null,
     *         "api_shard_id": null,
     *         "api_shard_count": null,
     *         "api_shards": null,
     *         "api_get": "https://api.otherlist.org/v2/bot/:id"
     *     }
     * }
     * </code></pre>
     *
     * @return The botlists as JSONObject.
     *
     * @throws IOException
     *         When the request couldn't be performed properly.
     * @throws RatelimitedException
     *         When the API gets ratelimited.
     *
     * @since v2.0.0
     */
    public JSONObject getBotlists() throws IOException, RatelimitedException{
        String url = BASE_URL + "lists";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try(Response response = CLIENT.newCall(request).execute()){
            Check.notNull(response.body(), "Received empty response body from BotBlcok API.");
            ResponseBody responseBody = response.body();

            Check.notEmpty(responseBody.string(), "Received empty response body from BotBlock API.");

            if(!response.isSuccessful()){
                if(response.code() == 429)
                    throw new RatelimitedException(responseBody.string());

                throw new IOException(String.format(
                        "Couldn't get Botlists. Site responded with error code %d (%s)",
                        response.code(),
                        response.message()
                ));
            }

            return new JSONObject(responseBody.string());
        }
    }

    private void postRequest() throws IOException, RatelimitedException{
        Check.notNull(json, "JSON may not be null.");
        Check.notEmpty(id, "ID may not be empty.");

        String url = BASE_URL + "count";

        RequestBody body = RequestBody.create(null, json.toString());
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", id)
                .addHeader("Content-Type", "application/json") // Some sites require this in the header.
                .post(body)
                .build();

        try(Response response = CLIENT.newCall(request).execute()){
            Check.notNull(response.body(), "Received empty response body from BotBlcok API.");
            ResponseBody responseBody = response.body();

            Check.notEmpty(responseBody.string(), "Received empty response body from BotBlock API.");

            if(!response.isSuccessful()){
                if(response.code() == 429)
                    throw new RatelimitedException(responseBody.string());

                throw new IOException(String.format(
                        "Couldn't post guild counts to BotBlockAPI! Site responded with error code %d (%s)",
                        response.code(),
                        response.message()
                ));
            }

            JSONObject json = new JSONObject(responseBody);
            if(json.has("failure")){
                JSONObject failure = json.getJSONObject("failure");

                List<String> sites = new ArrayList<>();
                for(String key : failure.keySet()){
                    try{
                        JSONArray array = failure.getJSONArray(key);
                        sites.add(String.format(
                                "Name: %s, Error code: %d, Error Message: %s",
                                key,
                                array.getInt(0),
                                array.getString(1)
                        ));
                    }catch (JSONException ex){
                        Map<String, Object> notFound = failure.toMap();
                        sites.add("Errors: " + notFound.toString());
                    }
                }

                throw new IOException(String.format(
                        "One or multiple requests failed! Response(s): %s",
                        String.join(", ", sites)
                ));
            }
        }
    }
}
