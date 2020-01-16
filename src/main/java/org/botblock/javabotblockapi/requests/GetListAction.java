/*
 * Copyright 2020 Andre601
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

/**
 * Class used to perform GET actions on the <a href="https://botblock.org/api/docs#lists" target="_blank">{@code /lists} endpoint</a>.
 * 
 * <p>GET requests are cached for 2 minutes unless disabled through {@link #GetListAction(boolean) GetListAction(true)}.
 * 
 * @since 5.0.0
 */
public class GetListAction{
    
    private final RequestHandler REQUEST_HANDLER = new RequestHandler();
    private boolean disableCache;
    
    /**
     * Constructor to get the instance of GetListAction.
     */
    public GetListAction(){
        this.disableCache = false;
    }
    
    /**
     * Constructor to get the instance of GetListAction.
     * Use this if you want to disable the caching of the GET requests. <b>We do not recommend this without own caching/ratelimiting.</b>
     * 
     * @param disableCache
     *        Whether or not to disable caching. {@code true} means caching gets <b>disabled</b>!
     */
    public GetListAction(boolean disableCache){
        this.disableCache = disableCache;
    }
    
    /**
     * Gets the name of the specified field. The returned String can either be a URL or a field name.
     * 
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     * @param  field
     *         The {@link org.botblock.javabotblockapi.requests.GetListAction.ApiField API field} to get the value from.
     *         
     * @return Possibly-null String containing either a URL or API field name.
     */
    @Nullable
    public String getApiField(@NotNull String id, @NotNull Site site, @NotNull ApiField field){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString(field.name().toLowerCase());
    }
    
    /**
     * Gets the name of the specified field. The returned String can either be a URL or a field name.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     * @param  field
     *         The {@link org.botblock.javabotblockapi.requests.GetListAction.ApiField API field} to get the value from.
     *         
     * @return Possibly-null String containing either a URL or API field name.
     */
    @Nullable
    public String getApiField(@NotNull String id, @NotNull String site, @NotNull ApiField field){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString(field.name().toLowerCase());
    }
    
    /**
     * Gets the URL used to display a widget (custom image) of the bot.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     * 
     * @return Possibly-null String containing the URL used to display a widget.
     */
    @Nullable
    public String getBotWidgetUrl(@NotNull String id, @NotNull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("bot_widget");
    }
    
    /**
     * Gets the URL used to display a widget (custom image) of the bot.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return Possibly-null String containing the URL used to display a widget.
     */
    @Nullable
    public String getBotWidgetUrl(@NotNull String id, @NotNull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("bot_widget");
    }
    
    /**
     * Gets the description of the bot list.
     * 
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     * 
     * @return Possibly-null String containing the description/tag line of the bot list.
     */
    @Nullable
    public String getDescription(@NotNull String id, @NotNull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("description");
    }
    
    /**
     * Gets the description of the bot list.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return Possibly-null String containing the description/tag line of the bot list.
     */
    @Nullable
    public String getDescription(@NotNull String id, @NotNull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("description");
    }
    
    /**
     * Gets the invite to the Discord of the bot list.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return Possibly-null String containing the invite to the Discord of the bot list.
     */
    @Nullable
    public String getDiscordInvite(@NotNull String id, @NotNull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("discord");
    }
    
    /**
     * Gets the invite to the Discord of the bot list.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return Possibly-null String containing the invite to the Discord of the bot list.
     */
    @Nullable
    public String getDiscordInvite(@NotNull String id, @NotNull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("discord");
    }
    
    /**
     * Gets the features of the bot list.
     * <br>The listed features can be both positive and negative.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return Possibly-empty {@link org.json.JSONArray JSONArray} containing the features of the bot list.
     */
    public JSONArray getFeatures(@NotNull String id, @NotNull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getJSONArray("features");
    }
    
