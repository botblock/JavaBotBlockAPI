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

import com.andre601.javabotblockapi.BotBlockAPI;
import com.andre601.javabotblockapi.exceptions.RatelimitedException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostAction{
    
    private boolean disableCache;
    private final RequestHandler REQUEST_HANDLER = new RequestHandler();
    
    public PostAction(){
        this.disableCache = false;
    }
    
    public PostAction(boolean disableCache){
        this.disableCache = disableCache;
    }
    
    /**
     * Posts the guild count provided through the {@link net.dv8tion.jda.api.JDA JDA instance}.
     * <br><b>It's recommended to use {@link #postGuilds(ShardManager, BotBlockAPI) postGuilds(ShardManager, BotBlockAPI} 
     * if you're using a sharded bot.</b>
     * 
     * <p>When the amount of shards a bot has is bigger than one will shard_id and shard_count be added.
     * 
     * @param  jda
     *         The {@link net.dv8tion.jda.api.JDA JDA instance}.
     * @param  botBlockAPI
     *         The {@link com.andre601.javabotblockapi.BotBlockAPI BotBlockAPI instance}.
     *         
     * @throws IOException
     *         When the post request couldn't be performed.
     * @throws RatelimitedException
     *         When we exceed the rate-limit of the BotBlock API.
     */
    public void postGuilds(@NotNull JDA jda, @NotNull BotBlockAPI botBlockAPI) throws IOException, RatelimitedException{
        JSONObject json = new JSONObject()
                .put("server_count", jda.getGuilds().size())
                .put("bot_id", jda.getSelfUser().getId());
        
        if(jda.getShardInfo().getShardTotal() > 1)
            json.put("shard_id", jda.getShardInfo().getShardId())
                    .put("shard_count", jda.getShardInfo().getShardTotal());
        
        botBlockAPI.getAuthTokens().forEach(json::put);
        
        REQUEST_HANDLER.performPOST(json);
    }
    
    /**
     * Posts the guild count with the provided bot id.
     * 
     * @param  botId
     *         The ID of the bot.
     * @param  guilds
     *         The guild count.
     * @param  botBlockAPI
     *         The {@link com.andre601.javabotblockapi.BotBlockAPI BotBlockAPI instance}.
     *
     * @throws IOException
     *         When the post request couldn't be performed.
     * @throws RatelimitedException
     *         When we exceed the rate-limit of the BotBlock API.
     */
    public void postGuilds(Long botId, int guilds, @NotNull BotBlockAPI botBlockAPI) throws IOException, RatelimitedException{
        postGuilds(Long.toString(botId), guilds, botBlockAPI);
    }
    
    /**
     * Posts the guild count from the provided {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance}.
     * <br>The guild count of each shard will be added as an JSONArray.
     * 
     * @param  shardManager
     *         The {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance}.
     * @param  botBlockAPI
     *         The {@link com.andre601.javabotblockapi.BotBlockAPI BotBlockAPI instance}.
     *
     * @throws IOException
     *         When the post request couldn't be performed.
     * @throws RatelimitedException
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
        botBlockAPI.getAuthTokens().forEach(json::put);
        
        REQUEST_HANDLER.performPOST(json);
    }
    
    /**
     * Posts the guild count with the provided bot id.
     *
     * @param  botId
     *         The ID of the bot.
     * @param  guilds
     *         The guild count.
     * @param  botBlockAPI
     *         The {@link com.andre601.javabotblockapi.BotBlockAPI BotBlockAPI instance}.
     *
     * @throws IOException
     *         When the post request couldn't be performed.
     * @throws RatelimitedException
     *         When we exceed the rate-limit of the BotBlock API.
     */
    public void postGuilds(@NotNull String botId, int guilds, @NotNull BotBlockAPI botBlockAPI) throws IOException, RatelimitedException{
        JSONObject json = new JSONObject()
                .put("server_count", guilds)
                .put("bot_id", botId);
        
        botBlockAPI.getAuthTokens().forEach(json::put);
        
        REQUEST_HANDLER.performPOST(json);
    }
    
}
