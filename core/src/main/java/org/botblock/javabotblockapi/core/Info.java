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
 * Class containing general information about the project.
 * 
 * <p>The class can be used for things such as determining the {@link #VERSION used version} or to get some static info 
 * like the {@link #GITHUB GitHub} or {@link #JENKINS Jenkins CI} URL.
 * 
 * @since 6.6.0
 */
public class Info{
    
    /**
     * Major version of the Wrapper.
     */
    public static final int MAJOR = 6;
    /**
     * Minor version of the Wrapper.
     */
    public static final int MINOR = 7;
    /**
     * Patch version of the Wrapper.
     */
    public static final int PATCH = 1;
    
    /**
     * Full version in the format {@code major.minor.patch}.
     */
    public static final String VERSION = String.format("%d.%d.%d", MAJOR, MINOR, PATCH);
    
    /**
     * URL to the GitHub repository.
     */
    public static final String GITHUB = "https://github.com/botblock/JavaBotBlockAPI";
    
    public static final String JENKINS = "https://ci.codemc.io/job/botblock/job/JavaBotBlockAPI";
}