    /**
     * Gets the features of the bot list.
     * <br>The listed features can be both positive and negative.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return Possibly-empty {@link org.json.JSONArray JSONArray} containing the features of the bot list.
     */
    public JSONArray getFeatures(@NotNull String id, @NotNull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getJSONArray("features");
    }
    
    /**
     * Returns the filtered JSON of bot lists.
     * <br>The filtered version only contains the {@link #getApiField(String, Site, ApiField) API fields} of the bot lists.
     *
     * @param  id
     *         The id used for the cache.
     *
     * @return {@link org.json.JSONObject JSONObject} containing the filtered information of the bot lists.
     */
    public JSONObject getFilteredLists(@NotNull String id){
        CheckUtil.notEmpty(id, "id");
        
        return REQUEST_HANDLER.performGetList(id, disableCache, true);
    }
    
    /**
     * Gets the currently used icon of the bot list.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return Possibly-null String containing the icon of the bot list.
     */
    @Nullable
    public String getIcon(@NotNull String id, @NotNull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
    
        return json.getString("icon");
    }
    
    /**
     * Gets the currently used icon of the bot list.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return Possibly-null String containing the icon of the bot list.
     */
    @Nullable
    public String getIcon(@NotNull String id, @NotNull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("icon");
    }
    
    /**
     * Gets the id of the bot list.
     * <br>The id is used for the <a href="https://botblock.org/api/docs#count" target="_blank">{@code /api/count} endpoint</a>.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return String containing the id of the bot list.
     */
    public String getId(@NotNull String id, @NotNull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("id");
    }
    
    /**
     * Gets the id of the bot list.
     * <br>The id is used for the <a href="https://botblock.org/api/docs#count" target="_blank">{@code /api/count} endpoint</a>.
     * 
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     * 
     * @return String containing the id of the bot list.
     */
    public String getId(@NotNull String id, @NotNull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("id");
    }
    
    /**
     * Gets the primary language of the bot list.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return String containing the primarily used language of the bot list.
     */
    public String getLanguage(@NotNull String id, @NotNull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("language");
    }
    
    /**
     * Gets the primary language of the bot list.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return String containing the primarily used language of the bot list.
     */
    public String getLanguage(@NotNull String id, @NotNull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("language");
    }
    
    /**
     * Gets the information of a specific bot list.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return {@link org.json.JSONObject JSONObject} containing information about the specified bot list.
     */
    public JSONObject getList(@NotNull String id, @NotNull Site site){
        CheckUtil.notEmpty(id, "id");
        
        return REQUEST_HANDLER.performGetList(id, site.getSite(), disableCache);
    }
    
    /**
     * Gets the information of a specific bot list.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return {@link org.json.JSONObject JSONObject} containing information about the specified bot list.
     */
    public JSONObject getList(@NotNull String id, @NotNull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        
        return REQUEST_HANDLER.performGetList(id, site, disableCache);
    }
    
    /**
     * Gets the information about all the currently listed bot lists.
     *
     * @param  id
     *         The id used for the cache.
     *
     * @return {@link org.json.JSONObject JSONObject} containing information about all the different bot lists.
     */
    public JSONObject getLists(@NotNull String id){
        CheckUtil.notEmpty(id, "id");
        
        return REQUEST_HANDLER.performGetList(id, disableCache, false);
    }
    
    /**
     * Gets the name of the bot list.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return String containing the name of the bot list.
     */
    public String getName(@NotNull String id, @NotNull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("name");
    }
    
    /**
     * Gets the name of the bot list.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return String containing the name of the bot list.
     */
    public String getName(@NotNull String id, @NotNull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("name");
    }
    
    /**
     * Gets the owners of a bot list.
     * <br>The pattern in which the owners are listed is {@code <name#discrim> (<id>), <name#discrim> (<id>), ...}
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return Possibly-null String containing the owners of the bot list.
     */
    @Nullable
    public String getOwners(@NotNull String id, @NotNull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("owners");
    }
    
