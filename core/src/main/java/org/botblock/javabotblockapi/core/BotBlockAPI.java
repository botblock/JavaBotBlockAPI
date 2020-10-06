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

package org.botblock.javabotblockapi.core;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * Class used to define the auth-tokens used for the different sites.
 *
 * <p>Use the {@link org.botblock.javabotblockapi.core.BotBlockAPI.Builder BotBlockAPI.Builder} class for easy creation.
 */
public class BotBlockAPI{
    /**
     * Default delay. Used for when no own delay was defined with {@link org.botblock.javabotblockapi.core.BotBlockAPI.Builder#setUpdateDelay(Integer) BotBlock.Builder.setUpdateDelay(Integer)}
     */
    public static final int DEFAULT_DELAY = 30;
    
    private final Map<String, String> tokens;
    private final int updateDelay;
    
    private BotBlockAPI(@Nonnull Map<String, String> tokens, int updateDelay){
        if(tokens.isEmpty())
            throw new NullPointerException("Tokens may not be empty.");
        
        if(updateDelay < 2)
            throw new IllegalArgumentException("UpdateDelay may not be less than 2.");
        
        this.tokens = tokens;
        this.updateDelay = updateDelay;
    }
    
    public Map<String, String> getTokens(){
        return tokens;
    }
    
    public int getUpdateDelay(){
        return updateDelay;
    }
    
    /**
     * Builder class to create an instance of {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlockAPI}
     */
    public static class Builder{
        private Map<String, String> tokens = new HashMap<>();
        private int updateDelay = DEFAULT_DELAY;
        
        /**
         * Empty constructor to get the class.
         */
        public Builder(){}
        
        /**
         * Adds the provided {@link org.botblock.javabotblockapi.core.Site Site name} and token to the Map.
         * <br>Entries with the same key will be overwritten.
         *
         * @param  site
         *         The {@link org.botblock.javabotblockapi.core.Site Site} to get the name from.
         * @param  token
         *         The API token from the corresponding bot list. May not be null or empty.
         *         <br>You may receive the API token from the bot list.
         *
         * <p>Following Exceptions can be thrown from the {@link org.botblock.javabotblockapi.core.CheckUtil CheckUtil}:
         * <ul>
         *     <li>{@link java.lang.NullPointerException NullPointerException} - When the provided Token is empty.</li>
         *     <li>{@link java.lang.IllegalStateException IllegalStateException} - When the provided Site doesn't support POST requests.</li>
         * </ul>
         *
         * @return The Builder after the site and token were set. Useful for chaining.
         *
         * @since 2.1.0
         */
        public Builder addAuthToken(@Nonnull Site site, @Nonnull String token){
            CheckUtil.notEmpty(token, "Token");
            CheckUtil.supportsPost(site);
            
            tokens.put(site.getSite(), token);
            return this;
        }
        
        /**
         * Adds the provided Site name and token to the Map.
         * <br>Entries with the same key will be overwritten.
         *
         * @param  site
         *         The name of the site. May not be null or empty.
         *         <br>A list of supported sites can be found <a href="https://botblock.org/api/docs#count" target="_blank">here</a>.
         * @param  token
         *         The API token from the corresponding bot list. May not be null or empty.
         *         <br>You may receive the API token from the bot list.
         *
         * @throws java.lang.NullPointerException
         *         When either the site or token are empty ({@code ""}).
         *
         * @return The Builder after the site and token were set. Useful for chaining.
         */
        public Builder addAuthToken(@Nonnull String site, @Nonnull String token){
            CheckUtil.notEmpty(site, "Site");
            CheckUtil.notEmpty(token, "Token");
            
            tokens.put(site, token);
            return this;
        }
        
        /**
         * Sets the provided Map as the new Map.
         * <br><b>This will overwrite every previously set entry!</b>
         *
         * @param  tokens
         *         The Map that should be used. May not be null.
         *
         * @throws java.lang.NullPointerException
         *         When the provided Map is empty.
         *
         * @return The Builder after the Map was set. Useful for chaining.
         */
        public Builder setAuthTokens(@Nonnull Map<String, String> tokens){
            CheckUtil.notEmpty(tokens, "Tokens");
            
            this.tokens = tokens;
            return this;
        }
        
        /**
         * Sets the update delay (in minutes) for the auto-posting.
         * <br>You don't need to set this when not using the auto-post option. Default is 30.
         *
         * @param  updateDelay
         *         The update interval in minutes that should be used. This can't be less than 2.
         *
         * @throws java.lang.IllegalStateException
         *         When the updateInterval is less than 2.
         *
         * @return The Builder after the updateInterval was set. Useful for chaining.
         */
        public Builder setUpdateDelay(@Nonnull Integer updateDelay){
            CheckUtil.condition(updateDelay < 2, "UpdateDelay may not be less than 2.");
            
            this.updateDelay = updateDelay;
            return this;
        }
        
        /**
         * Builds the instance of {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlockAPI}.
         *
         * @throws java.lang.NullPointerException
         *         When the Tokens Map is empty.
         *
         * @return The built, usable {@link org.botblock.javabotblockapi.core.BotBlockAPI BotBlockAPI}.
         */
        public BotBlockAPI build(){
            CheckUtil.notEmpty(tokens, "Tokens");
            
            return new BotBlockAPI(tokens, updateDelay);
        }
    }
}
