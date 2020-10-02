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
 * Annotation to mark an Object to be planned for removal.
 * <br>This is paired with the {@link java.lang.Deprecated Deprecated} and
 * {@link org.botblock.javabotblockapi.core.annotations.DeprecatedSince DeprecatedSince} annotations.
 * 
 * <p>This annotation will always contain the {@link #major() major}, {@link #minor()} and {@link #patch() patch} version
 * in which the annotated Object will be removed.
 * <br>For example will {@code @PlannedRemoval(major = 6, minor = 5, patch = 0)} indocate an Object for removal on version
 * 6.5.0.
 * 
 * @since 5.2.2
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.CONSTRUCTOR})
public @interface PlannedRemoval{
    
    /**
     * The major version for when the annotated Object will be removed.
     * 
     * @return The major version for when this Object will be removed.
     */
    int major();
    
    /**
     * The minor version for when the annotated Object will be removed.
     * 
     * @return The minor version for when this Object will be removed.
     */
    int minor();
    
    /**
     * The patch version for when the annotated Object will be removed.
     *
     * @return The path version for when this Object will be removed.
     */
    int patch();
}
