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
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Interface used to perform various GET actions towards the <a href="https://botblock.org/api/docs#bots" target="_blank">{@code /api/bots/:id}</a> endpoint.
 * 
 * <p>All GET requests are cached for 2 minutes, to prevent rate limits.
 */
public interface GetBotAction{
    
    /**
     * Gives the complete bot info returned by BotBlock.
     * 
     * <p>The returned {@link org.json.JSONObject JSONObject} may look like this:
     * <br><pre><code>
     * {
     *     "id": "123456789012345678",
     *     "username": "MyBot",
     *     "discriminator": "1234",
     *     "owners": [
     *         "234567890123456789"
     *     ],
     *     "server_count": 100,
     *     "invite": {@literal "https://discordapp.com/oauth2/authorize?client_id=123456789012345678&scope=bot"},
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
     * <br>With exception of id and list_data are all shown values based on the most common appearance of it.
     * <br>The information returned in list_data is unique per bot list.
     * 
     * @param  id
     *         The bot id to get information from.
     *         
     * @return {@link org.json.JSONObject JSONObject} containing the bot information.
     */
    JSONObject getBotInfo(@NotNull Long id);
    
    /**
     *
     * Gives the complete bot info returned by BotBlock.
     *
     * <p>The returned {@link org.json.JSONObject JSONObject} may look like this:
     * <br><pre><code>
     * {
     *     "id": "123456789012345678",
     *     "username": "MyBot",
     *     "discriminator": "1234",
     *     "owners": [
     *         "234567890123456789"
     *     ],
     *     "server_count": 100,
     *     "invite": {@literal "https://discordapp.com/oauth2/authorize?client_id=123456789012345678&scope=bot"},
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
     * <br>With exception of id and list_data are all shown values based on the most common appearance of it.
     * <br>The information returned in list_data is unique per bot list.
     * 
     * @param  id
     *         The bot id to get information from.
     *         
     * @return {@link org.json.JSONObject JSONObject} containing the bot information.
     */
    JSONObject getBotInfo(@NotNull String id);
    
    /**
     * Gives the information from each bot list.
     * <br>Note that the JSON of each list in the returned {@link org.json.JSONObject JSONObject} depends on what the list
     * returns and is therefore unique.
     * 
     * @param  id
     *         The bot id to get bot list information from.
     *         
     * @return {@link org.json.JSONObject JSONObject} containing the bot information from the different bot lists.
     */
    JSONObject getBotListInfo(@NotNull Long id);
    
    /**
     * Gives the information from each bot list.
     * <br>Note that the JSON of each list in the returned {@link org.json.JSONObject JSONObject} depends on what the list
     * returns and is therefore unique.
     *
     * @param  id
     *         The bot id to get bot list information from.
     *
     * @return {@link org.json.JSONObject JSONObject} containing the bot information from the different bot lists.
     */
    JSONObject getBotListInfo(@NotNull String id);
    
    /**
     * Gives the information from a specific bot list.
     * <br>Note that the JSON of the list in the returned {@link org.json.JSONArray JSONArray} depends on what the list
     * returns and is therefore unique.
     *
     * @param  id
     *         The bot id to get bot list information from.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site Site} to get information from.
     *
     * @return {@link org.json.JSONArray JSONArray} containing the bot information from the bot list.
     */
    JSONArray getBotListInfo(@NotNull Long id, @NotNull Site site);
    
    /**
     * Gives the information from a specific bot list.
     * <br>Note that the JSON of the list in the returned {@link org.json.JSONArray JSONArray} depends on what the list
     * returns and is therefore unique.
     *
     * @param  id
     *         The bot id to get bot list information from.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site Site} to get information from.
     *
     * @return {@link org.json.JSONArray JSONArray} containing the bot information from the bot list.
     */
    JSONArray getBotListInfo(@NotNull Long id, @NotNull String site);
    
    /**
     * Gives the information from a specific bot list.
     * <br>Note that the JSON of the list in the returned {@link org.json.JSONArray JSONArray} depends on what the list
     * returns and is therefore unique.
     *
     * @param  id
     *         The bot id to get bot list information from.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site Site} to get information from.
     *
     * @return {@link org.json.JSONArray JSONArray} containing the bot information from the bot list.
     */
    JSONArray getBotListInfo(@NotNull String id, @NotNull Site site);
    
    /**
     * Gives the information from a specific bot list.
     * <br>Note that the JSON of the list in the returned {@link org.json.JSONArray JSONArray} depends on what the list
     * returns and is therefore unique.
     *
     * @param  id
     *         The bot id to get bot list information from.
     * @param  site
     *         The {@link org.botblock.javabotblockapi.Site Site} to get information from.
     *
     * @return {@link org.json.JSONArray JSONArray} containing the bot information from the bot list.
     */
    JSONArray getBotListInfo(@NotNull String id, @NotNull String site);
    
    /**
     * Gives the discriminator of the bot.
     * <br>The discriminator is the for digit number in a Tag (example: MyBot#<b>1234</b>).
     * 
     * @param  id
     *         The id of the bot to get the discriminator from.
     *         
     * @return String containing the discriminator, or {@code 0000} when the provided id is invalid.
     *
     * @since  4.2.0
     */
    String getDiscriminator(@NotNull Long id);
    
    /**
     * Gives the discriminator of the bot.
     * <br>The discriminator is the for digit number in a Tag (example: MyBot#<b>1234</b>).
     *
     * @param  id
     *         The id of the bot to get the discriminator from.
     *
     * @return String containing the discriminator, or {@code 0000} when the provided id is invalid.
     *
     * @since  4.2.0
     */
    String getDiscriminator(@NotNull String id);
    
