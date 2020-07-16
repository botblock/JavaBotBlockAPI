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

import org.botblock.javabotblockapi.core.Site;
import org.botblock.javabotblockapi.core.requests.CheckUtil;
import org.botblock.javabotblockapi.requests.handler.RequestHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to perform GET actions on the <a href="https://botblock.org/api/docs#lists" target="_blank">{@code /api/lists}</a>
 * and <a href="https://botblock.org/api/docs#list" target="_blank">{@code /api/lists/:id}</a> endpoints.
 *
 * <p>GET requests are cached for 2 minutes unless disabled through {@link #GetListAction(boolean, String) GetListAction(true, String)}
 * or {@link #GetListAction(boolean, String, String) GetListAction(true, String, String)}.
 *
 * @since 5.0.0
 */
public class GetListAction{
    
    private final RequestHandler REQUEST_HANDLER;
    private final boolean disableCache;
    
    /**
     * Constructor to get an instance of GetListAction.
     *
     * <p>Using this constructor will set the following default values:
     * <br><ul>
     *     <li>Cache: {@code Enabled}</li>
     *     <li>User-Agent: {@code "JavaBotBlockAPI-0000/API_VERSION (Unknown; +https://jbba.dev) DBots/{id}"}</li>
     * </ul>
     *
     * @param id
     *        The id of the bot. This is required for the internal User-Agent.
     *
     * @throws java.lang.NullPointerException
     *         When the provided id is empty.
     */
    public GetListAction(@Nonnull String id){
        this(false, id);
    }
    
    /**
     * Constructor to get an instance of GetListAction.
     * <br>This constructor allows you to disable the internal caching, by providing {@code true} as the first argument.
     *
     * <p>Using this constructor will set the following default values:
     * <br><ul>
     *     <li>User-Agent: {@code "JavaBotBlockAPI-0000/API_VERSION (Unknown; +https://jbba.dev) DBots/{id}"}</li>
     * </ul>
     *
     * @param disableCache
     *        If the cache should be disabled.
     *        <br>{@code true} means the cache is <b>disabled</b>.
     * @param id
     *        The id of the bot. This is required for the internal User-Agent.
     *
     * @throws java.lang.NullPointerException
     *         When the provided id is empty.
     */
    public GetListAction(boolean disableCache, @Nonnull String id){
        this(disableCache, "JavaBotBlockAPI-0000/API_VERSION (Unknown; +https://jbba.dev) DBots/{id}", id);
    }
    
    /**
     * Constructor to get the instance of GetListAction.
     * <br>This constructor allows you to disable the internal caching, by providing {@code true} as the first argument
     * and also set a own User-Agent for the requests by providing any String as the second argument.
     *
     * <p>Note that you can provide {@code {id}} inside the userAgent to get it replaced with the provided id.
     *
     * @param disableCache
     *        If the cache should be disabled.
     *        <br>{@code true} means the cache is <b>disabled</b>.
     * @param userAgent
     *        The Name that should be used as User-Agent.
     * @param id
     *        The id of the bot. This is required for the internal User-Agent.
     *
     * @throws java.lang.NullPointerException
     *         When the provided userAgent or id is empty.
     */
    public GetListAction(boolean disableCache, @Nonnull String userAgent, @Nonnull String id){
        CheckUtil.notEmpty(userAgent, "UserAgent");
        CheckUtil.notEmpty(id, "ID");
        
        this.disableCache = disableCache;
        this.REQUEST_HANDLER = new RequestHandler(userAgent.replace("{id}", id));
    }
    
    /**
     * Gets the name of the specified field. The returned String can either be a URL or a field name.
     * 
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     * @param  field
     *         The {@link org.botblock.javabotblockapi.requests.GetListAction.ApiField API field} to get the value from.
     *
     * @return Possibly-null String containing either a URL or API field name.
     */
    @Nullable
    public String getApiField(@Nonnull String id, @Nonnull Site site, @Nonnull ApiField field){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString(field.getApiField());
    }
    
    /**
     * Gets the name of the specified field. The returned String can either be a URL or a field name.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     * @param  field
     *         The {@link org.botblock.javabotblockapi.requests.GetListAction.ApiField API field} to get the value from.
     *
     * @return Possibly-null String containing either a URL or API field name.
     */
    @Nullable
    public String getApiField(@Nonnull String id, @Nonnull String site, @Nonnull ApiField field){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString(field.getApiField());
    }
    
    /**
     * Gets the URL used to display a widget (custom image) of the bot.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return Possibly-null String containing the URL used to display a widget.
     */
    @Nullable
    public String getBotWidgetUrl(@Nonnull String id, @Nonnull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("bot_widget");
    }
    
    /**
     * Gets the URL used to display a widget (custom image) of the bot.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return Possibly-null String containing the URL used to display a widget.
     */
    @Nullable
    public String getBotWidgetUrl(@Nonnull String id, @Nonnull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("bot_widget");
    }
    
    /**
     * Gets the description of the bot list.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return Possibly-null String containing the description/tag line of the bot list.
     */
    @Nullable
    public String getDescription(@Nonnull String id, @Nonnull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("description");
    }
    
    /**
     * Gets the description of the bot list.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return Possibly-null String containing the description/tag line of the bot list.
     */
    @Nullable
    public String getDescription(@Nonnull String id, @Nonnull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("description");
    }
    
    /**
     * Gets the invite to the Discord of the bot list.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return Possibly-null String containing the invite to the Discord of the bot list.
     */
    @Nullable
    public String getDiscordInvite(@Nonnull String id, @Nonnull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("discord");
    }
    
    /**
     * Gets the invite to the Discord of the bot list.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return Possibly-null String containing the invite to the Discord of the bot list.
     */
    @Nullable
    public String getDiscordInvite(@Nonnull String id, @Nonnull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("discord");
    }
    
    /**
     * Gets the features of the bot list.
     * <br>The listed features can be both positive and negative.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return Possibly-empty {@link org.json.JSONArray JSONArray} containing the features of the bot list.
     */
    public JSONArray getFeatures(@Nonnull String id, @Nonnull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getJSONArray("features");
    }
    
    /**
     * Gets the features of the bot list.
     * <br>The listed features can be both positive and negative.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return Possibly-empty {@link org.json.JSONArray JSONArray} containing the features of the bot list.
     */
    public JSONArray getFeatures(@Nonnull String id, @Nonnull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getJSONArray("features");
    }
    
    /**
     * Returns the filtered JSON of bot lists.
     * <br>The filtered version only contains the {@link #getApiField(String, Site, ApiField) API fields} of the bot lists.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     *
     * @return {@link org.json.JSONObject JSONObject} containing the filtered information of the bot lists.
     */
    public JSONObject getFilteredLists(@Nonnull String id){
        CheckUtil.notEmpty(id, "id");
        
        return REQUEST_HANDLER.performGetList(id, disableCache, true);
    }
    
    /**
     * Gets the URL displaying the current Icon of the bot list.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return Possibly-null String containing the Icon-URL of the bot list.
     */
    @Nullable
    public String getIcon(@Nonnull String id, @Nonnull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("icon");
    }
    
    /**
     * Gets the URL displaying the current Icon of the bot list.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return Possibly-null String containing the Icon-URL of the bot list.
     */
    @Nullable
    public String getIcon(@Nonnull String id, @Nonnull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("icon");
    }
    
    /**
     * Gets the id of the bot list.
     * <br>The id is used for the <a href="https://botblock.org/api/docs#count" target="_blank">{@code /api/count} endpoint</a>.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return String containing the id of the bot list.
     */
    public String getId(@Nonnull String id, @Nonnull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("id");
    }
    
    /**
     * Gets the id of the bot list.
     * <br>The id is used for the <a href="https://botblock.org/api/docs#count" target="_blank">{@code /api/count} endpoint</a>.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return String containing the id of the bot list.
     */
    public String getId(@Nonnull String id, @Nonnull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("id");
    }
    
    /**
     * Gets the primary language of the bot list.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return String containing the primarily used language of the bot list.
     */
    public String getLanguage(@Nonnull String id, @Nonnull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("language");
    }
    
    /**
     * Gets the primary language of the bot list.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return String containing the primarily used language of the bot list.
     */
    public String getLanguage(@Nonnull String id, @Nonnull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("language");
    }
    
    /**
     * Gets the information of a specific bot list.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return {@link org.json.JSONObject JSONObject} containing information about the specified bot list.
     */
    public JSONObject getList(@Nonnull String id, @Nonnull Site site){
        CheckUtil.notEmpty(id, "id");
        
        return REQUEST_HANDLER.performGetList(id, site.getSite(), disableCache);
    }
    
    /**
     * Gets the information of a specific bot list.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return {@link org.json.JSONObject JSONObject} containing information about the specified bot list.
     */
    public JSONObject getList(@Nonnull String id, @Nonnull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        
        return REQUEST_HANDLER.performGetList(id, site, disableCache);
    }
    
    /**
     * Gets the information about all the currently listed bot lists.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     *
     * @return {@link org.json.JSONObject JSONObject} containing information about all the different bot lists.
     */
    public JSONObject getLists(@Nonnull String id){
        CheckUtil.notEmpty(id, "id");
        
        return REQUEST_HANDLER.performGetList(id, disableCache, false);
    }
    
    /**
     * Gets the name of the bot list.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return String containing the name of the bot list.
     */
    public String getName(@Nonnull String id, @Nonnull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("name");
    }
    
    /**
     * Gets the name of the bot list.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return String containing the name of the bot list.
     */
    public String getName(@Nonnull String id, @Nonnull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("name");
    }
    
    /**
     * Gets the owners of a bot list.
     * <br>The pattern in which the owners are listed is {@code <name#discrim> (<id>), <name#discrim> (<id>), ...}
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return Possibly-null String containing the owners of the bot list.
     */
    @Nullable
    public String getOwners(@Nonnull String id, @Nonnull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("owners");
    }
    
    /**
     * Gets the owners of a bot list.
     * <br>The pattern in which the owners are listed is {@code <name#discrim> (<id>), <name#discrim> (<id>), ...}
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return Possibly-null String containing the owners of the bot list,
     */
    @Nullable
    public String getOwners(@Nonnull String id, @Nonnull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("owners");
    }
    
    /**
     * Gets the UNIX timestamp of when the bot list was added to BotBlock as Integer.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return Integer displaying the UNIX time at which the bot list was added to BotBlock.
     */
    public Integer getTimeAdded(@Nonnull String id, @Nonnull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getInt("added");
    }
    
    /**
     * Gets the UNIX timestamp of when the bot list was added to BotBlock as Integer.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return Integer displaying the UNIX time at which the bot list was added to BotBlock.
     */
    public Integer getTimeAdded(@Nonnull String id, @Nonnull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getInt("added");
    }
    
    /**
     * Gets the URL for the bot list's website.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return String containing the URL to the bot list website.
     */
    public String getUrl(@Nonnull String id, @Nonnull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getString("url");
    }
    
    /**
     * Gets the URL for the bot list's website.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return String containing the URL to the bot list website.
     */
    public String getUrl(@Nonnull String id, @Nonnull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getString("url");
    }
    
    /**
     * Returns if the bot list is defunct.
     * <br>A defunct bot list is not displayed on the main site and is also excluded from the POST api.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return {@code true} if the bot list is defunct, {@code false} otherwise.
     */
    public boolean isDefunct(@Nonnull String id, @Nonnull Site site){
        CheckUtil.notEmpty(id, "id");
        JSONObject json = getList(id, site);
        
        return json.getInt("defunct") == 1;
    }
    
    /**
     * Returns if the bot list is defunct.
     * <br>A defunct bot list is not displayed on the main site and is also excluded from the POST api.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return {@code true} if the bot list is defunct, {@code false} otherwise.
     */
    public boolean isDefunct(@Nonnull String id, @Nonnull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        JSONObject json = getList(id, site);
        
        return json.getInt("defunct") == 1;
    }
    
    /**
     * Returns if the bot list is only for Discord bots.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return {@code true} if the list is only for Discord bots, {@code false} otherwise.
     */
    public boolean isDiscordOnly(@Nonnull String id, @Nonnull Site site){
        CheckUtil.notEmpty(id, "name");
        JSONObject json = getList(id, site);
        
        return json.getInt("discord_only") == 1;
    }
    
    /**
     * Returns if the bot list is only for Discord bots.
     *
     * <p>Possible Exceptions being thrown:
     * <br><ul>
     *     <li>{@link java.io.IOException IOException} - When the request wasn't successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException} - When the wrapper hits the rate limit.</li>
     * </ul>
     *
     * @param  id
     *         The id used for the cache.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get information from.
     *
     * @return {@code true} if the list is only for Discord bots, {@code false} otherwise.
     */
    public boolean isDiscordOnly(@Nonnull String id, @Nonnull String site){
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
        // String API field
        
        /**
         * Name of the field for the server count. Example: {@code server_count}
         *
         * @since 5.2.0
         */
        STRING_SERVER_COUNT("api_field"),
        
        /**
         * Name of the field for the shard id. Example: {@code shard_id}
         *
         * @since 5.2.0
         */
        STRING_SHARD_ID("api_shard_id"),
        
        /**
         * Name of the field for the shard count. Example: {@code shard_count}
         *
         * @since 5.2.0
         */
        STRING_SHARD_COUNT("api_shard_count"),
        
        /**
         * Name of the field for the shards. Example: {@code shards}
         *
         * @since 5.2.0
         */
        STRING_SHARDS("api_shards"),
        
        // URL API field
        
        /**
         * URL to GET all listed bots on a bot list.
         *
         * @since 5.2.0
         */
        URL_ALL("api_all"),
        
        /**
         * URL to view the API documentation of the bot list
         *
         * @since 5.2.0
         */
        URL_DOCS("api_docs"),
        
        /**
         * URL to GET information about a single bot listed on the bot list.
         *
         * @since 5.2.0
         */
        URL_GET("api_get"),
        
        /**
         * URL to POST the server count to a bot list.
         *
         * @since 5.2.0
         */
        URL_POST("api_post");
        
        private final String apiField;
        
        ApiField(String apiField){
            this.apiField = apiField;
        }
        
        public String getApiField(){
            return apiField;
        }
        
        
        @Override
        public String toString(){
            return apiField;
        }
    }
}
