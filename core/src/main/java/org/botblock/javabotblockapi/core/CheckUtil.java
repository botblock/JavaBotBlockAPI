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

import org.botblock.javabotblockapi.core.annotations.Get;
import org.botblock.javabotblockapi.core.annotations.Post;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Util class to perform basic checks.
 */
public class CheckUtil{
    
    /**
     * Will throw a {@link java.lang.NullPointerException NullPointerException} when the provided String is empty.
     * 
     * @param value
     *        The String to check.
     * @param name
     *        The name of the parameter checked.
     */
    public static void notEmpty(String value, String name){
        if(value.isEmpty())
            throw new NullPointerException(name + " may not be empty.");
    }
    
    /**
     * Will throw a {@link java.lang.NullPointerException NullPointerException} when the provided Map is empty.
     * 
     * @param value
     *        The Map to check.
     * @param name
     *        The name of the parameter checked.
     */
    public static void notEmpty(Map<?, ?> value, String name){
        if(value.isEmpty())
            throw new NullPointerException(name + " may not be empty.");
    }
    
    /**
     * Will throw a {@link java.lang.IllegalStateException IllegalStateException} when the provided expression returns true.
     * 
     * @param expression
     *        The expression to check against.
     * @param message
     *        The message to print in the Exception when thrown.
     */
    public static void condition(boolean expression, String message){
        if(expression)
            throw new IllegalStateException(message);
    }
    
    /**
     * Will throw a {@link java.lang.IllegalStateException IllegalStateException} when the provided
     * {@link org.botblock.javabotblockapi.core.Site Site instance} does not have the
     * {@link org.botblock.javabotblockapi.core.annotations.Post @Post annotation}.
     * 
     * @param site
     *        The Site instance to check.
     */
    public static void supportsPost(Site site){
        Field field = getField(site);
        
        condition(field == null || !field.isAnnotationPresent(Post.class), site.getSite() + " does not support POST!");
    }
    
    /**
     * Will throw a {@link java.lang.IllegalStateException IllegalStateException} when the provided
     * {@link org.botblock.javabotblockapi.core.Site Site instance} does not have the
     * {@link org.botblock.javabotblockapi.core.annotations.Get @Post annotation}.
     *
     * @param site
     *        The Site instance to check.
     */
    public static void supportsGet(Site site){
        Field field = getField(site);
    
        condition(field == null || !field.isAnnotationPresent(Get.class), site.getSite() + " does not support GET!");
    }
    
    private static Field getField(Site site){
        try{
            return site.getClass().getField(site.name());
        }catch(NoSuchFieldException ex){
            return null;
        }
    }
}
