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

import org.botblock.javabotblockapi.core.Info;
import org.botblock.javabotblockapi.core.Site;
import org.botblock.javabotblockapi.core.CheckUtil;
import org.botblock.javabotblockapi.core.exceptions.RateLimitedException;
import org.botblock.javabotblockapi.requests.handler.RequestHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to perform GET actions on the <a href="https://botblock.org/api/docs#bots" target="_blank">{@code /api/bots/:id}</a> endpoint.
 *
 * <p>GET requests are cached for 2 minutes unless disabled through either {@link #GetBotAction(boolean, String) GetBotAction(true, String)}
 * or {@link #GetBotAction(boolean, String, String) GetBotAction(true, String, String)}.
 *
 * @since 5.0.0
 */
public class GetBotAction{
    
    private final RequestHandler REQUEST_HANDLER;
    
    private final boolean disableCache;
    
    /**
     * Constructor to get an instance of GetBotAction.
     *
     * <p>Using this constructor will set the following default values ({@code {id}} will be replaced with the provided ID):
     * <br><ul>
     *     <li>Cache: {@code Enabled}</li>
     *     <li>User-Agent: {@code "JavaBotBlockAPI-0000/API_VERSION (Unknown; +https://jbba.dev) DBots/{id}"}</li>
     * </ul>
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided id is empty.</li>
     * </ul>
     *
     * @param id
     *        The id of the bot. This is required for the internal User-Agent.
     */
    public GetBotAction(@Nonnull String id){
        this(false, id);
    }
    
    /**
     * Constructor to get an instance of GetBotAction.
     * <br>This constructor allows you to disable the internal caching, by providing {@code true} as the first argument.
     *
     * <p>Using this constructor will set the following default values ({@code {id}} will be replaced with the provided ID):
     * <br><ul>
     *     <li>User-Agent: {@code "JavaBotBlockAPI-0000/API_VERSION (Unknown; +https://jbba.dev) DBots/{id}"}</li>
     * </ul>
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided id is empty.</li>
     * </ul>
     *
     * @param disableCache
     *        If the cache should be disabled.
     *        <br>{@code true} means the cache is <b>disabled</b>.
     * @param id
     *        The id of the bot. This is required for the internal User-Agent.
     */
    public GetBotAction(boolean disableCache, @Nonnull String id){
        this(disableCache, String.format(
                "JavaBotBlockAPI-0000/%s (Unknown; +https://jbba.dev) DBots/{id}",
                Info.VERSION
        ), id);
    }
    
    /**
     * Constructor to get the instance of GetBotAction.
     * <br>This constructor allows you to disable the internal caching, by providing {@code true} as the first argument
     * and also set a own User-Agent for the requests by providing any String as the second argument.
     *
     * <p>Note that you can provide {@code {id}} inside the userAgent to get it replaced with the provided id.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided userAgent or id is empty.</li>
     * </ul>
     *
     * @param disableCache
     *        If the cache should be disabled.
     *        <br>{@code true} means the cache is <b>disabled</b>.
     * @param userAgent
     *        The Name that should be used as User-Agent.
     * @param id
     *        The id of the bot. This is required for the internal User-Agent.
     */
    public GetBotAction(boolean disableCache, @Nonnull String userAgent, @Nonnull String id){
        CheckUtil.notEmpty(userAgent, "UserAgent");
        CheckUtil.notEmpty(id, "ID");
        
        this.disableCache = disableCache;
        this.REQUEST_HANDLER = new RequestHandler(userAgent.replace("{id}", id));
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
     * <br>With exception of id and list_data are all returned values based on how often one appears.
     * <br>Each entry in list data is unique to what the respective bot list returns.
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * @param  id
     *         The id of the bot to get the information from.
     *
     * @return Possibly-null {@link org.json.JSONObject JSONObject} containing the full information of the bot.
     */
    @Nullable
    public JSONObject getBotInfo(@Nonnull Long id){
        return getBotInfo(String.valueOf(id));
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
     * <br>With exception of id and list_data are all returned values based on how often one appears.
     * <br>Each entry in list data is unique to what the respective bot list returns.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided id is empty.</li>
     * </ul>
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     * 
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the information from.
     *
     * @return Possibly-null {@link org.json.JSONObject JSONObject} containing the full information of the bot.
     */
    @Nullable
    public JSONObject getBotInfo(@Nonnull String id){
        CheckUtil.notEmpty(id, "id");
        
        return REQUEST_HANDLER.performGetBot(id, disableCache);
    }
    
    /**
     * Gets the information from the various bot lists.
     * <br>The returned data is entirely dependant on the bot list itself and is therefore unique.
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     * 
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return Possibly-null {@link org.json.JSONObject JSONObject} containing information from the different bot list.
     */
    @Nullable
    public JSONObject getBotListInfo(@Nonnull Long id){
        return getBotListInfo(String.valueOf(id));
    }
    
    /**
     * Gets the information from the various bot lists.
     * <br>The returned data is entirely dependant on the bot list itself and is therefore unique.
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * A {@link RateLimitedException RatelimitedException} may be thrown
     * from the RequestHandler, if the HTTP request was rate limited.
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the bot list info from.
     *
     * @return Possibly-null {@link org.json.JSONObject JSONObject} containing information from the different bot list.
     */
    @Nullable
    public JSONObject getBotListInfo(@Nonnull String id){
        CheckUtil.notEmpty(id, "id");
        
        JSONObject json = getBotInfo(id);
        if(json == null)
            return null;
        
        return json.getJSONObject("list_data");
    }
    
    /**
     * Gets the information from the specified bot list.
     * <br>The returned data is entirely dependant on the bot list itself and is therefore unique.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.IllegalStateException IllegalStateException} - When the provided Site does not support GET requests.</li>
     * </ul>
     * 
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the bot list info from.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get info from.
     *
     * @return Possibly-null {@link org.json.JSONArray JSONArray} containing the information of the provided bot list.
     */
    @Nullable
    public JSONArray getBotListInfo(@Nonnull Long id, @Nonnull Site site){
        CheckUtil.condition(!site.supportsGet(), site.getName() + " does not support GET requests!");
        
        return getBotListInfo(String.valueOf(id), site);
    }
    
    /**
     * Gets the information from the specified bot list.
     * <br>The returned data is entirely dependant on the bot list itself and is therefore unique.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided site is empty.</li>
     * </ul>
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the bot list info from.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get info from.
     *
     * @return Possibly-null {@link org.json.JSONArray JSONArray} containing the information of the provided bot list.
     */
    @Nullable
    public JSONArray getBotListInfo(@Nonnull Long id, @Nonnull String site){
        CheckUtil.notEmpty(site, "site");
        
        return getBotListInfo(String.valueOf(id), site);
    }
    
    /**
     * Gets the information from the specified bot list.
     * <br>The returned data is entirely dependant on the bot list itself and is therefore unique.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided id is empty.</li>
     *     <li>{@link java.lang.IllegalStateException IllegalStateException} - When the provided Site does not support GET requests.</li>
     * </ul>
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the bot list info from.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get info from.
     *
     * @return Possibly-null {@link org.json.JSONArray JSONArray} containing the information of the provided bot list.
     */
    @Nullable
    public JSONArray getBotListInfo(@Nonnull String id, @Nonnull Site site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.condition(!site.supportsGet(), site.getName() + " does not support GET requests!");
        
        JSONObject json = getBotListInfo(id);
        if(json == null)
            return null;
        
        return json.getJSONArray(site.getName());
    }
    
    /**
     * Gets the information from the specified bot list.
     * <br>The returned data is entirely dependant on the bot list itself and is therefore unique.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided id or site is empty.</li>
     * </ul>
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the bot list info from.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.core.Site site} to get info from.
     *
     * @return Possibly-null {@link org.json.JSONArray JSONArray} containing the information of the provided bot list.
     */
    @Nullable
    public JSONArray getBotListInfo(@Nonnull String id, @Nonnull String site){
        CheckUtil.notEmpty(id, "id");
        CheckUtil.notEmpty(site, "site");
        
        JSONObject json = getBotListInfo(id);
        if(json == null)
            return null;
        
        return json.getJSONArray(site);
    }
    
    /**
     * Gets the discriminator (The 4 numbers after the # in the username) of the bot.
     * <br>The discriminator is based on the most common appearance of it across the bot lists.
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the discriminator from.
     *
     * @return Possibly-null String containing the discriminator of the bot or {@code 0000} if the provided id is invalid.
     *
     * @since  4.2.0
     */
    @Nullable
    public String getDiscriminator(@Nonnull Long id){
        return getDiscriminator(String.valueOf(id));
    }
    
    /**
     * Gets the discriminator (The 4 numbers after the # in the username) of the bot.
     * <br>The discriminator is based on the most common appearance of it.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided id is empty.</li>
     * </ul>
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the discriminator from.
     *
     * @return Possibly-null String containing the discriminator of the bot or {@code 0000} if the provided id is invalid.
     *
     * @since  4.2.0
     */
    @Nullable
    public String getDiscriminator(@Nonnull String id){
        CheckUtil.notEmpty(id, "id");
        
        JSONObject json = getBotInfo(id);
        if(json == null)
            return null;
        
        return json.getString("discriminator");
    }
    
    /**
     * Gets the GitHub link of the bot.
     * <br>The GitHub link is based on the most common appearance of it.
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the GitHub link from.
     *
     * @return Possibly-null or possibly-empty String containing the GitHub link of the bot.
     *
     * @since  4.2.0
     */
    @Nullable
    public String getGitHub(@Nonnull Long id){
        return getGitHub(String.valueOf(id));
    }
    
    /**
     * Gets the GitHub link of the bot.
     * <br>The GitHub link is based on the most common appearance of it.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided id is empty.</li>
     * </ul>
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the GitHub link from.
     *
     * @return Possibly-null or possibly-empty String containing the GitHub link of the bot.
     *
     * @since  4.2.0
     */
    @Nullable
    public String getGitHub(@Nonnull String id){
        CheckUtil.notEmpty(id, "id");
        
        JSONObject json = getBotInfo(id);
        if(json == null)
            return null;
        
        return json.getString("github");
    }
    
    /**
     * Gets the currently used library of the bot.
     * <br>The library is based on the most common appearance of it.
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the library from.
     *
     * @return Possibly-null or possibly-empty String containing the library of the bot.
     *
     * @since  4.2.0
     */
    @Nullable
    public String getLibrary(@Nonnull Long id){
        return getLibrary(String.valueOf(id));
    }
    
    /**
     * Gets the currently used library of the bot.
     * <br>The library is based on the most common appearance of it.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided id is empty.</li>
     * </ul>
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the library from.
     *
     * @return Possibly-null or possibly-empty String containing the library of the bot.
     *
     * @since  4.2.0
     */
    @Nullable
    public String getLibrary(@Nonnull String id){
        CheckUtil.notEmpty(id, "id");
        
        JSONObject json = getBotInfo(id);
        if(json == null)
            return null;
        
        return json.getString("library");
    }
    
    /**
     * Gets the name of the bot.
     * <br>The name is based on the most common appearance of it.
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the name from.
     *
     * @return Possibly-null String containing the name of the bot or {@code Unknown} if the provided id is invalid.
     *
     * @since  4.2.0
     */
    @Nullable
    public String getName(@Nonnull Long id){
        return getName(String.valueOf(id));
    }
    
    /**
     * Gets the name of the bot.
     * <br>The name is based on the most common appearance of it.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided id is empty.</li>
     * </ul>
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the name from.
     *
     * @return Possibly-null String containing the name of the bot or {@code Unknown} if the provided id is invalid.
     *
     * @since  4.2.0
     */
    @Nullable
    public String getName(@Nonnull String id){
        CheckUtil.notEmpty(id, "id");
        
        JSONObject json = getBotInfo(id);
        if(json == null)
            return null;
        
        return json.getString("username");
    }
    
    /**
     * Gets the OAuth invite link of a bot.
     * <br>The OAuth invite is used to add a bot to a Discord server.
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the OAuth link from.
     *
     * @return Possibly-null or possibly-empty String containing the OAuth link for the bot.
     *
     * @since  5.1.13
     */
    @Nullable
    public String getOAuthInvite(@Nonnull Long id){
        return getOAuthInvite(String.valueOf(id));
    }
    
    /**
     * Gets the OAuth invite link of a bot.
     * <br>The OAuth invite is used to add a bot to a Discord server.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided id is empty.</li>
     * </ul>
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the OAuth link from.
     *
     * @return Possibly-null or possibly-empty String containing the OAuth link for the bot.
     *
     * @since  5.1.13
     */
    @Nullable
    public String getOAuthInvite(@Nonnull String id){
        CheckUtil.notEmpty(id, "id");
        
        JSONObject json = getBotInfo(id);
        if(json == null)
            return null;
        
        return json.getString("invite");
    }
    
    /**
     * Gets an ArrayList with the owner ids of the bot.
     * <br>The IDs listed are based on how often they appear on the different bot lists.
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the Owners from.
     *
     * @return Possibly-empty ArrayList containing the owners of the bot.
     */
    @Nullable
    public List<String> getOwners(@Nonnull Long id){
        return getOwners(String.valueOf(id));
    }
    
    /**
     * Gets an ArrayList with the owner ids of the bot.
     * <br>The IDs listed are based on how often they appear on the different bot lists.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided id is empty.</li>
     * </ul>
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the Owners from.
     *
     * @return Possibly-empty ArrayList containing the owners of the bot.
     */
    @Nullable
    public List<String> getOwners(@Nonnull String id){
        CheckUtil.notEmpty(id, "id");
        
        JSONObject json = getBotInfo(id);
        if(json == null)
            return new ArrayList<>();
        
        List<String> owners = new ArrayList<>();
        for(int i = 0; i < json.getJSONArray("owners").length(); i++)
            owners.add(json.getJSONArray("owners").getString(i));
        
        return owners;
    }
    
    /**
     * Gets the prefix of the bot.
     * <br>The prefix is based on the most common appearance of it.
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the prefix from.
     *
     * @return Possibly-null or possibly-empty String containing the prefix of the bot.
     *
     * @since  4.2.0
     */
    @Nullable
    public String getPrefix(@Nonnull Long id){
        return getPrefix(String.valueOf(id));
    }
    
    /**
     * Gets the prefix of the bot.
     * <br>The prefix is based on the most common appearance of it.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided id is empty.</li>
     * </ul>
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the prefix from.
     *
     * @return Possibly-null or possibly-empty String containing the prefix of the bot.
     *
     * @since  v4.2.0
     */
    @Nullable
    public String getPrefix(@Nonnull String id){
        CheckUtil.notEmpty(id, "id");
        
        JSONObject json = getBotInfo(id);
        if(json == null)
            return null;
        
        return json.getString("prefix");
    }
    
    /**
     * Gets the server count of the bot.
     * <br>The server count is based on the most common appearance of it.
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the server count from.
     *
     * @return Possibly-null Integer containing the server count for the bot.
     */
    @Nullable
    public Integer getServerCount(@Nonnull Long id){
        return getServerCount(String.valueOf(id));
    }
    
    /**
     * Gets the server count of the bot.
     * <br>The server count is based on the most common appearance of it.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided id is empty.</li>
     * </ul>
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the server count from.
     *
     * @return Possibly-null Integer containing the server count for the bot.
     */
    @Nullable
    public Integer getServerCount(@Nonnull String id){
        CheckUtil.notEmpty(id, "id");
        
        JSONObject json = getBotInfo(id);
        if(json == null)
            return null;
        
        return json.getInt("server_count");
    }
    
    /**
     * Gets the support link (i.e. Discord invite) from the bot.
     * <br>The link is based on the most common appearance of it.
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the support link from.
     *
     * @return Possibly-null or possibly-empty String containing the support link.
     */
    @Nullable
    public String getSupportLink(@Nonnull Long id){
        return getSupportLink(String.valueOf(id));
    }
    
    /**
     * Gets the support link (i.e. Discord invite) from the bot.
     * <br>The link is based on the most common appearance of it.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided id is empty.</li>
     * </ul>
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the support link from.
     *
     * @return Possibly-null or possibly-empty String containing the support link.
     */
    @Nullable
    public String getSupportLink(@Nonnull String id){
        CheckUtil.notEmpty(id, "id");
        
        JSONObject json = getBotInfo(id);
        if(json == null)
            return null;
        
        return json.getString("support");
    }
    
    /**
     * Gets the website of the bot.
     * <br>The website is based on the most common appearance of it.
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the website from.
     *
     * @return Possibly-null or possibly-empty String containing the bot's website.
     *
     * @since  v4.2.0
     */
    @Nullable
    public String getWebsite(@Nonnull Long id){
        return getWebsite(String.valueOf(id));
    }
    
    /**
     * Gets the website of the bot.
     * <br>The website is based on the most common appearance of it.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided id is empty.</li>
     * </ul>
     *
     * <p>Following Exceptions can be thrown from the HTTP request:
     * <ul>
     *     <li>{@link java.io.IOException IOException} - When the request was non-successful.</li>
     *     <li>{@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException} - When the request got rate limited.</li>
     * </ul>
     *
     * <p>This method may also return {@code null} if the request wasn't successful.
     *
     * @param  id
     *         The id of the bot to get the website from.
     *
     * @return Possibly-null or possibly-empty String containing the bot's website.
     *
     * @since  v4.2.0
     */
    @Nullable
    public String getWebsite(@Nonnull String id){
        CheckUtil.notEmpty(id, "id");
        
        JSONObject json = getBotInfo(id);
        if(json == null)
            return null;
        
        return json.getString("website");
    }
}
