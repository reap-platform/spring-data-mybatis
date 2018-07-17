/*
 * Copyright (c) 2018-present the original author or authors.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.springframework.data.mybatis.utils;

import java.lang.reflect.Field;

import org.springframework.lang.Nullable;

/**
 * Spring Data Mybatis specific reflection utility methods and classes.
 * 
 * @author 7cat
 * @since 1.0
 */
public final class ReflectionUtils {

	/**
	 * Sets the given field on the given object to the given value if the field old value is null. Will make sure the
	 * given field is accessible.
	 *
	 * @param field must not be {@literal null}.
	 * @param target must not be {@literal null}.
	 * @param value
	 */
	public static void setFieldIfNull(Field field, Object target, @Nullable Object value) {
		org.springframework.util.ReflectionUtils.makeAccessible(field);
		try {
			if (field.get(target) == null) {
				org.springframework.util.ReflectionUtils.setField(field, target, value);
			}
		} catch (IllegalAccessException ex) {
			org.springframework.util.ReflectionUtils.handleReflectionException(ex);
			throw new IllegalStateException(
					"Unexpected reflection exception - " + ex.getClass().getName() + ": " + ex.getMessage());
		}
	}
}
