package com.andre601.javabotblockapi;

/**
 * Enum class with all currently supported sites of BotBlock.org.
 * <br>May be used in {@link com.andre601.javabotblockapi.BotBlockAPI BotBlockAPI} or
 * {@link com.andre601.javabotblockapi.RequestHandler RequestHandler}.
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
     * <a href="https://discordapps.dev" target="_blank">discordapps.dev</a>
     */
    DISCORDAPPS_DEV("discordapps.dev"),
    /**
     * <a href="https://discord.boats" target="_blank">discord.boats</a>
     */
    DISCORD_BOATS("discord.boats"),
    /**
     * <a href="https://discordbots.org" target="_blank">discordbots.org</a>
     */
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
     * <a href="https://discordbotlist.us.to" target="_blank">discordbotlist.us.to</a>
     */
    DISCORDBOTLIST_US_TO("discordbotlist.us.to"),
    /**
     * <a href="https://discordsbestbots.xyz" target="_blank">discordsbestbots.xyz</a>
     */
    DISCORSDBESTBOTS_XYZ("discordsbestbots.xyz"),
    /**
     * <a href="https://discordbots.fun" target="_blank">discordbots.fun</a>
     */
    DISCORDBOTS_FUN("discordbots.fun"),
    /**
     * <a href="https://divinediscordbots.com" target="_blank">divinediscordbots.com</a>
     */
    DIVINEDISCORDBOTS_COM("divinediscordbots.com"),
    /**
     * <a href="https://lbots.org" target="_blank">lbots.org</a>
     */
    LBOTS_ORG("lbots.org"),
    /**
     * <a href="https://mythicalbots.xyz" target="_blank">mythicalbots.xyz</a>
     */
    MYTHICALBOTS_XYZ("mythicalbots.xyz"),
    /**
     * <a href="https://wonderbotlist.com" target="_blank">wonderbotlist.com</a>
     */
    WONDERBOTLIST_COM("wonderbotlist.com");

    private String site;

    Site(String site){
        this.site = site;
    }

    /**
     * Gets the site that was selected.
     *
     * @return The selected site.
     */
    public String getSite(){
        return this.site;
    }
}
