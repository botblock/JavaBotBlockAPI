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

import java.util.Arrays;
import java.util.List;

/**
 * Enum class containing all sites currently supported by BotBlock.org.
 *
 * @since 2.1.0
 */
public enum Site {
    
    /**
     * <a href="https://arcane-center.xyz" target="_blank">arcane-center.xyz</a>
     */
    ARCANE_CENTER_XYZ("arcane-center.xyz", new HttpMethod[]{HttpMethod.POST}),
    
    /**
     * <a href="https://bladebotlist.xyz" target="_blanK">bladebotlist.xyz</a>
     * 
     * @since 6.3.0
     */
    BLADEBOTLIST_XYZ("bladebotlist.xyz", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://blist.xyz" target="_blank">blist.xyz</a>
     */
    BLIST_XYZ("blist.xyz", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://botlist.space" target="_blank">botlist.space</a>
     */
    BOTLIST_SPACE("botlist.space", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://botsdatabase.com" target="_blank">botsdatabase.com</a>
     */
    BOTSDATABASE_COM("botsdatabase.com", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://bots.discordlabs.org" target="_blank">bots.discordlabs.org</a>
     */
    BOTS_DISCORDLABS_ORG("bots.discordlabs.org", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://botsfordiscord.com" target="_blank">botsfordiscord.com</a>
     */
    BOTSFORDISCORD_COM("botsfordiscord.com", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://bots.ondiscord.xyz" target="_blank">bots.ondiscord.xyz</a>
     */
    BOTS_ONDISCORD_XYZ("bots.ondiscord.xyz", new HttpMethod[]{HttpMethod.POST}),
    
    /**
     * <a href="https://dblista.pl" target="_blank">dblista.pl</a>
     */
    DBLISTA_PL("dblista.pl", new HttpMethod[]{HttpMethod.GET}),
    
    /**
     * <a href="https://discordapps.dev" target="_blank">discordapps.dev</a>
     */
    DISCORDAPPS_DEV("discordapps.dev", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://discord.boats" target="_blank">discord.boats</a>
     */
    DISCORD_BOATS("discord.boats", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://discordbotdirectory.net" target="_blank">discordbotdirectory.net</a>
     * 
     * @since 6.3.0
     */
    DISCORDBOTDIRECTORY_NET("discordbotdirectory.net", new HttpMethod[]{HttpMethod.GET}),
    
    /**
     * <a href="https://discordbotlist.com" target="_blank">discordbotlist.com</a>
     */
    DISCORDBOTLIST_COM("discordbotlist.com", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://discordbots.co" target="_blank">discordbots.co</a>
     *
     * @since 5.2.3
     */
    DISCORDBOTS_CO("discordbots.co", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://discord.bots.gg" target="_blank">discord.bots.gg</a>
     */
    DISCORD_BOTS_GG("discord.bots.gg", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://discordbots.fun" target="_blank">discordbots.fun</a>
     * 
     * @deprecated No longer exists
     */
    @Deprecated
    @DeprecatedSince(major = 6, minor = 3, patch = 0)
    @PlannedRemoval(major = 6, minor = 3, patch = 2)
    DISCORDBOTS_FUN("discordbots.fun", new HttpMethod[0]),
    
    /**
     * <a href="https://discordextremelist.xyz" target="_blank">discordextremelist.xyz</a>
     *
     * @since 2.3.3
     */
    DISCORDEXTREMELIST_XYZ("discordextremelist.xyz", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://discordlist.co" target="_blank">discordlist.co</a>
     * 
     * @deprecated No longer exists
     */
    @Deprecated
    @DeprecatedSince(major = 6, minor = 3, patch = 0)
    @PlannedRemoval(major = 6, minor = 3, patch = 2)
    DISCORDLIST_CO("discordlist.co", new HttpMethod[0]),
    
    /**
     * <a href="https://discordlistology.com" target="_blank">discordlistology.com</a>
     *
     * @since 5.2.1
     */
    DISCORDLISTOLOGY_COM("discordlistology.com", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://disforge.com/bots" target="_blank">disforge.com</a>
     * 
     * @since 6.2.2
     */
    DISFORGE_COM("disforge.com", new HttpMethod[]{HttpMethod.POST}),
    
    /**
     * <a href="https://glennbotlist.xyz" target="_blank">glennbotlist.xyz</a>
     * 
     * @deprecated No longer exists
     */
    @Deprecated
    @DeprecatedSince(major = 6, minor = 3, patch = 0)
    @PlannedRemoval(major = 6, minor = 3, patch = 2)
    GLENNBOTLIST_XYZ("glennbotlist.xyz", new HttpMethod[0]),
    
    /**
     * <a href="https://hydrogenbots.club" target="_blank">hydrogenbots.club</a>
     * 
     * @since 6.2.1
     */
    HYDROGENBOTS_CLUB("hydrogenbots.club", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://mythicalbots.xyz" target="_blank">mythicalbots.xyz</a>
     */
    MYTHICALBOTS_XYZ("mythicalbots.xyz", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://space-bot-list.xyz" target="_blank">space-bot-list.xyz</a>
     */
    SPACE_BOT_LIST_XYZ("space-bot-list.xyz", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://topcord.xyz" target="_blank">topcord.xyz</a>
     */
    TOPCORD_XYZ("topcord.xyz", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://voidbots.net" target="_blank">voidbots.net</a>
     * 
     * @since 6.3.0
     */
    VOIDBOTS_NET("voidbots.net", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://wonderbotlist.com" target="_blank">wonderbotlist.com</a>
     */
    WONDERBOTLIST_COM("wonderbotlist.com", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST}),
    
    /**
     * <a href="https://yabl.xyz" target="_blank">yabl.xyz</a>
     *
     * @since 2.1.1
     */
    YABL_XYZ("yabl.xyz", new HttpMethod[]{HttpMethod.GET, HttpMethod.POST});
    
    private final String site;
    
    private final List<HttpMethod> methods;
    
    Site(String site, HttpMethod[] methods){
        this.site = site;
        
        this.methods = Arrays.asList(methods);
    }
    
    /**
     * Gives the ID of the selected site, which is used as ID in the BotBlock.org API.
     *
     * @return The selected site.
     */
    public String getSite(){
        return this.site;
    }
    
    /**
     * Returns if the Site supports GET requests for retrieving Bot information.
     * <br>This is only used in GetBotAction methods as the BotBlock API will return list information no matter what.
     * 
     * @return Whether this Site supports GET requests for retrieval of Bot information or not.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean supportsGet(){
        return methods.contains(HttpMethod.GET);
    }
    
    /**
     * Returns if the Site supports POST requests for submiting bot information.
     * <br>This is used for the {@link org.botblock.javabotblockapi.core.BotBlockAPI.Builder BotBlockAPI Builder}.
     * 
     * @return Whether this Site supports POST requests for submiting Bot information to them.
     */
    public boolean supportsPost(){
        return methods.contains(HttpMethod.POST);
    }
    
    /**
     * Enum containing the different Http Request methods a bot list could have.
     */
    public enum HttpMethod{
        /**
         * The Bot List supports GETting information about a specific bot.
         */
        GET,
        
        /**
         * The Bot List supports POSTing information of a bot to their site.
         */
        POST
    }
}