    /**
     * Gives the URL to the GitHub-repository of the bot, when available.
     * 
     * @param  id
     *         The id of the bot to get the GitHub URL from.
     *         
     * @return Possibly-empty String containing the URL to the GitHub repository of the bot.
     *
     * @since  4.2.0
     */
    String getGitHub(@NotNull Long id);
    
    /**
     * Gives the URL to the GitHub-repository of the bot, when available.
     *
     * @param  id
     *         The id of the bot to get the GitHub URL from.
     *
     * @return Possibly-empty String containing the URL to the GitHub repository of the bot.
     *
     * @since  4.2.0
     */
    String getGitHub(@NotNull String id);
    
    /**
     * Gives the OAuth invite for the bot.
     * <br>The OAuth invite is used to invite the bot to a Discord server.
     * 
     * @param  id
     *         The id of the bot to get the OAuth link from.
     *         
     * @return Possibly-empty String containing the OAuth invite for the bot.
     */
    String getInvite(@NotNull Long id);
    
    /**
     * Gives the OAuth invite for the bot.
     * <br>The OAuth invite is used to invite the bot to a Discord server.
     *
     * @param  id
     *         The id of the bot to get the OAuth link from.
     *
     * @return Possibly-empty String containing the OAuth invite for the bot.
     */
    String getInvite(@NotNull String id);
    
    /**
     * Gives the Library, which is used by the bot, if available.
     * 
     * @param  id
     *         The id of the bot to get the Library from.
     *         
     * @return Possibly-empty String containing the used library of the bot.
     *
     * @since  4.2.0
     */
    String getLibrary(@NotNull Long id);
    
    /**
     * Gives the Library, which is used by the bot, if available.
     *
     * @param  id
     *         The id of the bot to get the Library from.
     *
     * @return Possibly-empty String containing the used library of the bot.
     *
     * @since  4.2.0
     */
    String getLibrary(@NotNull String id);
    
    /**
     * Gives the name of the bot.
     * 
     * @param  id
     *         The id of the bot to get the name from.
     *         
     * @return The name of the bot, or {@code Unknown} when the provided id is invalid.
     *
     * @since  4.2.0
     */
    String getName(@NotNull Long id);
    
    /**
     * Gives the name of the bot.
     *
     * @param  id
     *         The id of the bot to get the name from.
     *
     * @return The name of the bot, or {@code Unknown} when the provided id is invalid.
     *
     * @since  4.2.0
     */
    String getName(@NotNull String id);
    
    /**
     * Gives a {@literal List<String>} containing the ids of the bot owners.
     *
     * @param  id
     *         The id of the bot to get the owners from.
     *
     * @return Possibly-empty {@literal List<String>} containing the ids of the bot owners.
     */
    List<String> getOwners(@NotNull Long id);
    
    /**
     * Gives a {@literal List<String>} containing the ids of the bot owners.
     *
     * @param  id
     *         The id of the bot to get the owners from.
     *
     * @return Possibly-empty {@literal List<String>} containing the ids of the bot owners.
     */
    List<String> getOwners(@NotNull String id);
    
    /**
     * Gives the prefix used by the bot for commands.
     * 
     * @param  id
     *         The id of the bot to get the prefix from.
     *         
     * @return Possibly-empty String containing the prefix of the bot.
     *
     * @since  4.2.0
     */
    String getPrefix(@NotNull Long id);
    
    /**
     * Gives the prefix used by the bot for commands.
     *
     * @param  id
     *         The id of the bot to get the prefix from.
     *
     * @return Possibly-empty String containing the prefix of the bot.
     *
     * @since  4.2.0
     */
    String getPrefix(@NotNull String id);
    
    /**
     * Gives the current server count a bot has.
     * 
     * @param  id
     *         The id of the bot to get the server count from.
     *         
     * @return Possibly-null Integer containing the server count of the bot.
     */
    Integer getServerCount(@NotNull Long id);
    
    /**
     * Gives the current server count a bot has.
     *
     * @param  id
     *         The id of the bot to get the server count from.
     *
     * @return Possibly-null Integer containing the server count of the bot.
     */
    Integer getServerCount(@NotNull String id);
    
    /**
     * Gives the support link (i.e. Discord invite. Not the {@link #getInvite(Long) OAuth link}) of a bot.
     * 
     * @param  id
     *         The id of the bot to get the support link from.
     *         
     * @return Possibly-empty String containing the support URL.
     */
    String getSupportLink(@NotNull Long id);
    
    /**
     * Gives the support link (i.e. Discord invite. Not the {@link #getInvite(Long) OAuth link}) of a bot.
     *
     * @param  id
     *         The id of the bot to get the support link from.
     *
     * @return Possibly-empty String containing the support URL.
     */
    String getSupportLink(@NotNull String id);
    
    /**
     * Gives the website URL of the bot.
     * 
     * @param  id
     *         The id of the bot to get the website URL from.
     *         
     * @return Possibly-empty String containing the website URL of the bot.
     *
     * @since  4.2.0
     */
    String getWebsite(@NotNull Long id);
    
    /**
     * Gives the website URL of the bot.
     *
     * @param  id
     *         The id of the bot to get the website URL from.
     *
     * @return Possibly-empty String containing the website URL of the bot.
     * 
     * @since  4.2.0
     */
    String getWebsite(@NotNull String id);
}
