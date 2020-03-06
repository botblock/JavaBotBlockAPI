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

import org.botblock.javabotblockapi.Site;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to perform GET actions on the <a href="https://botblock.org/api/docs#bots" target="_blank">{@code /api/bots/:id} endpoint</a>.
 *
 * <p>GET requests are cached for 2 minutes unless disabled through {@link #GetBotAction(boolean) GetBotAction(true)}
 *
 * @since 5.0.0
 */
public class GetBotAction{
    
    private final RequestHandler REQUEST_HANDLER = new RequestHandler();
    private boolean disableCache;
    
    /**
     * Constructor to get the instance of GetBotAction.
     */
    public GetBotAction(){
        this.disableCache = false;
    }
    
    /**
     * Constructor to get the instance of GetBotAction.
     * <br>Use this if you want to disable the caching of the GET requests.
     * <br><b>We do not recommend this without own caching/ratelimiting.</b>
     *
     * @param disableCache
     *        Whether or not caching should be disabled. {@code true} means caching is <b>disabled</b>!
     */
    public GetBotAction(boolean disableCache){
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
     *     "prefix": "?",
     *     "website": "",
     *     "github": "",
     *     "support": "",
     *     "library": "JDA",
     *     "list_data": {
     *         "somebotlist.com": [
     *             {"data": "Unique bot list data"},
     *             200
     *         ],
     *         "otherlist.org": [
     *             {"data": "Unique bot list data"},
     *             404
     *         ]
     *     }
     * }
     * </code></pre>
     * <br>The values of id, username, discriminator, owners, server_count and invite are based on the most common appearance.
     * <br>The returned information from the different bot list may vary for each one.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-null JSONObject containing the bots information.
     */
    @Nullable
    public JSONObject getBotInfo(@NotNull Long id){
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
     *     "prefix": "?",
     *     "website": "",
     *     "github": "",
     *     "support": "",
     *     "library": "JDA",
     *     "list_data": {
     *         "somebotlist.com": [
     *             {"data": "Unique bot list data"},
     *             200
     *         ],
     *         "otherlist.org": [
     *             {"data": "Unique bot list data"},
     *             404
     *         ]
     *     }
     * }
     * </code></pre>
     * <br>The values of id, username, discriminator, owners, server_count and invite are based on the most common appearance.
     * <br>The returned information from the different bot list may vary for each one.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-null JSONObject containing the bots information.
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
     * @return Possibly-null JSONObject containing the bots information from the different bot lists.
     */
    @Nullable
    public JSONObject getBotListInfo(@NotNull Long id){
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
     * @return Possibly-null JSONObject containing the bots information from the different bot lists.
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
     *         The {@link org.botblock.javabotblockapi.Site site} to get info from.
     *
     * @return Possibly-null JSONArray containing the bots info on the provided site.
     */
    @Nullable
    public JSONArray getBotListInfo(@NotNull Long id, @NotNull Site site){
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
     *         <br>A list of supported sites can be found {@link Site here}.
     *
     * @return Possibly-null JSONArray containing the bots info on the provided site.
     */
    @Nullable
    public JSONArray getBotListInfo(@NotNull Long id, @NotNull String site){
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
     *         The {@link org.botblock.javabotblockapi.Site site} to get info from.
     *
     * @return Possibly-null JSONArray containing the bots info on the provided site.
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
     *         <br>A list of supported sites can be found {@link org.botblock.javabotblockapi.Site here}.
     *
     * @return Possibly-null JSONArray containing the bots info on the provided site.
     */
    @Nullable
    public JSONArray getBotListInfo(@NotNull String id, @NotNull String site){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache).getJSONObject("list_data");
        
        return json.getJSONArray(site);
    }
    
    /**
     * Gets the discriminator (The 4 numbers after the # in the username) of the bot.
     * <br>The discriminator is based on the most common appearance of it across the bot lists.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return The discriminator of the bot or {@code 0000} if unknown/invalid bot.
     *
     * @since  4.2.0
     */
    public String getDiscriminator(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("discriminator");
    }
    
    /**
     * Gets the discriminator (The 4 numbers after the # in the username) of the bot.
     * <br>The discriminator is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return The discriminator of the bot or {@code 0000} if unknown/invalid bot.
     *
     * @since  4.2.0
     */
    public String getDiscriminator(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("discriminator");
    }
    
    /**
     * Gets the GitHub link of the bot.
     * <br>The GitHub link is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty String containing the GitHub link of the bot.
     *
     * @since  4.2.0
     */
    public String getGitHub(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("github");
    }
    
    /**
     * Gets the GitHub link of the bot.
     * <br>The GitHub link is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty String containing the GitHub link of the bot.
     *
     * @since  4.2.0
     */
    public String getGitHub(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("github");
    }
    
    /**
     * Gets the OAuth invite of the bot.
     * <br>The invite is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty String containing the OAuth invite for the bot.
     */
    public String getInvite(@NotNull Long id){
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
     * @return Possibly-empty String containing the OAuth invite for the bot.
     */
    public String getInvite(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("invite");
    }
    
    /**
     * Gets the currently used library of the bot.
     * <br>The library is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty String containing the library of the bot.
     *
     * @since  4.2.0
     */
    public String getLibrary(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("library");
    }
    
    /**
     * Gets the currently used library of the bot.
     * <br>The library is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty String containing the library of the bot.
     *
     * @since  4.2.0
     */
    public String getLibrary(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("library");
    }
    
    /**
     * Gets the name of the bot.
     * <br>The name is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return The name of the bot or {@code Unknown} if unknown/invalid bot.
     *
     * @since  4.2.0
     */
    public String getName(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("username");
    }
    
    /**
     * Gets the name of the bot.
     * <br>The name is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return The name of the bot or {@code Unknown} if unknown/invalid bot.
     *
     * @since  4.2.0
     */
    public String getName(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("username");
    }
    
    /**
     * Gets an ArrayList with the owner ids of the bot.
     * <br>The IDs listed are based on how often they appear on the different bot lists.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty ArrayList containing the owners of the bot.
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
     * @return Possibly-empty ArrayList containing the owners of the bot.
     */
    public List<String> getOwners(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        List<String> owners = new ArrayList<>();
        for(int i = 0; i < json.getJSONArray("owners").length(); i++)
            owners.add(json.getJSONArray("owners").getString(i));
        
        return owners;
    }
    
    /**
     * Gets the prefix of the bot.
     * <br>The prefix is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty String containing the prefix of the bot.
     *
     * @since  4.2.0
     */
    public String getPrefix(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("prefix");
    }
    
    /**
     * Gets the prefix of the bot.
     * <br>The prefix is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty String containing the prefix of the bot.
     *
     * @since  v4.2.0
     */
    public String getPrefix(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("prefix");
    }
    
    /**
     * Gets the server count of the bot.
     * <br>The server count is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-null Integer containing the server count for the bot.
     */
    @Nullable
    public Integer getServerCount(@NotNull Long id){
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
     * @return Possibly-null Integer containing the server count for the bot.
     */
    @Nullable
    public Integer getServerCount(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getInt("server_count");
    }
    
    /**
     * Gets the support link (i.e. Discord invite) from the bot.
     * <br>The link is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty String containing the support link.
     */
    public String getSupportLink(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("support");
    }
    
    /**
     * Gets the support link (i.e. Discord invite) from the bot.
     * <br>The link is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-empty String containing the support link.
     */
    public String getSupportLink(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("support");
    }
    
    /**
     * Gets the website of the bot.
     * <br>The website is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-Empty String containing the bot's website.
     *
     * @since  v4.2.0
     */
    public String getWebsite(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("website");
    }
    
    /**
     * Gets the website of the bot.
     * <br>The website is based on the most common appearance of it.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-Empty String containing the bot's website.
     *
     * @since  v4.2.0
     */
    public String getWebsite(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("website");
    }
}
