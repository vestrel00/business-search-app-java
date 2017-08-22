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

package com.vestrel00.business.search.commons;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.NonNull;

/**
 * Provides utility methods for time.
 */
@Singleton
public final class TimeUtils {

    private static final String RAW_24_HOUR_FORMAT = "HHmm";
    private static final String AM_PM_FORMAT = "hh:mm a";

    private final CalendarFactory calendarFactory;
    private final StringUtils stringUtils;
    private final Locale locale;

    @Inject
    TimeUtils(CalendarFactory calendarFactory, StringUtils stringUtils, Locale locale) {
        this.calendarFactory = calendarFactory;
        this.stringUtils = stringUtils;
        this.locale = locale;
    }

    /**
     * Convert the given raw 24-hour time string to am/pm format.
     * <p>
     * E.G.
     * <ul>
     *      <li>0000 -> 12:00 AM</li>
     *      <li>1200 -> 12:00 PM</li>
     *      <li>2130 -> 9:30 PM</li>
     * </ul>
     *
     * @param raw24hrStr time string in 24-hour clock notation. See {@link #RAW_24_HOUR_FORMAT}.
     * @return the am/pm notation of the raw 24-hour string. See {@link #AM_PM_FORMAT}.
     */
    public String fromRaw24hrToAmPm(@NonNull String raw24hrStr) {
        if (!stringUtils.onlyDigits(raw24hrStr)
                || raw24hrStr.length() != RAW_24_HOUR_FORMAT.length()) {
            throw new IllegalArgumentException("Raw 24-hour time string " + raw24hrStr
                    + " must be in the format of " + RAW_24_HOUR_FORMAT);
        }

        int hourOfDay = Integer.parseInt(raw24hrStr.substring(0, 2));
        int minuteOfHour = Integer.parseInt(raw24hrStr.substring(2, 4));

        Calendar calendar = calendarFactory.create();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minuteOfHour);

        Date date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(AM_PM_FORMAT, locale);

        return formatter.format(date);
    }
}
