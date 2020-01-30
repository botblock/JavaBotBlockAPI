/*
 * Copyright 2019 - 2020 Andre601
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
 */

package org.botblock.javabotblockapi.requests;

import org.botblock.javabotblockapi.BotBlockAPI;
import org.botblock.javabotblockapi.exceptions.RatelimitedException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class used to perform POST requests towards the <a href="https://botblock.org/api/docs#count" target="_blank">/api/count</a> endpoint.
 * 
 * <p>You can {@link #enableAutoPost(JDA, BotBlockAPI) post automatically} or {@link #postGuilds(JDA, BotBlockAPI) post manually}.
 * 
 * @since 3.0.0
 */
public class PostAction{
    
    private final RequestHandler REQUEST_HANDLER = new RequestHandler();
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    
    /**
     * Empty constructor to create the PostAction instance.
     */
    public PostAction(){}
    
    /**
     * Shuts down the scheduler, to stop the automatic posting.
     */
    public void disableAutoPost(){
        scheduler.shutdown();
    }
    
    /**
     * Starts posting of the guild count each n minutes.
     * <br>The delay in which this happens is set using <code>{@link org.botblock.javabotblockapi.BotBlockAPI.Builder#setUpdateDelay(Integer)  BotBlockAPI.Builder#setUpdateInterval(Integer)}</code>
     * 
     * <p><b>The scheduler will stop (cancel) the task, when an Exception appears!</b>
     * 
     * @param jda
     *        The {@link net.dv8tion.jda.api.JDA JDA instance} to use.
     * @param botBlockAPI
     *        The {@link org.botblock.javabotblockapi.BotBlockAPI BotBlockAPI instance} to use.
     */
    public void enableAutoPost(@NotNull JDA jda, @NotNull BotBlockAPI botBlockAPI){
        scheduler.scheduleAtFixedRate(() -> {
            try{
                postGuilds(jda, botBlockAPI);
            }catch(IOException | RatelimitedException ex){
                ex.printStackTrace();
            }
        }, botBlockAPI.getUpdateDelay(), botBlockAPI.getUpdateDelay(), TimeUnit.MINUTES);
    }
    
    /**
     * Starts posting of the guild count each n minutes.
     * <br>The delay in which this happens is set using <code>{@link org.botblock.javabotblockapi.BotBlockAPI.Builder#setUpdateDelay(Integer)}  BotBlockAPI.Builder#setUpdateInterval(Integer)}</code>
     *
     * <p><b>The scheduler will stop (cancel) the task, when an Exception appears!</b>
     *
     * @param botId
     *        The ID of the bot as Long.
     * @param guilds
     *        The guild count.
     * @param botBlockAPI
     *        The {@link org.botblock.javabotblockapi.BotBlockAPI BotBlockAPI instance} to use.
     */
    public void enableAutoPost(@NotNull Long botId, int guilds, @NotNull BotBlockAPI botBlockAPI){
        scheduler.scheduleAtFixedRate(() -> {
            try{
                postGuilds(botId, guilds, botBlockAPI);
            }catch(IOException | RatelimitedException ex){
                ex.printStackTrace();
            }
        }, botBlockAPI.getUpdateDelay(), botBlockAPI.getUpdateDelay(), TimeUnit.MINUTES);
    }
    
    /**
     * Starts posting of the guild count each n minutes.
     * <br>The delay in which this happens is set using <code>{@link org.botblock.javabotblockapi.BotBlockAPI.Builder#setUpdateDelay(Integer)}  BotBlockAPI.Builder#setUpdateInterval(Integer)}</code>
     *
     * <p><b>The scheduler will stop (cancel) the task, when an Exception appears!</b>
     *
     * @param shardManager
     *        The {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance} to use.
     * @param botBlockAPI
     *        The {@link org.botblock.javabotblockapi.BotBlockAPI BotBlockAPI instance} to use.
     */
    public void enableAutoPost(@NotNull ShardManager shardManager, @NotNull BotBlockAPI botBlockAPI){
        scheduler.scheduleAtFixedRate(() -> {
            try{
                postGuilds(shardManager, botBlockAPI);
            }catch(IOException | RatelimitedException ex){
                ex.printStackTrace();
            }
        }, botBlockAPI.getUpdateDelay(), botBlockAPI.getUpdateDelay(), TimeUnit.MINUTES);
    }
    
    /**
     * Starts posting of the guild count each n minutes.
     * <br>The delay in which this happens is set using <code>{@link org.botblock.javabotblockapi.BotBlockAPI.Builder#setUpdateDelay(Integer)}  BotBlockAPI.Builder#setUpdateInterval(Integer)}</code>
     *
     * <p><b>The scheduler will stop (cancel) the task, when an Exception appears!</b>
     *
     * @param botId
     *        The ID of the bot as String.
     * @param guilds
     *        The guild count.
     * @param botBlockAPI
     *        The {@link org.botblock.javabotblockapi.BotBlockAPI BotBlockAPI instance} to use.
     */
    public void enableAutoPost(@NotNull String botId, int guilds, @NotNull BotBlockAPI botBlockAPI){
        scheduler.scheduleAtFixedRate(() -> {
                try{
                    postGuilds(botId, guilds, botBlockAPI); 
                }catch(IOException | RatelimitedException ex){
                    ex.printStackTrace(); 
                }
        }, botBlockAPI.getUpdateDelay(), botBlockAPI.getUpdateDelay(), TimeUnit.MINUTES);
    }
    
    /**
     * Posts the guild count provided through the {@link net.dv8tion.jda.api.JDA JDA instance}.
     * <br><b>It's recommended to use {@link #postGuilds(ShardManager, BotBlockAPI) postGuilds(ShardManager, BotBlockAPI)} 
     * if you're using a sharded bot.</b>
     * 
     * <p>When the amount of shards a bot has is bigger than one will {@code shard_id} and {@code shard_count} be added.
     * 
     * @param  jda
     *         The {@link net.dv8tion.jda.api.JDA JDA instance}.
     * @param  botBlockAPI
     *         The {@link org.botblock.javabotblockapi.BotBlockAPI BotBlockAPI instance}.
     *
     * @throws java.io.IOException
     *         When the post request couldn't be performed.
     * @throws org.botblock.javabotblockapi.exceptions.RatelimitedException
     *         When we exceed the rate-limit of the BotBlock API.
     */
    public void postGuilds(@NotNull JDA jda, @NotNull BotBlockAPI botBlockAPI) throws IOException, RatelimitedException{
        JSONObject json = new JSONObject()
                .put("server_count", jda.getGuilds().size())
                .put("bot_id", jda.getSelfUser().getId());
        
        if(jda.getShardInfo().getShardTotal() > 1)
            json.put("shard_id", jda.getShardInfo().getShardId())
                    .put("shard_count", jda.getShardInfo().getShardTotal());
        
        botBlockAPI.getTokens().forEach(json::put);
        
        REQUEST_HANDLER.performPOST(json, botBlockAPI.getTokens().size());
    }
    
    /**
     * Posts the guild count with the provided bot id.
     * 
     * @param  botId
     *         The ID of the bot.
     * @param  guilds
     *         The guild count.
     * @param  botBlockAPI
     *         The {@link org.botblock.javabotblockapi.BotBlockAPI BotBlockAPI instance}.
     *
     * @throws java.io.IOException
     *         When the post request couldn't be performed.
     * @throws org.botblock.javabotblockapi.exceptions.RatelimitedException
     *         When we exceed the rate-limit of the BotBlock API.
     */
    public void postGuilds(@NotNull Long botId, @NotNull Integer guilds, @NotNull BotBlockAPI botBlockAPI) throws IOException, RatelimitedException{
        postGuilds(Long.toString(botId), guilds, botBlockAPI);
    }
    
    /**
     * Posts the guild count from the provided {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance}.
     * <br>The guild count of each shard will be added as an JSONArray.
     * 
     * @param  shardManager
     *         The {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance}.
     * @param  botBlockAPI
     *         The {@link org.botblock.javabotblockapi.BotBlockAPI BotBlockAPI instance}.
     *
     * @throws java.io.IOException
     *         When the post request couldn't be performed.
     * @throws org.botblock.javabotblockapi.exceptions.RatelimitedException
     *         When we exceed the rate-limit of the BotBlock API.
     */
    public void postGuilds(@NotNull ShardManager shardManager, @NotNull BotBlockAPI botBlockAPI) throws IOException, RatelimitedException{
        JDA jda = shardManager.getShardById(0);
        if(jda == null)
            throw new NullPointerException("Received shard was null!");
        
        JSONObject json = new JSONObject()
                .put("server_count", shardManager.getGuilds().size())
                .put("bot_id", jda.getSelfUser().getId())
                .put("shard_count", shardManager.getShardCache().size());
    
        List<Integer> shards = new ArrayList<>();
        for(JDA shard : shardManager.getShards())
            shards.add(shard.getGuilds().size());
        
        json.put("shards", new JSONArray(Arrays.deepToString(shards.toArray())));
        botBlockAPI.getTokens().forEach(json::put);
        
        REQUEST_HANDLER.performPOST(json, botBlockAPI.getTokens().size());
    }
    
    /**
     * Posts the guild count with the provided bot id.
     *
     * @param  botId
     *         The ID of the bot.
     * @param  guilds
     *         The guild count.
     * @param  botBlockAPI
     *         The {@link org.botblock.javabotblockapi.BotBlockAPI BotBlockAPI instance}.
     *
     * @throws java.io.IOException
     *         When the post request couldn't be performed.
     * @throws org.botblock.javabotblockapi.exceptions.RatelimitedException
     *         When we exceed the rate-limit of the BotBlock API.
     */
    public void postGuilds(@NotNull String botId, @NotNull Integer guilds, @NotNull BotBlockAPI botBlockAPI) throws IOException, RatelimitedException{
        JSONObject json = new JSONObject()
                .put("server_count", guilds)
                .put("bot_id", botId);
        
        botBlockAPI.getTokens().forEach(json::put);
        
        REQUEST_HANDLER.performPOST(json, botBlockAPI.getTokens().size());
    }
    
}
