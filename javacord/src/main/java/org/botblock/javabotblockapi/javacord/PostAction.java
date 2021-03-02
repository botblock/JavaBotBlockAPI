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

package org.botblock.javabotblockapi.javacord;

import org.botblock.javabotblockapi.core.BotBlockAPI;
import org.botblock.javabotblockapi.core.CheckUtil;
import org.botblock.javabotblockapi.core.Info;
import org.botblock.javabotblockapi.core.exceptions.RateLimitedException;
import org.botblock.javabotblockapi.requests.handler.RequestHandler;
import org.javacord.api.DiscordApi;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class used to perform POST requests towards the <a href="https://botblock.org/api/docs#count" target="_blank">/api/count</a>
 * endpoint of BotBlock using the Javacord Library.
 * 
 * <p>The class offers options to post either {@link #postGuilds(BotBlockAPI, DiscordApi...) manually} or
 * {@link #enableAutoPost(BotBlockAPI, DiscordApi...) automatically}.
 *
 * <p>If you want to post without using Javacord, use the {@link org.botblock.javabotblockapi.requests.PostAction normal PostAction}.
 */
public class PostAction{
    private final Logger LOG = LoggerFactory.getLogger("JavaBotBlockAPI - PostAction (Javacord)");
    
    private final RequestHandler requestHandler;
    private final ScheduledExecutorService scheduler;
    
    /**
     * Creates a new instance of this class.
     * <br>This will set the UserAgent used for POST request to {@code <botname>-<discriminator>/<api-version> (Javacord) DBots/<id>}
     * using the provided {@link org.javacord.api.DiscordApi DiscordApi instance}.
     * 
     * @param api
     *        The {@link org.javacord.api.DiscordApi DiscordApi instance} used to set the UserAgent.
     */
    public PostAction(DiscordApi api){
        this.requestHandler = new RequestHandler(String.format(
                "%s-%s/%s (Javacord) DBots/%s",
                api.getYourself().getName(),
                api.getYourself().getDiscriminator(),
                Info.VERSION,
                api.getYourself().getId()
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
     * @see java.util.concurrent.ScheduledExecutorService#awaitTermination(long, TimeUnit)
     */
    public void disableAutoPost(long time, @Nonnull TimeUnit timeUnit){
        CheckUtil.condition(time <= 0, "time may not be less or equal to 0!");
        
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
     * task, which will post the statistics of the provided {@link org.javacord.api.DiscordApi DiscordApi instance} every n minutes.
     *
     * <p>If the post can't be performed - either by getting a {@link org.botblock.javabotblockapi.core.exceptions.RateLimitedException RatelimitedException}
     * or by getting an {@link java.io.IOException IOException} - will the exception be caught and a Stacktrace printed.
     * 
     * <p>The scheduler will wait an initial delay of 1 minute and then performs a task every n minutes, where n is the
     * time set in {@link org.botblock.javabotblockapi.core.BotBlockAPI.Builder#setUpdateDelay(Integer) BotBlockAPI.Builder.setUpdateDelay(Integer)}
     * (default is 30 minutes).
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.IllegalStateException IllegalStateException} - When the provided DiscordApis are 0 or less.</li>
     * </ul>
     * 
     * @param discordApis
     *        The {@link org.javacord.api.DiscordApi DiscordApi instances} to post stats from.
     * @param botBlockAPI
     *        The {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlockAPI instance} to use.
     */
    public void enableAutoPost(@Nonnull BotBlockAPI botBlockAPI, @Nonnull DiscordApi... discordApis){
        CheckUtil.condition(discordApis.length <= 0, "At least one DiscordApi instance needs to be provided!");
        
        scheduler.scheduleAtFixedRate(() -> {
            try{
                postGuilds(botBlockAPI, discordApis);
            }catch(IOException | RateLimitedException ex){
                LOG.warn("Got an exception while performing a auto-post task!", ex);
            }
        }, 1, botBlockAPI.getUpdateDelay(), TimeUnit.MINUTES);
    }
    
    /**
     * Performs a POST request towards the BotBlock API using the information from the provided
     * {@link org.javacord.api.DiscordApi DiscordApi} and {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlock} instances.
     * 
     * <p>If the provided DiscordApi instance is a sharded Bot (Amount of shards is larger than 1) will the request
     * contain the {@code shards} array alongside a {@code shard_count} field.
     *
     * <p>Following Exceptions can be thrown from the CheckUtil:
     * <ul>
     *     <li>{@link java.lang.IllegalStateException IllegalStateException} - When the provided DiscordApis are 0 or less.</li>
     * </ul>
     * 
     * @param discordApis
     *        The {@link org.javacord.api.DiscordApi DiscordApi instances} to post stats from.
     * @param botBlockAPI
     *        The {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlockAPI instance} to use.
     *        
     * @throws java.io.IOException
     *         When the POST request wasn't successful.
     * @throws org.botblock.javabotblockapi.core.exceptions.RateLimitedException
     *         When we get rate limited by the BotBlock API (returns error code 429).
     */
    public void postGuilds(@Nonnull BotBlockAPI botBlockAPI, @Nonnull DiscordApi... discordApis) throws IOException, RateLimitedException{
        CheckUtil.condition(discordApis.length <= 0, "At least one DiscordApi instance needs to be provided!");
        
        JSONObject json = new JSONObject()
                .put("bot_id", discordApis[0].getYourself().getId());
        
        if(discordApis.length > 1){
            int guilds = Arrays.stream(discordApis).map(DiscordApi::getServers).mapToInt(Collection::size).sum();
            json.put("server_count", guilds)
                .put("shard_count", discordApis.length);
            
            List<Integer> shards = new ArrayList<>();
            for(DiscordApi api : discordApis)
                shards.add(api.getServers().size());
            
            json.put("shards", new JSONArray(Arrays.deepToString(shards.toArray())));
        }else{
            json.put("server_count", discordApis[0].getServers().size());
        }
        
        botBlockAPI.getTokens().forEach(json::put);
        
        requestHandler.performPOST(json, botBlockAPI.getTokens().size());
    }
}
