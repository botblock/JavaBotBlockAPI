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

package org.botblock.javabotblockapi.internal;

import org.botblock.javabotblockapi.Site;
import org.botblock.javabotblockapi.requests.GetBotAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetBotActionImpl implements GetBotAction{
    
    private final RequestHandler REQUEST_HANDLER = new RequestHandler();
    private boolean disableCache;
    
    public GetBotActionImpl(){
        this.disableCache = false;
    }
    
    public GetBotActionImpl(boolean disableCache){
        this.disableCache = disableCache;
    }
    
    @Override
    public JSONObject getBotInfo(@NotNull Long id){
        return REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
    }
    
    @Override
    public JSONObject getBotInfo(@NotNull String id){
        return REQUEST_HANDLER.performGetBot(id, disableCache);
    }
    
    @Override
    public JSONObject getBotListInfo(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getJSONObject("list_data");
    }
    
    @Override
    public JSONObject getBotListInfo(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getJSONObject("list_data");
    }
    
    @Override
    public JSONArray getBotListInfo(@NotNull Long id, @NotNull Site site){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache).getJSONObject("list_data");
        
        return json.getJSONArray(site.getSite());
    }
    
    @Override
    public JSONArray getBotListInfo(@NotNull Long id, @NotNull String site){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache).getJSONObject("list_data");
        
        return json.getJSONArray(site);
    }
    
    @Override
    public JSONArray getBotListInfo(@NotNull String id, @NotNull Site site){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache).getJSONObject("list_data");
        
        return json.getJSONArray(site.getSite());
    }
    
    @Override
    public JSONArray getBotListInfo(@NotNull String id, @NotNull String site){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache).getJSONObject("list_data");
        
        return json.getJSONArray(site);
    }
    
    @Override
    public String getDiscriminator(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("discriminator");
    }
    
    @Override
    public String getDiscriminator(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("discriminator");
    }
    
    @Override
    public String getGitHub(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("github");
    }
    
    @Override
    public String getGitHub(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("github");
    }
    
    @Override
    public String getInvite(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("invite");
    }
    
    @Override
    public String getInvite(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("invite");
    }
    
    @Override
    public String getLibrary(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("library");
    }
    
    @Override
    public String getLibrary(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("library");
    }
    
    @Override
    public String getName(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("username");
    }
    
    @Override
    public String getName(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("username");
    }
    
    @Override
    public List<String> getOwners(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        List<String> owners = new ArrayList<>();
        for(int i = 0; i < json.getJSONArray("owners").length(); i++)
            owners.add(json.getJSONArray("owners").getString(i));
        
        return owners;
    }
    
    @Override
    public List<String> getOwners(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        List<String> owners = new ArrayList<>();
        for(int i = 0; i < json.getJSONArray("owners").length(); i++)
            owners.add(json.getJSONArray("owners").getString(i));
        
        return owners;
    }
    
    @Override
    public String getPrefix(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("prefix");
    }
    
    @Override
    public String getPrefix(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("prefix");
    }
    
    @Override
    public Integer getServerCount(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getInt("server_count");
    }
    
    @Override
    public Integer getServerCount(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getInt("server_count");
    }
    
    @Override
    public String getSupportLink(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("support");
    }
    
    @Override
    public String getSupportLink(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("support");
    }
    
    @Override
    public String getWebsite(@NotNull Long id){
        JSONObject json = REQUEST_HANDLER.performGetBot(Long.toString(id), disableCache);
        
        return json.getString("website");
    }
    
    @Override
    public String getWebsite(@NotNull String id){
        JSONObject json = REQUEST_HANDLER.performGetBot(id, disableCache);
        
        return json.getString("website");
    }
}
