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
package org.botblock.javabotblockapi.exceptions;

import org.json.JSONObject;

/**
 * Class used to indicate when the bot gets Ratelimited by the BotBlock API.
 * <br>You can use {@link #getDelay() getDelay()} to find out how long you have to wait before you can access
 * {@link #getRoute() the route} again.
 */
public class RatelimitedException extends RuntimeException{
    private int delay;
    private String botId;
    private String ip;
    private String route;

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
     * Returns the bot id that was ratelimited.
     * 
     * @return The id of the bot that was ratelimited
     */
    public String getBotId(){
        return botId;
    }
    
    /**
     * Returns the ip that was ratelimited.
     * 
     * @return The ip that was ratelimited
     */
    public String getIp(){
        return ip;
    }
    
    /**
     * Returns the route on which the bot was ratelimited.
     * 
     * @return The route on which the bot was ratelimited
     */
    public String getRoute(){
        return route;
    }
    
    /**
     * Returns this class formatted to a String.
     *
     * @return {@code RatelimitedException{@literal {delay=<delay>, bot_id=<bot_id>, ip=<ip>, route=<route>}}}
     */
    @Override
    public String toString(){
        return String.format(
                "RatelimitedException{delay=%d, botId=%s, ip=%s, route=%s}",
                delay,
                botId,
                ip,
                route
        );
    }
    
    /**
     * Returns a formatted message displaying the various information returned in this exception.
     * <br>This essentially calles {@link #toString() toString()} in this class.
     *
     * @return Same output as {@link #toString() toString()}
     */
    @Override
    public String getMessage(){
        return toString();
    }
}
