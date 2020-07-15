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

package org.botblock.javabotblockapi.jda;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.botblock.javabotblockapi.core.BotBlockAPI;
import org.botblock.javabotblockapi.core.exceptions.RatelimitedException;
import org.botblock.javabotblockapi.core.requests.CheckUtil;
import org.botblock.javabotblockapi.requests.handler.RequestHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class is used to perform POST requests towards the BotBlock API using the Java Discord Library (JDA).
 * <br>It allows you to just provide either an {@link net.dv8tion.jda.api.JDA JDA} or a 
 * {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager} instance and all other stuff such as providing
 * the amount of shards, the Guild count, etc. are handled automatically.
 */
public class PostAction{
    private final RequestHandler requestHandler;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    
    /**
     * Creates a new instance of this class.
     * <br>This will set the UserAgent used for POST requests to {@code <botname>-<discriminator>/<api-version> (JDA) DBots/<id>}
     * using the provided {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance}.
     * 
     * @param shardManager
     *        The {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance} used to set the UserAgent.
     */
    public PostAction(@Nonnull ShardManager shardManager){
        this(Objects.requireNonNull(shardManager.getShardById(0)));
    }
    
    /**
     * Creates a new instance of this class.
     * <br>This will set the UserAgent used for POST requests to {@code <botname>-<discriminator>/<api-version> (JDA) DBots/<id>}
     * using the provided {@link net.dv8tion.jda.api.JDA JDA instance}.
     *
     * @param jda
     *        The {@link net.dv8tion.jda.api.JDA JDA instance} used to set the UserAgent.
     */
    public PostAction(@Nonnull JDA jda){
        requestHandler = new RequestHandler(String.format(
                "%s-%s/API_VERSION (JDA) DBots/%s",
                jda.getSelfUser().getName(),
                jda.getSelfUser().getDiscriminator(),
                jda.getSelfUser().getId()
        ));
    }
    
    /**
     * Disables the automatic posting of Stats.
     * <br>This essentially just performs a {@link java.util.concurrent.ScheduledExecutorService#shutdown() ScheduledExecutorService.shutdown()}.
     * 
     * @see java.util.concurrent.ScheduledExecutorService#shutdown() 
     */
    public void disableAutoPost(){
        scheduler.shutdown();
    }
    
    /**
     * Starts a {@link java.util.concurrent.ScheduledExecutorService#scheduleAtFixedRate(Runnable, long, long, TimeUnit) scheduleAtFixedRate}
     * task, which will post the statistics of the provided {@link net.dv8tion.jda.api.JDA JDA instance} every n minutes.
     * 
     * <p>If the post can't be performed - either by getting a {@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException}
     * or by getting an {@link java.io.IOException IOException} - will the exception be catched and the stacktrace printed.
     * 
     * <p>The scheduler will wait an initial delay of 1 minute and then performs a task every n minutes, where n is the
     * time set in {@link org.botblock.javabotblockapi.core.BotBlockAPI.Builder#setUpdateDelay(Integer) BotBlockAPI.Builder.setUpdateDelay(Integer)}
     * (default is 30 minutes).
     * 
     * <p>If you have a sharded bot is it recommendet to use {@link #enableAutoPost(ShardManager, BotBlockAPI) enableAutoPost(ShardManager, BotBlockAPI)} instead.
     * 
     * @param jda
     *        The {@link net.dv8tion.jda.api.JDA JDA instance} to post stats from.
     * @param botBlockAPI
     *        The {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlockAPI instance} to use.
     */
    public void enableAutoPost(@Nonnull JDA jda, @Nonnull BotBlockAPI botBlockAPI){
        scheduler.scheduleAtFixedRate(() -> {
            try{
                postGuilds(jda, botBlockAPI);
            }catch(IOException | RatelimitedException ex){
                ex.printStackTrace();
            }
        }, 1, botBlockAPI.getUpdateDelay(), TimeUnit.MINUTES);
    }
    
