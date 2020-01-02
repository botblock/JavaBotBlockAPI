/*
 * Copyright 2020 Andre601
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
 * Used when the Wrapper gets rate-limited by the BotBlockAPI.
 */
public class RatelimitedException extends Throwable{
    private int delay;
    private String bot_id;
    private String ip;
    private String route;

    public RatelimitedException(String response){
        JSONObject json = new JSONObject(response);

        this.delay = json.getInt("retry_after");
        this.bot_id = json.getString("ratelimit_bot_id");
        this.ip = json.getString("ratelimit_ip");
        this.route = json.getString("ratelimit_route");
    }

    /**
     * Gives the exception message.
     *
     * @return The Exception message with route, IP, ID and when you can send again.
     */
    @Override
    public String getMessage(){
        return String.format(
                "Got ratelimited on route %s (IP: %s, Bot ID: %s). Can send again in %d seconds.",
                route,
                ip,
                bot_id,
                delay
        );
    }
}