    /**
     * Gets the owners of a bot list.
     * <br>The pattern in which the owners are listed is {@code <name#discrim> (<id>), <name#discrim> (<id>), ...}
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return Possibly-null String containing the owners of the bot list,
     */
    @Nullable
    public String getOwners(@NotNull String id, @NotNull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("owners");
    }
    
    /**
     * Gets the time on which the bot list was added to BotBlock.org
     * <br>The time is in seconds (UNIX timestamp)
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return Integer displaying the UNIX time at which the bot list was added to BotBlock.
     */
    public Integer getTimeAdded(@NotNull String id, @NotNull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getInt("added");
    }
    
    /**
     * Gets the time on which the bot list was added to BotBlock.org
     * <br>The time is in seconds (UNIX timestamp)
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return Integer displaying the UNIX time at which the bot list was added to BotBlock.
     */
    public Integer getTimeAdded(@NotNull String id, @NotNull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getInt("added");
    }
    
    /**
     * Gets the URL for the bot list's website.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return String containing the URL to the bot list website.
     */
    public String getUrl(@NotNull String id, @NotNull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("url");
    }
    
    /**
     * Gets the URL for the bot list's website.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return String containing the URL to the bot list website.
     */
    public String getUrl(@NotNull String id, @NotNull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("url");
    }
    
    /**
     * Returns if the bot list is defunct.
     * <br>A defunct bot list is not displayed on the main site and is also excluded from the POST api.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return {@code true} if the bot list is defunct, {@code false} otherwise.
     */
    public boolean isDefunct(@NotNull String id, @NotNull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getInt("defunct") == 1;
    }
    
    /**
     * Returns if the bot list is defunct.
     * <br>A defunct bot list is not displayed on the main site and is also excluded from the POST api.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return {@code true} if the bot list is defunct, {@code false} otherwise.
     */
    public boolean isDefunct(@NotNull String id, @NotNull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getInt("defunct") == 1;
    }
    
    /**
     * Returns if the bot list is only for Discord bots.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return {@code true} if the list is only for Discord bots, {@code false} otherwise.
     */
    public boolean isDiscordOnly(@NotNull String id, @NotNull Site site){
        CheckUtil.notEmpty(id, "name");
        JSONObject json = getList(id, site);
        
        return json.getInt("discord_only") == 1;
    }
    
    /**
     * Returns if the bot list is only for Discord bots.
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site site} to get information from.
     *
     * @return {@code true} if the list is only for Discord bots, {@code false} otherwise.
     */
    public boolean isDiscordOnly(@NotNull String id, @NotNull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
    
        return json.getInt("discord_only") == 1;
    }
    
    /**
     * Enum containing the different API fields a bot list may have.
     * <br>This is used for {@link org.botblock.javabotblockapi.requests.GetListAction#getApiField(String, Site, ApiField) GetListAction#getApiField(String, Site|String, ApiField)}
     * 
     * @since 5.0.0
     */
    public enum ApiField{
        // URL fields
    
        /**
         * API field to return the URL to list all bots of a bot list.
         */
        API_ALL,
        
        /**
         * API field to return the URL to the API documentation of the bot list.
         */
        API_DOCS,
        
        /**
         * API field to return the URL used to GET information about a bot.
         */
        API_GET,
        
        /**
         * API field to return the URL used to POST information from a bot.
         */
        API_POST,
        
        // Fields returning a String
    
        /**
         * Name of the field for posting the server count (i.e. {@code server_count})
         */
        API_FIELD,
        
        /**
         * Name of the field for posting the shard id (i.e. {@code shard_id})
         */
        API_SHARD_ID,
    
        /**
         * Name of the field for posting the shard count (i.e. {@code shard_count})
         */
        API_SHARD_COUNT,
    
        /**
         * Name of the field for posting the different shard info (i.e. {@code shards})
         */
        API_SHARDS
    }
}
