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

package org.botblock.javabotblockapi.core.annotations;

import java.lang.annotation.*;

/**
 * Annotation used to indicate since when an Object is deprecated.
 * <br>This is always paired with the {@link java.lang.Deprecated @Deprecated} annotation.
 * 
 * <p>A replacement may be mentioned with the {@link #replacements() replacements String array} but is not guaranteed.
 * <br>Anything annotated with this should be avoided as it may be removed in a future release.
 * 
 * <p>When a removal is planned will a {@link org.botblock.javabotblockapi.core.annotations.PlannedRemoval PlannedRemoval}
 * annotation be added to indicate the version of the Object's removal.
 * 
 * @since 3.2.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.CONSTRUCTOR})
public @interface DeprecatedSince{
    
    /**
     * The Major version since when the annotated object is deprecated.
     * 
     * @return int representing the major version.
     * 
     * @since 6.3.0
     */
    int major();
    
    /**
     * The Minor version since when the annotated object is deprecated.
     *
     * @return int representing the minor version.
     *
     * @since 6.3.0
     */
    int minor();
    
    /**
     * The Patch version since when the annotated object is deprecated.
     *
     * @return int representing the patch version.
     *
     * @since 6.3.0
     */
    int patch();
    
    /**
     * Optional String array indicating one or multiple possible replacement Objects to use.
     * 
     * @return The Object(s) to use instead of the deprecated one.
     */
    String[] replacements() default {""};
}
