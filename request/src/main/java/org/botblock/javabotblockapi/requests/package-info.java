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
 * This is the Request module which is used to provide support for POST and GET requests to/from the BotBlock API.
 * <br>When posting the Guild count is this module a requirement! It depends on the core module.
 *
 * <h1>Installation</h1>
 * Please replace {@code API_VERSION} with the latest release on Bintray.
 *
 * <h2>Gradle (recommended)</h2>
 *
 * <pre><code>
 * repositories{
 *     maven{ url = 'https://repo.codemc.io/repository/maven-public' }
 * }
 *
 * dependencies{
 *     compile group: 'org.botblock', name: 'javabotblockapi-core', version: 'API_VERSION'
 *     compile group: 'org.botblock', name: 'javabotblockapi-request', version: 'API_VERSION'
 * }
 * </code></pre>
 *
 * <h2>Maven</h2>
 *
 * <pre><code>{@literal
 * <repositories>
 *     <repository>
 *         <id>codemc</id>
 *         <name>CodeMC-Nexus</name>
 *         <url>https://repo.codemc.io/repository/maven-public</url>
 *     </repository>
 * </repositories>
 *
 * <dependencies>
 *     <dependency>
 *         <groupId>org.botblock</groupId>
 *         <artifactId>javabotblockapi-core</artifactId>
 *         <version>API_VERSION</version>
 *     </dependency>
 *     <dependency>
 *         <groupId>org.botblock</groupId>
 *         <artifactId>javabotblockapi-request</artifactId>
 *         <version>API_VERSION</version>
 *     </dependency>
 * </dependencies>
 * }</code></pre>
 *
 * <h2>Manual</h2>
 * We do not recommend using jar files directly and instead use one of the above dependency management systems.
 *
 * <p>If you still want to do it manually, or can't use one of the other option, head over to the
 * <a target="_blank" href="https://github.com/botblock/JavaBotBlockAPI/releases/latest">GitHub releases page</a> or to
 * the <a target="_blank" href="https://bintray.com/beta/#/andre601/maven/JavaBotBlockAPI?tab=overview">Bintray release page</a>
 * and download the jar files from there.
 *
 * <p>Note that you will not receive any support when using this method.
 */
package org.botblock.javabotblockapi.requests;