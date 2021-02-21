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
 * <p>Keep in mind that not all Sites support either GET or POST requests. You may use {@link #supportsGet() supportsGet()}
 * or {@link #supportsPost() supportsPost()} methods to check whether the instance supports GET and/or POST respectively.
 * 
 * @since 6.3.0
 */
public class Site{
    
    /**
     * <a href="https://arcane-center.xyz" target="_blank">arcane-center.xyz</a>
     * 
     * <p>Supported methods:
     * <ul>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site ARCANE_CENTER_XYZ = new Site("arcane-center.xyz", HttpMethod.POST);
    
    /**
     * <a href="https://bladebotlist.xyz" target="_blank">bladebotlist.xyz</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     * 
     * @since 6.3.0
     */
    public static final Site BLADEBOTLIST_XYZ = new Site("bladebotlist.xyz", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://blist.xyz" target="_blank">blist.xyz</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site BLIST_XYZ = new Site("blist.xyz", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://botlist.space" target="_blank">botlist.space</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site BOTLIST_SPACE = new Site("botlist.space", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://botsdatabase.com" target="_blank">botsdatabase.com</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site BOTSDATABASE_COM = new Site("botsdatabase.com", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://bots.discordlabs.org" target="_blank">bots.discordlabs.org</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site BOTS_DISCORDLABS_ORG = new Site("discordlabs.org", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://bots.distop.xyz" target="_blank">bots.distop.xyz</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     * </ul>
     * 
     * @deprecated Site no longer available.
     * 
     * @since 6.4.1
     */
    @Deprecated
    @DeprecatedSince(major = 6, minor = 6, patch = 1)
    @PlannedRemoval(major = 6, minor = 6, patch = 3)
    public static final Site BOTS_DISTOP_XYZ = new Site("bots.distop.xyz");
    
    /**
     * <a href="https://botsfordiscord.com" target="_blank">botsfordiscord.com</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site BOTSFORDISCORD_COM = new Site("botsfordiscord.com", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://bots.idledev.org" target="_blank">bots.idledev.org</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     * 
     * @deprecated Site no longer available.
     */
    @Deprecated
    @DeprecatedSince(major = 6, minor = 6, patch = 1)
    @PlannedRemoval(major = 6, minor = 6, patch = 3)
    public static final Site BOTS_IDLEDEV_ORG = new Site("bots.idledev.org");
    
    /**
     * <a href="https://bots.ondiscord.xyz" target="_blank">bots.ondiscord.xyz</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site BOTS_ONDISCORD_XYZ = new Site("bots.ondiscord.xyz", HttpMethod.POST);
    
    /**
     * <a href="https://dblista.pl" target="_blank">dblista.pl</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     * </ul>
     */
    public static final Site DBLISTA_PL = new Site("dblista.pl", HttpMethod.GET);
    
    /**
     * <a href="https://discordapps.dev" target="_blank">discordapps.dev</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site DISCORDAPPS_DEV = new Site("discordapps.dev", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://discord.boats" target="_blank">discord.boats</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site DISCORD_BOATS = new Site("discord.boats", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://discordbotdirectory.net" target="_blank">discordbotdirectory.net</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     * </ul>
     * 
     * @since 6.3.0
     */
    public static final Site DISCORDBOTDIRECTORY_NET = new Site("discordbotdirectory.net", HttpMethod.GET);
    
    /**
     * <a href="https://discordbotlist.com" target="_blank">discordbotlist.com</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site DISCORDBOTLIST_COM = new Site("discordbotlist.com", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://discordbots.co" target="_blank">discordbots.co</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     * 
     * @since 5.2.3
     */
    public static final Site DISCORDBOTS_CO = new Site("discordbots.co", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://discord.bots.gg" target="_blank">discord.bots.gg</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site DISCORD_BOTS_GG = new Site("discord.bots.gg", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://discordextremelist.xyz" target="_blank">discordextremelist.xyz</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site DISCORDEXTREMELIST_XYZ = new Site("discordextremelist.xyz", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://discordlistology.com" target="_blank">discordlistology.com</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site DISCORDLISTOLOGY_COM = new Site("discordlistology.com", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://disforge.com" target="_blank">disforge.com</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site DISFORGE_COM = new Site("disforge.com", HttpMethod.POST);
    
    /**
     * <a href="https://fasteslist.xyz" target="_blank">fateslist.xyz</a>
     * 
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site FATESLIST_XYZ = new Site("fateslist.xyz", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://infinitybotlist.com" target="_blank">infinitybotlist.com</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site INFINITYBOTLIST_COM = new Site("infinitybotlist.com", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href ="https://paradisebots.net" target="_blank">paradisebots.net</a>
     * 
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     * 
     * @since 6.4.2
     */
    public static final Site PARADISEBOTS_NET = new Site("paradisebots.net", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://space-bot-list.xyz" target="_blank">space-bot-list.xyz</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site SPACE_BOT_LIST_XYZ = new Site("space-bot-list.xyz", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://topcord.xyz" target="_blank">topcord.xyz</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site TOPCORD_XYZ = new Site("topcord.xyz", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://voidbots.net" target="_blank">voidbots.net</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site VOIDBOTS_NET = new Site("voidbots.net", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://wonderbotlist.com" target="_blank">wonderbotlist.com</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
     */
    public static final Site WONDERBOTLIST_COM = new Site("wonderbotlist.com", HttpMethod.GET, HttpMethod.POST);
    
    /**
     * <a href="https://yabl.xyz" target="_blank">yabl.xyz</a>
     *
     * <p>Supported methods:
     * <ul>
     *     <li>GET</li>
     *     <li>POST</li>
     * </ul>
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
    
    /**
     * The name used by the BotBlock API to identify the site.
     * <br>The name usually is just the domain of the site without the http(s):// in front of it.
     * 
     * @return The name of the site used for the BotBlock API.
     */
    public String getName(){
        return name;
    }
    
    /**
     * Whether the site supports GET requests towards itself or not.
     * 
     * @return True if the site supports GET request, otherwise false.
     */
    public boolean supportsGet(){
        return !methods.isEmpty() && methods.contains(HttpMethod.GET);
    }
    
    /**
     * Whether the site supports POST requests towards itself or not.
     *
     * @return True if the site supports POST request, otherwise false.
     */
    public boolean supportsPost(){
        return !methods.isEmpty() && methods.contains(HttpMethod.POST);
    }
    
    public enum HttpMethod{
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
