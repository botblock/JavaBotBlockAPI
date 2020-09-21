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
package org.botblock.javabotblockapi.core.exceptions;

import org.json.JSONObject;

/**
 * Indicates that the Wrapper got rate limited by the BotBlock API.
 * <br>Use {@link #getDelay() getDelay()} to find out how long you have to wait until you can perform another request
 * towards {@link #getRoute() the targeted route}.
 */
public class RatelimitedException extends RuntimeException{
    private final int delay;
    private final String botId;
    private final String ip;
    private final String route;

    public RatelimitedException(String response){
        JSONObject json = new JSONObject(response);

        this.delay = json.getInt("retry_after");
        this.botId = json.getString("ratelimit_bot_id");
        this.ip = json.getString("ratelimit_ip");
        this.route = json.getString("ratelimit_route");
    }
    
    /**
     * Returns the delay - in milliseconds - you have to wait to perform a request again.
     * 
     * @return The delay you have to wait in milliseconds
     */
    public int getDelay(){
        return delay;
    }
    
    /**
     * Returns the bot id that was rate limited.
     * 
     * @return The id of the bot that was rate limited
     */
    public String getBotId(){
        return botId;
    }
    
    /**
     * Returns the ip that was rate limited.
     * 
     * @return The ip that was rate limited
     */
    public String getIp(){
        return ip;
    }
    
    /**
     * Returns the route on which the bot was rate limited.
     * 
     * @return The route on which the bot was rate limited
     */
    public String getRoute(){
        return route;
    }
    
    /**
     * Returns this class formatted to a String.
     *
     * @return {@code RatelimitedException{delay=<delay>, botId=<botId>, ip=<ip>, route=<route>}}
     */
    @Override
    public String toString(){
        return "RatelimitedException{"
                + "delay=" + delay + ", "
                + "botId=" + botId + ", "
                + "ip="    + ip    + ", "
                + "route=" + route + ", "
                + "}";
    }
    
    /**
     * Returns a message informing us about {@link #getRoute() where} we got rate limited, {@link #getDelay() for how long} and
     * on what {@link #getBotId() bot id} and {@link #getIp() ip}.
     *
     * @return String containing a message with route, delay, bot id and IP.
     */
    @Override
    public String getMessage(){
        return "Got rate limited on route" + route + " for " + delay + "ms with bot id " + botId + " (ip: " + ip + ")";
    }
}