    /**
     * Starts a {@link java.util.concurrent.ScheduledExecutorService#scheduleAtFixedRate(Runnable, long, long, TimeUnit) scheduleAtFixedRate}
     * task, which will post the statistics of the provided {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance} every n minutes.
     *
     * <p>If the post can't be performed - either by getting a {@link org.botblock.javabotblockapi.core.exceptions.RatelimitedException RatelimitedException}
     * or by getting an {@link java.io.IOException IOException} - will the exception be catched and the stacktrace printed.
     *
     * <p>The scheduler will wait an initial delay of 1 minute and then performs a task every n minutes, where n is the
     * time set in {@link org.botblock.javabotblockapi.core.BotBlockAPI.Builder#setUpdateDelay(Integer) BotBlockAPI.Builder.setUpdateDelay(Integer)}
     * (default is 30 minutes).
     * 
     * @param shardManager
     *        The {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance} to post stats from.
     * @param botBlockAPI
     *        The {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlockAPI instance} to use.
     */
    public void enableAutoPost(@Nonnull ShardManager shardManager, @Nonnull BotBlockAPI botBlockAPI){
        scheduler.scheduleAtFixedRate(() -> {
            try{
                postGuilds(shardManager, botBlockAPI);
            }catch(IOException | RatelimitedException ex){
                ex.printStackTrace();
            }
        }, botBlockAPI.getUpdateDelay(), botBlockAPI.getUpdateDelay(), TimeUnit.MINUTES);
    }
    
    /**
     * Performs a POST request towards the BotBlock API using the information from the provided
     * {@link net.dv8tion.jda.api.JDA JDA} and {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlockAPI} instances.
     * 
     * <p>If the provided JDA instance also is part of a sharded Bot (Amount of shards is larger than 1) will the request
     * also include {@code shard_id} and {@code shard_count}
     * 
     * @param  jda
     *         The {@link net.dv8tion.jda.api.JDA JDA instance} to post stats from.
     * @param  botBlockAPI
     *         The {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlockAPI instance} to use.
     *         
     * @throws java.io.IOException
     *         When the POST request wasn't successfull.
     * @throws org.botblock.javabotblockapi.core.exceptions.RatelimitedException
     *         When we get rate limited by the BotBlock API (returns error code 429).
     */
    public void postGuilds(@Nonnull JDA jda, @Nonnull BotBlockAPI botBlockAPI) throws IOException, RatelimitedException{
        JSONObject json = new JSONObject()
                .put("server_count", jda.getGuildCache().size())
                .put("bot_id", jda.getSelfUser().getId());
        
        if(jda.getShardInfo().getShardTotal() > 1)
            json.put("shard_id", jda.getShardInfo().getShardId())
                .put("shard_count", jda.getShardInfo().getShardTotal());
        
        botBlockAPI.getTokens().forEach(json::put);
        
        requestHandler.performPOST(json, botBlockAPI.getTokens().size());
    }
    
    /**
     * Performs a POST request towards the BotBlock API using the information from the provided
     * {@link net.dv8tion.jda.api.JDA JDA} and {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlockAPI} instances.
     *
     * <p>If the provided JDA instance also is part of a sharded Bot (Amount of shards is larger than 1) will the request
     * also include {@code shard_id} and {@code shard_count}
     *
     * @param  shardManager
     *         The {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance} to post stats from.
     * @param  botBlockAPI
     *         The {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlockAPI instance} to use.
     *
     * @throws java.lang.IllegalStateException
     *         When the first shard (id 0) of the provided ShardManager is null.
     * @throws java.io.IOException
     *         When the POST request wasn't successfull.
     * @throws org.botblock.javabotblockapi.core.exceptions.RatelimitedException
     *         When we get rate limited by the BotBlock API (returns error code 429).
     */
    public void postGuilds(@Nonnull ShardManager shardManager, @Nonnull BotBlockAPI botBlockAPI) throws IOException, RatelimitedException{
        JDA shard = shardManager.getShardById(0);
        CheckUtil.condition(shard == null, "Received invalid/null Shard (Shard ID: 0)");
        
        JSONObject json = new JSONObject()
                .put("server_count", shardManager.getGuildCache().size())
                .put("bot_id", shard.getSelfUser().getId())
                .put("shard_count", shardManager.getShardCache().size());
    
        List<Long> shards = new ArrayList<>();
        for(JDA jda : shardManager.getShardCache())
            shards.add(jda.getGuildCache().size());
        
        json.put("shards", new JSONArray(Arrays.deepToString(shards.toArray())));
        botBlockAPI.getTokens().forEach(json::put);
        
        requestHandler.performPOST(json, botBlockAPI.getTokens().size());
    }
}
