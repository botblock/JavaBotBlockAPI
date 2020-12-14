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

package org.botblock.javabotblockapi.requests.handler;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import okhttp3.*;
import org.botblock.javabotblockapi.core.exceptions.RateLimitedException;
import org.botblock.javabotblockapi.core.CheckUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RequestHandler{
    
    private final Logger LOG = LoggerFactory.getLogger("JavaBotBlockAPI - RequestHandler");
    private final OkHttpClient CLIENT = new OkHttpClient();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    
    private final String BASE_URL = "https://botblock.org/api/";
    private final String userAgent;
    
    private final Cache<String, JSONObject> botCache = Caffeine.newBuilder()
            .expireAfterWrite(2, TimeUnit.MINUTES)
            .build();
    private final Cache<String, JSONObject> listCache = Caffeine.newBuilder()
            .expireAfterWrite(2, TimeUnit.MINUTES)
            .build();
    
    public RequestHandler(String userAgent){
        this.userAgent = userAgent;
    }
    
    public JSONObject performGetBot(@Nonnull String id, boolean disableCache){
        String url = BASE_URL + "bots/" + id;
        
        if(!disableCache)
            return botCache.get(id, k -> {
                try{
                    return performGET(url, userAgent);
                }catch(IOException | RateLimitedException ex){
                    LOG.error("Exception while performing a GET request. Type: Get Bot, Cache: Enabled", ex);
                    return null;
                }
            });
        
        try{
            return performGET(url, userAgent);
        }catch(IOException ex){
            LOG.error("Exception while performing a GET request. Type: Get Bot, Cache: Disabled", ex);
            return null;
        }
    }
    
    public JSONObject performGetList(@Nonnull String id, boolean disableCache, boolean filtered){
        return performGetList(id, null, disableCache, filtered);
    }
    
    public JSONObject performGetList(@Nonnull String id, @Nullable String site, boolean disableCache){
        return performGetList(id, site, disableCache, false);
    }
    
    public JSONObject performGetList(@Nonnull String id, @Nullable String site, boolean disableCache, boolean filtered){
        String url = BASE_URL + "lists" + (site == null ? "" : "/" + site);
        if(filtered)
            url += "?filter=true";
        
        if(!disableCache){
            String finalUrl = url;
            return listCache.get(id, k -> {
                try{
                    return performGET(finalUrl, userAgent);
                }catch(IOException ex){
                    LOG.error("IOException while performing a GET request. Type: Get list, Cache: Enabled", ex);
                    return null;
                }
            });
        }
        
        try{
            return performGET(url, userAgent);
        }catch(IOException ex){
            LOG.error("IOException while performing a GET request. Type: Get list, Cache: Disabled", ex);
            return null;
        }
        
    }
    
    public void performPOST(@Nonnull JSONObject json, int sites) throws IOException{
        CheckUtil.condition(sites < 1, "The POST action requires at least 1 site!");
        
        String url = BASE_URL + "count";
        final long timeout = sites * 10L;
        
        OkHttpClient postClient = CLIENT.newBuilder()
                .callTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .build();
        
        RequestBody body = RequestBody.create(json.toString(), null);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", userAgent)
                .post(body)
                .build();
        
        try(Response response = postClient.newCall(request).execute()){
            ResponseBody responseBody = response.body();
            if(responseBody == null){
                LOG.error("Received empty Response from BotBlock API!");
                LOG.error(
                        "Response{timestamp={}, protocol={}, code={}, message={}, headers={}}",
                        System.currentTimeMillis(),
                        response.protocol(),
                        response.code(),
                        response.message(),
                        response.headers().toString()
                );
                return;
            }
            
            String bodyString = responseBody.string();
            if(bodyString.isEmpty()){
                LOG.error("Received empty Response from BotBlock API!");
                LOG.error(
                        "Response{timestamp={}, protocol={}, code={}, message={}, headers={}}",
                        System.currentTimeMillis(),
                        response.protocol(),
                        response.code(),
                        response.message(),
                        response.headers().toString()
                );
                return;
            }
            
            if(!response.isSuccessful()){
                if(response.code() == 429){
                    JSONObject errorJson = new JSONObject(bodyString);
                    throw new RateLimitedException(errorJson);
                }
                
                throw new IOException(String.format(
                        "Could not post Guild count. The API responded with error code %d (%s)",
                        response.code(),
                        response.message()
                ));
            }
            
            JSONObject responseJson = new JSONObject(bodyString);
            if(!responseJson.getJSONObject("failure").isEmpty()){
                JSONObject failure = responseJson.getJSONObject("failure");
                JSONArray failures = new JSONArray();
                
                for(String key : failure.keySet()){
                    try{
                        JSONArray array = failure.getJSONArray(key);
                        failures.put(getJson(key, array));
                    }catch(JSONException ex){
                        failures.put(getJson(key, null));
                    }
                }
                
                LOG.warn("One or more POST requests returned a non-successful response. JSON with failed sites below.");
                LOG.warn(failures.toString());
            }
        }
    }
    
    public ScheduledExecutorService getScheduler(){
        return scheduler;
    }
    
    private JSONObject performGET(@Nonnull String url, String header) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", header)
                .build();
        
        try(Response response = CLIENT.newCall(request).execute()){
            ResponseBody body = response.body();
            if(body == null){
                LOG.error("Received empty Response from BotBlock API!");
                LOG.error(
                        "Response{timestamp={}, protocol={}, code={}, message={}, headers={}}",
                        System.currentTimeMillis(),
                        response.protocol(),
                        response.code(),
                        response.message(),
                        response.headers().toString()
                );
                return null;
            }
            
            String bodyString = body.string();
            if(bodyString.isEmpty()){
                LOG.error("Received empty Response from BotBlock API!");
                LOG.error(
                        "Response{timestamp={}, protocol={}, code={}, message={}, headers={}}",
                        System.currentTimeMillis(),
                        response.protocol(),
                        response.code(),
                        response.message(),
                        response.headers().toString()
                );
                return null;
            }
            
            if(!response.isSuccessful()){
                if(response.code() == 429){
                    JSONObject errorJson = new JSONObject(bodyString);
                    throw new RateLimitedException(errorJson);
                }
                
                throw new IOException(String.format(
                        "Could not retrieve information. The API responded with error code %d (%s).",
                        response.code(),
                        response.message()
                ));
            }
            
            return new JSONObject(bodyString);
        }
    }
    
    JSONObject getJson(String key, JSONArray array){
        JSONObject json = new JSONObject()
                .put("code", array == null ? "?" : array.get(0))
                .put("message", array == null ? "?" : array.get(1));
        
        return new JSONObject().put(key, json);
    }
}
