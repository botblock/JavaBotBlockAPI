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


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.json.JSONObject;

/**
 * Class to perform GET actions with.
 */
public class GetAction{
    
    private final RequestHandler REQUEST_HANDLER = new RequestHandler();
    private boolean disableCache;
    
    /**
     * Empty constructor to get the instance of GetAction.
     */
    public GetAction(){
        this.disableCache = false;
    }
    
    /**
     * Constructor to get the instance of GetAction.
     * 
     * @param disableCache
     *        If the caching should be disabled. {@code true} means the cache will be <b>disabled</b>.
     *        <br>It's recommended to NOT disable caching.
     */
    public GetAction(boolean disableCache){
        this.disableCache = disableCache;
    }
    
    /**
     * Gets the full information of a bot.
     * <br>This may return null.
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
     * <br><b>{@code {"data"}}</b> depends on what the bot list returns.
     * 
     * @param  jda
     *         The {@link net.dv8tion.jda.api.JDA JDA instance} to use.
     *         
     * @return A possibly-null JSONObject containing the bots information.
     */
    public JSONObject getAll(JDA jda){
        return REQUEST_HANDLER.performGetBot(jda.getSelfUser().getId(), disableCache);
    }
    
    /**
     * Gets the full information of a bot.
     * <br>This may return null.
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
     * <br><b>{@code {"data"}}</b> depends on what the bot list returns.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return A possibly-null JSONObject containing the bots information.
     */
    public JSONObject getAll(Long id){
        return REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
    }
    
    /**
     * Gets the full information of a bot.
     * <br>This may return null.
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
     * <br><b>{@code {"data"}}</b> depends on what the bot list returns.
     *
     * @param  shardManager
     *         The {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance} to use.
     *
     * @return A possibly-null JSONObject containing the bots information.
     */
    public JSONObject getAll(ShardManager shardManager){
        JDA jda = shardManager.getShardById(0);
        if(jda == null)
            return null;
        
        return REQUEST_HANDLER.performGetBot(jda.getSelfUser().getId(), disableCache);
    }
    
    /**
     * Gets the full information of a bot.
     * <br>This may return null.
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
     * <br><b>{@code {"data"}}</b> depends on what the bot list returns.
     *
     * @param  id
     *         The bots id to use.
     *
     * @return A possibly-null JSONObject containing the bots information.
     */
    public JSONObject getAll(String id){
        return REQUEST_HANDLER.performGetBot(id, disableCache);
    }
}
