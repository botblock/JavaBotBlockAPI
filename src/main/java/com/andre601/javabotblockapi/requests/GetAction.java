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
 */

package com.andre601.javabotblockapi.requests;


import com.andre601.javabotblockapi.Site;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to perform GET actions with.
 * 
 * <p>With this class can you do the following actions:
 * <ul>
 *     <li>{@link #getBotInfo(Long) get full bot information}</li>
 *     <li>{@link #getBotListInfo(Long) get bot info from all bot lists}</li>
 *     <li>{@link #getBotListInfo(Long, Site) get bot info from a specific list}</li>
 *     <li>{@link #getBotList(String, Site) get a specific bot list}</li>
 *     <li>{@link #getBotLists(String) get all supported bot lists}</li>
 *     <li>{@link #getInvite(Long) get a bots invite link}</li>
 *     <li>{@link #getServerCount(Long) get a bots server count}</li>
 *     <li>{@link #getOwners(Long) get the owners of a bot}</li>
 * </ul>
 * 
 * <p>All requests are cached for 2 minutes. This can be disabled with {@link #GetAction(boolean) GetAction(true)} although it's not recommended.
 * 
 * @since v3.0.0
 */
public class GetAction{
    
    private final RequestHandler REQUEST_HANDLER = new RequestHandler();
    private boolean disableCache;
    
    /**
     * Constructor to get the instance of GetAction.
     */
    public GetAction(){
        this.disableCache = false;
    }
    
    /**
     * Constructor to get the instance of GetAction.
     * 
     * @param disableCache
     *        If the caching should be disabled. {@code true} means the cache will be <b>disabled</b>.
     *        <br>It's recommended to NOT disable caching if you don't use a own caching/rate limit system.
     */
    public GetAction(boolean disableCache){
        this.disableCache = disableCache;
    }
    
    /**
     * Gets the full information of a bot.
     *
     * <p>The JSONObject may look like this:
     * <br><pre><code>
     * {
     *     "id": "123456789012345678",
     *     "username": "MyBot",
     *     "discriminator": "1234",
     *     "owners": [
     *         "234567890123456789"
     *     ],
     *     "server_count": 100,
     *     "invite":{@literal "https://discordapp.com/oauth2/authorize?client_id=123456789012345678&scope=bot"},
     *     "list_data": {
     *         "somebotlist.com": [
     *             {"data"},
     *             200
     *         ],
     *         "otherlist.org": [
     *             {"data"},
     *             404
     *         ]
     *     }
     * }
     * </code></pre>
     * <br>The values of id, username, discriminator, owners, server_count and invite are based on the most common appearance.
     * <br><b>{@code {"data"}}</b> depends on what the bot list returns.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return A possibly-null JSONObject containing the bots information.
     */
    @Nullable
    public JSONObject getBotInfo(Long id){
        return REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
    }
    
    /**
     * Gets the full information of a bot.
     *
     * <p>The JSONObject may look like this:
     * <br><pre><code>
     * {
     *     "id": "123456789012345678",
     *     "username": "MyBot",
     *     "discriminator": "1234",
     *     "owners": [
     *         "234567890123456789"
     *     ],
     *     "server_count": 100,
     *     "invite":{@literal "https://discordapp.com/oauth2/authorize?client_id=123456789012345678&scope=bot"},
     *     "list_data": {
     *         "somebotlist.com": [
     *             {"data"},
     *             200
     *         ],
     *         "otherlist.org": [
     *             {"data"},
     *             404
     *         ]
     *     }
     * }
     * </code></pre>
     * <br>The values of id, username, discriminator, owners, server_count and invite are based on the most common appearance.
     * <br><b>{@code {"data"}}</b> depends on what the bot list returns.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return A possibly-null JSONObject containing the bots information.
     */
    @Nullable
    public JSONObject getBotInfo(@NotNull String id){
        return REQUEST_HANDLER.performGetBot(id, disableCache);
    }
    
    /**
     * Gets the information of the bot stored on the different bot lists.
     * <br>The returned JSON depends on what each bot list returns and is therefore different for each one.
     * 
     * @param  id
     *         The bots id to use.
     *         
     * @return A possibly-null JSONObject containing the bots information from the different bot lists.
     */
    @Nullable
    public JSONObject getBotListInfo(Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getJSONObject("list_data");
    }
    
    /**
     * Gets the information of the bot stored on the different bot lists.
     * <br>The returned JSON depends on what each bot list returns and is therefore different for each one.
     * 
     * @param  id
     *         The bots id to use.
     *         
     * @return A possibly-null JSONObject containing the bots information from the different bot lists.
     */
    @Nullable
    public JSONObject getBotListInfo(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getJSONObject("list_data");
    }
    
    /**
     * Gets the information of the bot on a specific bot list.
     * <br>The returned JSONArray depends on the bot list defined and can be different for each one.
     * 
     * @param  id
     *         The bots id to use.
     * @param  site
     *         The {@link com.andre601.javabotblockapi.Site site} to get info from.
     *         
     * @return A possibly-null JSONArray containing the bots info on the provided site.
     */
    @Nullable
    public JSONArray getBotListInfo(Long id, @NotNull Site site){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache).getJSONObject("list_data");
        
        return json.getJSONArray(site.getSite());
    }
    
    /**
     * Gets the information of the bot on a specific bot list.
     * <br>The returned JSONArray depends on the bot list defined and can be different for each one.
     * 
     * @param  id
     *         The bots id to use.
     * @param  site
     *         The site to get the info from.
     *         <br>A list of supported sites can be found {@link com.andre601.javabotblockapi.Site here}.
     * 
     * @return A possibly-null JSONArray containing the bots info on the provided site.
     */
    @Nullable
    public JSONArray getBotListInfo(Long id, @NotNull String site){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache).getJSONObject("list_data");
        
        return json.getJSONArray(site);
    }
    
    /**
     * Gets the information of the bot on a specific bot list.
     * <br>The returned JSONArray depends on the bot list defined and can be different for each one.
     * 
     * @param  id
     *         The bots id to use.
     * @param  site
     *         The {@link com.andre601.javabotblockapi.Site site} to get info from.
     *         
     * @return A possibly-null JSONArray containing the bots info on the provided site.
     */
    @Nullable
    public JSONArray getBotListInfo(@NotNull String id, @NotNull Site site){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache).getJSONObject("list_data");
        
        return json.getJSONArray(site.getSite());
    }
    
    /**
     * Gets the information of the bot on a specific bot list.
     * <br>The returned JSONArray depends on the bot list defined and can be different for each one.
     * 
     * @param  id
     *         The bots id to use.
     * @param  site
     *         The site to get the info from.
     *         <br>A list of supported sites can be found {@link com.andre601.javabotblockapi.Site here}.
     *         
     * @return A possibly-null JSONArray containing the bots info on the provided site.
     */
    @Nullable
    public JSONArray getBotListInfo(@NotNull String id, @NotNull String site){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache).getJSONObject("list_data");
        
        return json.getJSONArray(site);
    }
    
    /**
     * Gets the API information of a specific bot list on botblock.org.
     *
     * <p>The returned JSON could look like this:
     * <br><pre><code>
     * {
     *     "api_docs": "https://thelist.org/api/docs",
     *     "api_post": "https://thelist.org/api/bot/stats/:id",
     *     "api_field": "server_count",
     *     "api_shard_id": "shard_id",
     *     "api_shard_count": "shard_count",
     *     "api_shards": null,
     *     "api_get": "https://thelist.org/api/bot/info/:id"
     * }
     * </code></pre>
     *
     * @param  id
     *         The id used for the internal caching.
     * @param  site
     *         The {@link com.andre601.javabotblockapi.Site site} to get information from.
     *
     * @return A possibly-null JSONObject containing information from the provided bot list.
     */
    @Nullable
    public JSONObject getBotList(@NotNull String id, @NotNull Site site){
        JSONObject json = REQUEST_HANDLER.performGetList(id, disableCache);
        
        return json.getJSONObject(site.getSite());
    }
    
    /**
     * Gets the API information of a specific bot list on botblock.org.
     *
     * <p>The returned JSON could look like this:
     * <br><pre><code>
     * {
     *     "api_docs": "https://thelist.org/api/docs",
     *     "api_post": "https://thelist.org/api/bot/stats/:id",
     *     "api_field": "server_count",
     *     "api_shard_id": "shard_id",
     *     "api_shard_count": "shard_count",
     *     "api_shards": null,
     *     "api_get": "https://thelist.org/api/bot/info/:id"
     * }
     * </code></pre>
     *
     * @param  id
     *         The id used for the internal caching.
     * @param  site
     *         The site to get information from.
     *         <br>A list of supported sites can be found {@link com.andre601.javabotblockapi.Site here}.
     *
     * @return A possibly-null JSONObject containing information from the provided bot list.
     */
    @Nullable
    public JSONObject getBotList(@NotNull String id, @NotNull String site){
        JSONObject json = REQUEST_HANDLER.performGetList(id, disableCache);
        
        return json.getJSONObject(site);
    }
    
    /**
     * Gets the API information of all supported bot lists on botblock.org.
     *
     * <p>The returned JSON could look like this:
     * <br><pre><code>
     * {
     *     "thelist.org": {
     *         "api_docs": "https://thelist.org/api/docs",
     *         "api_post": "https://thelist.org/api/bot/stats/:id",
     *         "api_field": "server_count",
     *         "api_shard_id": "shard_id",
     *         "api_shard_count": "shard_count",
     *         "api_shards": null,
     *         "api_get": "https://thelist.org/api/bot/info/:id"
     *     },
     *     "listofbots.com": {
     *         "api_docs": "https://listofbots.com/docs",
     *         "api_post": "https://listofbots.com/api/stats/:id",
     *         "api_field": "guild_count",
     *         "api_shard_id": null,
     *         "api_shard_count": null,
     *         "api_shards": "shards",
     *         "api_get": null
     *     }
     * }
     * </code></pre>
     *
     * @param  id
     *         The id used for the internal caching.
     *
     * @return A possibly-null JSONObject containing information from all supported bot lists.
     */
    public JSONObject getBotLists(@NotNull String id){
        return REQUEST_HANDLER.performGetList(id, disableCache);
    }
    
    /**
     * Gets the OAuth invite of the bot.
     * <br>The invite is based on the most common appearance of it.
     * 
     * @param  id
     *         The bots id to use.
     *         
     * @return A String containing the OAuth invite for the bot.
     */
    public String getInvite(Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("invite");
    }
    
    /**
     * Gets the OAuth invite of the bot.
     * <br>The invite is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return A String containing the OAuth invite for the bot.
     */
    public String getInvite(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("invite");
    }
    
    /**
     * Gets the server count of the bot.
     * <br>The server count is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return A Integer containing the server count for the bot.
     */
    public int getServerCount(Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
    
        return json.getInt("server_count");
    }
    
    /**
     * Gets the server count of the bot.
     * <br>The server count is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return A Integer containing the server count for the bot.
     */
    public int getServerCount(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getInt("server_count");
    }
    
    /**
     * Gets an ArrayList with the owner ids of the bot.
     * <br>The IDs listed are based on how often they appear on the different bot lists.
     * 
     * @param  id
     *         The bots id to use.
     *         
     * @return A possibly-empty ArrayList.
     */
    public List<String> getOwners(Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        List<String> owners = new ArrayList<>();
        for(int i = 0; i < json.getJSONArray("owners").length(); i++)
            owners.add(json.getJSONArray("owners").getString(i));
        
        return owners;
    }
    
    /**
     * Gets an ArrayList with the owner ids of the bot.
     * <br>The IDs listed are based on how often they appear on the different bot lists.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return A possibly-empty ArrayList.
     */
    public List<String> getOwners(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        List<String> owners = new ArrayList<>();
        for(int i = 0; i < json.getJSONArray("owners").length(); i++)
            owners.add(json.getJSONArray("owners").getString(i));
        
        return owners;
    }
}
