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
import org.botblock.javabotblockapi.core.Info;
import org.botblock.javabotblockapi.core.exceptions.RateLimitedException;
import org.botblock.javabotblockapi.core.CheckUtil;
import org.botblock.javabotblockapi.requests.handler.RequestHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class used to perform POST requests towards the <a href="https://botblock.org/api/docs#count" target="_blank">/api/count</a> 
 * endpoint of BotBlock using the JDA Library.
 *
 * <p>The class offers options to post either {@link #postGuilds(JDA, BotBlockAPI) manually} or
 * {@link #enableAutoPost(JDA, BotBlockAPI) automatically}.
 * <br>It also allows you to choose, if you want to use a {@link net.dv8tion.jda.api.JDA JDA instance} or a
 * {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance}.
 * 
 * <p>If you want to post without using either instance, use the {@link org.botblock.javabotblockapi.requests.PostAction normal PostAction}.
 */
public class PostAction{
    private final Logger LOG = LoggerFactory.getLogger("JavaBotBlockAPI - PostAction (JDA)");
    
    private final RequestHandler requestHandler;
    private final ScheduledExecutorService scheduler;
    
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
        this.requestHandler = new RequestHandler(String.format(
                "%s-%s/%s (JDA) DBots/%s",
                jda.getSelfUser().getName(),
                jda.getSelfUser().getDiscriminator(),
                Info.VERSION,
                jda.getSelfUser().getId()
        ));
        this.scheduler = requestHandler.getScheduler();
    }
    
    /**
     * Disables the automatic posting of Stats.
     * <br>This essentially just performs a {@link java.util.concurrent.ScheduledExecutorService#shutdown() ScheduledExecutorService.shutdown()}
     * by calling the {@link #disableAutoPost(BotBlockAPI) disableAutoPost(null)} method.
     *
     * <p>Note that using this method will NOT make the scheduler wait for previously scheduled tasks to complete.
     * <br>If you want to wait for the tasks to complete use {@link #disableAutoPost(BotBlockAPI) disableAutoPost(BotBlockAPI)} or
     * {@link #disableAutoPost(long, TimeUnit) disableAutoPost(long, TimeUnit)} instead.
     *
     * @see java.util.concurrent.ScheduledExecutorService#shutdown()
     */
    public void disableAutoPost(){
        disableAutoPost(null);
    }
    
    /**
     * Disables the automatic posting of Stats.
     * <br>Unlike {@link #disableAutoPost() disableAutoPost()} can you make the scheduler wait for all scheduled tasks to 
     * finish, or to time out after n minutes by providing the {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlock instance}.
     *
     * <p>Passing null as argument will just perform a {@link java.util.concurrent.ScheduledExecutorService#shutdown() ScheduledExecutorService.shutdown()}
     * similar to what the disableAutoPost() method does.
     *
     * <p>If you want to use a different delay than what you've set in the BotBlockAPI instance, can you use 
     * {@link #disableAutoPost(long, TimeUnit) disableAutoPost(long, TimeUnit)} instead.
     *
     * <p>This method may throw a {@link java.lang.InterruptedException InterruptedException} in the terminal.
     *
     * @param botBlockAPI
     *        The {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlockAPI instance} or null to just perform a shutdown.
     *
     * @since 6.0.0
     * 
     * @see java.util.concurrent.ScheduledExecutorService#shutdown()
     * @see java.util.concurrent.ScheduledExecutorService#awaitTermination(long, TimeUnit)
     */
    public void disableAutoPost(@Nullable BotBlockAPI botBlockAPI){
        if(botBlockAPI != null){
            disableAutoPost(botBlockAPI.getUpdateDelay(), TimeUnit.MINUTES);
            return;
        }
        
        scheduler.shutdown();
    }
    
    /**
     * Disables the automatic posting of Stats.
     * <br>Unlike {@link #disableAutoPost() disableAutoPost()} can you make the scheduler wait for all scheduled tasks to 
     * finish, or to time out after a specified time frame.
     *
     * <p>This method may throw a {@link java.lang.InterruptedException InterruptedException} in the terminal.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.IllegalStateException IllegalStateException} - When the provided time param is 0 or lower.</li>
     * </ul>
     * 
     * @param time
     *        The amount of time to wait for scheduled executions to finish before the Scheduler would time out.
     * @param timeUnit
     *        The {@link java.util.concurrent.TimeUnit TimeUnit} to use.
     *
     * @since 6.0.0
     *
     * @see java.util.concurrent.ScheduledExecutorService#awaitTermination(long, TimeUnit)
     */
    public void disableAutoPost(long time, @Nonnull TimeUnit timeUnit){
        CheckUtil.condition(time <= 0, "Time may not be less or equal to 0");
        
        try{
            scheduler.shutdown();
            if(!scheduler.awaitTermination(time, timeUnit))
                LOG.warn("Scheduler couldn't properly wait for termination.");
        }catch(InterruptedException ex){
            LOG.warn("Got interrupted while shutting down the Scheduler!", ex);
        }
    }
    
    /**
     * Starts a {@link java.util.concurrent.ScheduledExecutorService#scheduleAtFixedRate(Runnable, long, long, TimeUnit) scheduleAtFixedRate}
     * task, which will post the statistics of the provided {@link net.dv8tion.jda.api.JDA JDA instance} every n minutes.
     * 
     * <p>If the post can't be performed - either by getting a {@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException}
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
            }catch(IOException | RateLimitedException ex){
                LOG.warn("Got an exception while performing a auto-post task!", ex);
            }
        }, 1, botBlockAPI.getUpdateDelay(), TimeUnit.MINUTES);
    }
    
    /**
     * Starts a {@link java.util.concurrent.ScheduledExecutorService#scheduleAtFixedRate(Runnable, long, long, TimeUnit) scheduleAtFixedRate}
     * task, which will post the statistics of the provided {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance} every n minutes.
     *
     * <p>If the post can't be performed - either by getting a {@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException}
     * or by getting an {@link java.io.IOException IOException} - will the exception be caught and a Stacktrace printed.
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
            }catch(IOException | RateLimitedException ex){
                LOG.warn("Got an exception while performing a auto-post task!", ex);
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
     *         When the POST request wasn't successful.
     * @throws org.botblock.javabotblockapi.core.exceptions.RateLimitedException
     *         When we get rate limited by the BotBlock API (returns error code 429).
     */
    public void postGuilds(@Nonnull JDA jda, @Nonnull BotBlockAPI botBlockAPI) throws IOException, RateLimitedException{
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
     * {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager} and {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlockAPI} instances.
     *
     * <p>The following Exceptions may be thrown by the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.IllegalStateException IllegalStateException} - if the first shard of the provided ShardManager is null.</li>
     * </ul>
     * 
     * @param  shardManager
     *         The {@link net.dv8tion.jda.api.sharding.ShardManager ShardManager instance} to post stats from.
     * @param  botBlockAPI
     *         The {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlockAPI instance} to use.
     *
     * @throws java.io.IOException
     *         When the POST request wasn't successful.
     * @throws org.botblock.javabotblockapi.core.exceptions.RateLimitedException
     *         When we get rate limited by the BotBlock API (returns error code 429).
     */
    public void postGuilds(@Nonnull ShardManager shardManager, @Nonnull BotBlockAPI botBlockAPI) throws IOException, RateLimitedException{
        JDA shard = shardManager.getShardById(0);
        CheckUtil.condition(shard == null, "Shard 0 of ShardManager was invalid (null).");
        
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
