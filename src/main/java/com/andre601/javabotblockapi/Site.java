/*
 * Copyright 2019 Andre601
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
package com.andre601.javabotblockapi;

import com.andre601.javabotblockapi.annotations.DeprecatedSince;
import com.andre601.javabotblockapi.annotations.ReplacedWith;

/**
 * Enum class containing all sites currently supported by BotBlock.org.
 * 
 * <p>Those names can be used in methods of either the {@link com.andre601.javabotblockapi.BotBlockAPI BotBlockAPI},
 * the {@link com.andre601.javabotblockapi.requests.GetAction GetAction} or the 
 * {@link com.andre601.javabotblockapi.requests.PostAction PostAction} class.
 * 
 * @since v2.1.0
 */
public enum Site {
    /**
     * <a href="https://botlist.space" target="_blank">botlist.space</a>
     */
    BOTLIST_SPACE("botlist.space"),
    
    /**
     * <a href="https://botsfordiscord.com" target="_blank">botsfordiscord.com</a>
     */
    BOTSFORDISCORD_COM("botsfordiscord.com"),
    
    /**
     * <a href="https://bots.ondiscord.xyz" target="_blank">bots.ondiscord.xyz</a>
     */
    BOTS_ONDISCORD_XYZ("bots.ondiscord.xyz"),
    
    /**
     * <a href="https://cloud-botlist.xyz" target="_blank">cloud-botlist.xyz</a>
     */
    CLOUD_BOTLIST_XYZ("cloud-botlist.xyz"),
    
    /**
     * <a href="https://discordapps.dev" target="_blank">discordapps.dev</a>
     */
    DISCORDAPPS_DEV("discordapps.dev"),
    
    /**
     * <a href="https://discord.boats" target="_blank">discord.boats</a>
     */
    DISCORD_BOATS("discord.boats"),
    
    /**
     * <a href="https://discordbots.org" target="_blank">discordbots.org</a>
     * 
     * @deprecated Use {@link #TOP_GG TOP_GG} insetad.
     *             <br>This option still works, but is no longer supported, nor recommended!
     */
    @Deprecated
    @ReplacedWith("TOP_GG")
    @DeprecatedSince("v3.0.1")
    DISCORDBOTS_ORG("discordbots.org"),
    
    /**
     * <a href="https://discordbotlist.com" target="_blank">discordbotlist.com</a>
     */
    DISCORDBOTLIST_COM("discordbotlist.com"),
    
    /**
     * <a href="https://discordbotreviews.xyz" target="_blank">discordbotreviews.xyz</a>
     */
    DISCORDBOTREVIEWS_XYZ("discordbotreviews.xyz"),
    
    /**
     * <a href="https://discordbot.world" target="_blank">discordbot.world</a>
     */
    DISCORDBOT_WORLD("discordbot.world"),
    
    /**
     * <a href="https://discord.bots.gg" target="_blank">discord.bots.gg</a>
     */
    DISCORD_BOTS_GG("discord.bots.gg"),
    
    /**
     * <a href="https://discordextremelist.xyz" target="_blank">discordextremelist.xyz</a>
     * 
     * @since v2.3.3
     */
    DISCORDEXTREMELIST_XYZ("discordextremelist.xyz"),
    
    /**
     * <a href="https://discordsbestbots.xyz" target="_blank">discordsbestbots.xyz</a>
     */
    DISCORDSBESTBOTS_XYZ("discordsbestbots.xyz"),
    
    /**
     * <a href="https://discordbots.fun" target="_blank">discordbots.fun</a>
     */
    DISCORDBOTS_FUN("discordbots.fun"),
    
    /**
     * <a href="https://divinediscordbots.com" target="_blank">divinediscordbots.com</a>
     */
    DIVINEDISCORDBOTS_COM("divinediscordbots.com"),
    
    /**
     * <a href="https://glennbotlist.xyz" target="_blank">glennbotlist.xyz</a>
     */
    GLENNBOTLIST_XYZ("glennbotlist.xyz"),
    
    /**
     * <a href="https://lbots.org" target="_blank">lbots.org</a>
     */
    LBOTS_ORG("lbots.org"),
    
    /**
     * <a href="https://mythicalbots.xyz" target="_blank">mythicalbots.xyz</a>
     */
    MYTHICALBOTS_XYZ("mythicalbots.xyz"),
    
    /**
     * <a href="https://top.gg" target="_blank">top.gg</a>
     * <br>Note that top.gg is discordbots.org (new domain).
     * 
     * @since v3.0.0
     */
    TOP_GG("top.gg"),
    
    /**
     * <a href="https://wonderbotlist.com" target="_blank">wonderbotlist.com</a>
     */
    WONDERBOTLIST_COM("wonderbotlist.com"),
    
    /**
     * <a href="https://yabl.xyz" target="_blank">yabl.xyz</a>
     * 
     * @since v2.1.1
     */
    YABL_XYZ("yabl.xyz");

    private String site;

    Site(String site){
        this.site = site;
    }

    /**
     * Gives the ID of the selected site, which is used as ID in the BotBlock.org API.
     *
     * @return The selected site.
     */
    public String getSite(){
        return this.site;
    }
}
