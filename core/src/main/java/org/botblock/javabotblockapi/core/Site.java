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

import org.botblock.javabotblockapi.core.annotations.DeprecatedSince;
import org.botblock.javabotblockapi.core.annotations.PlannedRemoval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class containing all (known) Bot lists.
 * <br>The static instances of this class allow the easy use within various methods of the JavaBotBlockAPI.
 * 
 * <p>Keep in mind that not all Sites support eithere GET or POST requests. You may use {@link #supportsGet() supportsGet()}
 * or {@link #supportsPost() supportsPost()} methods to check whether the instance supports GET and/or POST respectively.
 * 
 * @since 6.3.0
 */
public class Site{
    
    /**
     * <a href="https://arcane-center.xyz" target="_blank">arcane-center.xyz</a>
     */
    public static final Site ARCANE_CENTER_XYZ = new Site("arcane-center.xyz", HttpMethod.POST);
    
    /**
     * <a href="https://bladebotlist.xyz" target="_blank">bladebotlist.xyz</a>
     * 
     * @since 6.3.0
     */
    public static final Site BLADEBOTLIST_XYZ = new Site("bladebotlist.xyz", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://blist.xyz" target="_blank">blist.xyz</a>
     */
    public static final Site BLIST_XYZ = new Site("blist.xyz", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://botlist.space" target="_blank">botlist.space</a>
     */
    public static final Site BOTLIST_SPACE = new Site("botlist.space", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://botsdatabase.com" target="_blank">botsdatabase.com</a>
     */
    public static final Site BOTSDATABASE_COM = new Site("botsdatabase.com", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://bots.discordlabs.org" target="_blank">bots.discordlabs.org</a>
     */
    public static final Site BOTS_DISCORDLABS_ORG = new Site("discordlabs.org", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://botsfordiscord.com" target="_blank">botsfordiscord.com</a>
     */
    public static final Site BOTSFORDISCORD_COM = new Site("botsfordiscord.com", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://bots.ondiscord.xyz" target="_blank">bots.ondiscord.xyz</a>
     */
    public static final Site BOTS_ONDISCORD_XYZ = new Site("bots.ondiscord.xyz", HttpMethod.POST);
    
    /**
     * <a href="https://dblista.pl" target="_blank">dblista.pl</a>
     */
    public static final Site DBLISTA_PL = new Site("dblista.pl", HttpMethod.GET);
    
    /**
     * <a href="https://discordapps.dev" target="_blank">discordapps.dev</a>
     */
    public static final Site DISCORDAPPS_DEV = new Site("discordapps.dev", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://discord.boats" target="_blank">discord.boats</a>
     */
    public static final Site DISCORD_BOATS = new Site("discord.boats", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://discordbotdirectory.net" target="_blank">discordbotdirectory.net</a>
     * 
     * @since 6.3.0
     */
    public static final Site DISCORDBOTDIRECTORY_NET = new Site("discordbotdirectory.net", HttpMethod.GET);
    
    /**
     * <a href="https://discordbotlist.com" target="_blank">discordbotlist.com</a>
     */
    public static final Site DISCORDBOTLIST_COM = new Site("discordbotlist.com", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://discordbots.co" target="_blank">discordbots.co</a>
     * 
     * @since 5.2.3
     */
    public static final Site DISCORDBOTS_CO = new Site("discordbots.co", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://discord.bots.gg" target="_blank">discord.bots.gg</a>
     */
    public static final Site DISCORD_BOTS_GG = new Site("discord.bots.gg", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://discordbots.fun" target="_blank">discordbots.fun</a>
     *
     * @deprecated Site no longer exists
     */
    @Deprecated
    @DeprecatedSince(major = 6, minor = 3, patch = 0)
    @PlannedRemoval(major = 6, minor = 3, patch = 2)
    public static final Site DISCORDBOTS_FUN = new Site("discordbots.fun");
    
    /**
     * <a href="https://discordextremelist.xyz" target="_blank">discordextremelist.xyz</a>
     */
    public static final Site DISCORDEXTREMELIST_XYZ = new Site("discordextremelist.xyz", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://discordlist.co" target="_blank">discordlist.co</a>
     *
     * @deprecated Site no longer exists
     */
    @Deprecated
    @DeprecatedSince(major = 6, minor = 3, patch = 0)
    @PlannedRemoval(major = 6, minor = 3, patch = 0)
    public static final Site DISCORDLIST_CO = new Site("discordlist.co");
    
    /**
     * <a href="https://discordlistology.com" target="_blank">discordlistology.com</a>
     */
    public static final Site DISCORDLISTOLOGY_COM = new Site("discordlistology.com", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://disforge.com" target="_blank">disforge.com</a>
     */
    public static final Site DISFORGE_COM = new Site("disforge.com", HttpMethod.POST);
    
    /**
     * <a href="https://glennbotlist.xyz" target="_blank">glennbotlist.xyz</a>
     * 
     * @deprecated Site no longer exists
     */
    @Deprecated
    @DeprecatedSince(major = 6, minor = 3, patch = 0)
    @PlannedRemoval(major = 6, minor = 3, patch = 0)
    public static final Site GLENNBOTLIST_XYZ = new Site("glennbotlist.xyz");
    
    /**
     * <a href="https://hydrogenbots.club" target="_blank">hydrogenbots.club</a>
     */
    public static final Site HYDROGENBOTS_CLUB = new Site("hydrogenbots.club", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://mythicalbots.xyz" target="_blank">mythicalbots.xyz</a>
     */
    public static final Site MYTHICALBOTS_XYZ = new Site("mythicalbots.xyz", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://space-bot-list.xyz" target="_blank">space-bot-list.xyz</a>
     */
    public static final Site SPACE_BOT_LIST_XYZ = new Site("space-bot-list.xyz", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://topcord.xyz" target="_blank">topcord.xyz</a>
     */
    public static final Site TOPCORD_XYZ = new Site("topcord.xyz", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://voidbots.net" target="_blank">voidbots.net</a>
     */
    public static final Site VOIDBOTS_NET = new Site("voidbots.net", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://wonderbotlist.com" target="_blank">wonderbotlist.com</a>
     */
    public static final Site WONDERBOTLIST_COM = new Site("wonderbotlist.com", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://yabl.xyz" target="_blank">yabl.xyz</a>
     */
    public static final Site YABL_XYZ = new Site("yabl.xyz", HttpMethod.GET, HttpMethod.POST);
    
    private final String name;
    private final List<HttpMethod> methods;
    
    private Site(String name, HttpMethod... methods){
        this.name = name;
        this.methods = Arrays.asList(methods);
    }
    
    private Site(String name){
        this.name = name;
        this.methods = new ArrayList<>();
    }
    
    public String getName(){
        return name;
    }
    
    public boolean supportsGet(){
        return !methods.isEmpty() && methods.contains(HttpMethod.GET);
    }
    
    public boolean supportsPost(){
        return !methods.isEmpty() && methods.contains(HttpMethod.POST);
    }
    
    public static enum HttpMethod{
        /**
         * Bot list supports GET requests.
         */
        GET,
        
        /**
         * Bot list supports POST requests.
         */
        POST
    }
}
