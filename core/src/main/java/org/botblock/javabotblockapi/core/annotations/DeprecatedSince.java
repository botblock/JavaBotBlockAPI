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
 * <br>This is always paired with the {@link Deprecated @Deprecated} annotation.
 * 
 * <p>A replacement may be mentioned with the {@link #replacements() replacements String array} but is not guaranteed.
 * <br>Anything annotated with this should be avoided as it may be removed in a future release.
 * 
 * @since 3.2.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.CONSTRUCTOR})
public @interface DeprecatedSince{
    
    /**
     * Since what version this method or field is deprecated.
     * <br>This is field is required!
     * 
     * @return The version since when this object is deprecated.
     */
    String version();
    
    /**
     * Optional String indicating a possible replacement method or field to use.
     * 
     * @return The method/field to use instead of the deprecated one.
     */
    String[] replacements() default {""};
}
