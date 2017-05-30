/*
 * Copyright 2017 Vandolf Estrellado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vestrel00.business.search.util;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 * Provides utility methods for Strings.
 */
@Singleton
public final class StringUtils {

    private final Locale locale;

    @Inject
    StringUtils(Locale locale) {
        this.locale = locale;
    }

    public String format(@NonNull String format, @NonNull Object... args) {
        return String.format(locale, format, args);
    }

    public String join(@NonNull List<String> strings, @NonNull String separator) {
        StringBuilder joined = new StringBuilder();
        for (String string : strings) {
            joined.append(string);
            joined.append(separator);
        }

        if (joined.length() > 0) {
            // delete the last occurrence of the separator
            int indexToDelete = joined.lastIndexOf(separator);
            joined.deleteCharAt(indexToDelete);
        }

        return joined.toString();
    }

    public boolean isEmpty(@Nullable String string) {
        return string == null || string.isEmpty();
    }

    public boolean allEmpty(@NonNull String... strings) {
        for (String string : strings) {
            if (!isEmpty(string)) {
                return false;
            }
        }
        return true;
    }

    public boolean onlyDigits(@NonNull String string) {
        return string.matches("\\d+");
    }
}
