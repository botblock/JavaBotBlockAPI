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

/**
 * Enum class containing all sites currently supported by BotBlock.org.
 *
 * @since 2.1.0
 */
public enum Site {
    
    /**
     * <a href="https://arcane-center.xyz" target="_blank">arcane-center.xyz</a>
     */
    ARCANE_CENTER_XYZ("arcane-center.xyz", true, true),
    
    /**
     * <a href="https://blist.xyz" target="_blank">blist.xyz</a>
     */
    BLIST_XYZ("blist.xyz", true, true),
    
    /**
     * <a href="https://botlist.space" target="_blank">botlist.space</a>
     */
    BOTLIST_SPACE("botlist.space", true, true),
    
    /**
     * <a href="https://botsdatabase.com" target="_blank">botsdatabase.com</a>
     */
    BOTSDATABASE_COM("botsdatabase.com", true, true),
    
    /**
     * <a href="https://bots.discordlabs.org" target="_blank">bots.discordlabs.org</a>
     */
    BOTS_DISCORDLABS_ORG("bots.discordlabs.org", true, true),
    
    /**
     * <a href="https://botsfordiscord.com" target="_blank">botsfordiscord.com</a>
     */
    BOTSFORDISCORD_COM("botsfordiscord.com", true, true),
    
    /**
     * <a href="https://bots.ondiscord.xyz" target="_blank">bots.ondiscord.xyz</a>
     */
    BOTS_ONDISCORD_XYZ("bots.ondiscord.xyz", true, true),
    
    /**
     * <a href="https://dblista.pl" target="_blank">dblista.pl</a>
     */
    DBLISTA_PL("dblista.pl", true, true),
    
    /**
     * <a href="https://discordapps.dev" target="_blank">discordapps.dev</a>
     */
    DISCORDAPPS_DEV("discordapps.dev", true, true),
    
    /**
     * <a href="https://discord.boats" target="_blank">discord.boats</a>
     */
    DISCORD_BOATS("discord.boats", true, true),
    
    /**
     * <a href="https://discordbotdirectory.net" target="_blank">discordbotdirectory.net</a>
     * 
     * @since 6.3.0
     */
    DISCORDBOTDIRECTORY_NET("discordbotdirectory.net", true, false),
    
    /**
     * <a href="https://discordbotlist.com" target="_blank">discordbotlist.com</a>
     */
    DISCORDBOTLIST_COM("discordbotlist.com", true, true),
    
    /**
     * <a href="https://discordbots.co" target="_blank">discordbots.co</a>
     *
     * @since 5.2.3
     */
    DISCORDBOTS_CO("discordbots.co", true, true),
    
    /**
     * <a href="https://discord.bots.gg" target="_blank">discord.bots.gg</a>
     */
    DISCORD_BOTS_GG("discord.bots.gg", true, true),
    
    /**
     * <a href="https://discordbots.fun" target="_blank">discordbots.fun</a>
     */
    DISCORDBOTS_FUN("discordbots.fun", true, true),
    
    /**
     * <a href="https://discordextremelist.xyz" target="_blank">discordextremelist.xyz</a>
     *
     * @since 2.3.3
     */
    DISCORDEXTREMELIST_XYZ("discordextremelist.xyz", true, true),
    
    /**
     * <a href="https://discordlist.co" target="_blank">discordlist.co</a>
     */
    DISCORDLIST_CO("discordlist.co", true, true),
    
    /**
     * <a href="https://discordlistology.com" target="_blank">discordlistology.com</a>
     *
     * @since 5.2.1
     */
    DISCORDLISTOLOGY_COM("discordlistology.com", true, true),
    
    /**
     * <a href="https://disforge.com/bots" target="_blank">disforge.com</a>
     * 
     * @since 6.2.2
     */
    DISFORGE_COM("disforge.com", true, true),
    
    /**
     * <a href="https://glennbotlist.xyz" target="_blank">glennbotlist.xyz</a>
     */
    GLENNBOTLIST_XYZ("glennbotlist.xyz", true, true),
    
    /**
     * <a href="https://hydrogenbots.club" target="_blank">hydrogenbots.club</a>
     * 
     * @since 6.2.1
     */
    HYDROGENBOTS_CLUB("hydrogenbots.club", true, true),
    
    /**
     * <a href="https://mythicalbots.xyz" target="_blank">mythicalbots.xyz</a>
     */
    MYTHICALBOTS_XYZ("mythicalbots.xyz", true, true),
    
    /**
     * <a href="https://space-bot-list.xyz" target="_blank">space-bot-list.xyz</a>
     */
    SPACE_BOT_LIST_XYZ("space-bot-list.xyz", true, true),
    
    /**
     * <a href="https://topcord.xyz" target="_blank">topcord.xyz</a>
     */
    TOPCORD_XYZ("topcord.xyz", true, true),
    
    /**
     * <a href="https://voidbots.net" target="_blank">voidbots.net</a>
     * 
     * @since 6.3.0
     */
    VOIDBOTS_NET("voidbots.net", true, true),
    
    /**
     * <a href="https://wonderbotlist.com" target="_blank">wonderbotlist.com</a>
     */
    WONDERBOTLIST_COM("wonderbotlist.com", true, true),
    
    /**
     * <a href="https://yabl.xyz" target="_blank">yabl.xyz</a>
     *
     * @since 2.1.1
     */
    YABL_XYZ("yabl.xyz", true, true);
    
    private final String site;
    
    private final boolean hasGet;
    private final boolean hasPost;
    
    Site(String site, boolean hasGet, boolean hasPost){
        this.site = site;
        
        this.hasGet = hasGet;
        this.hasPost = hasPost;
    }
    
    /**
     * Gives the ID of the selected site, which is used as ID in the BotBlock.org API.
     *
     * @return The selected site.
     */
    public String getSite(){
        return this.site;
    }
    
    public boolean supportsGet(){
        return hasGet;
    }
    
    public boolean supportsPost(){
        return hasPost;
    }
}
