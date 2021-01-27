/*
 * Copyright 2019 - 2021 Andre601
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
 * Class containing static info used in other modules.
 * <br>Feel free to use the info shared here inside your bot if you, for example, want to share what Lib your bot
 * uses to post Guild count or similar.
 * 
 * @since 6.4.0
 */
public class JavaBotBlockAPIInfo{
    
    /**
     * Major version of the Wrapper.
     */
    public static final String MAJOR = "6";
    /**
     * Minor version of the Wrapper.
     */
    public static final String MINOR = "4";
    /**
     * Patch version of the Wrapper.
     */
    public static final String PATCH = "1";
    
    /**
     * Full version in the format {@code major.minor.patch}.
     */
    public static final String VERSION = String.format("%s.%s.%s", MAJOR, MINOR, PATCH);
    
    /**
     * URL to the GitHub repository.
     */
    public static final String GITHUB = "https://github.com/botblock/JavaBotBlockAPI";
}
