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

/**
 * This is the core module used across all other modules.
 * <br>When using either of the other modules is this one here required to be installed too.
 * 
 * <h1>Installation</h1>
 * When installing it, make sure to replace {@code {version}} from the examples below with the latest release.
 * 
 * <h2>Maven</h2>
 * <pre><code>
 * <repositories>
 *     <repository>
 *         <id>jcenter</id>
 *         <name>jcenter-bintray</name>
 *         <url>https://jcenter.binrtray.com</url>
 *     </repository>
 * </repositories>
 * 
 * <dependencies>
 *     <dependency>
 *         <groupId>org.botblock</groupId>
 *         <artifactId>JavaBotBlockAPI-core</artifactId>
 *         <version>{version}</version>
 *     </dependency>
 * </dependencies>
 * </code></pre>
 * 
 * <h2>Gradle (Recommendet)</h2>
 * <pre><code>
 * repositories{
 *     jcenter()
 * }
 * 
 * dependencies{
 *     compile 'org.botblock:JavaBotBlockAPI-core:{version}'
 * }
 * </code></pre>
 * 
 * <h2>Manual</h2>
 * We do not recommend using jar files directly and instead use one of the above dependency management systems.
 * <br>If you still want to do it manually, or can't use one of the other option, head over to the
 * <a target="_blank" href="https://github.com/botblock/JavaBotBlockAPI/releases/latest">GitHub releases page</a> or to
 * the <a target="_blank" href="https://bintray.com/beta/#/andre601/maven/JavaBotBlockAPI?tab=overview">Bintray release page</a>
 * and download the jar files from there.
 * 
 * <p>Note that you will not receive any support when using this method.
 */
package org.botblock.javabotblockapi.core;