/*
 * Copyright 2018 Vandolf Estrellado
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

package com.vestrel00.business.search.presentation.java.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps a business day, whose first day of the week is a {@link Calendar#MONDAY}, to a
 * {@link Calendar#DAY_OF_WEEK}, whose first day of the week is a {@link Calendar#SUNDAY}.
 */
@Reusable
public final class BusinessDayToDayOfWeekMapper {

    private static final List<Integer> DAY_OF_WEEK;

    static {
        DAY_OF_WEEK = new ArrayList<>(7);
        DAY_OF_WEEK.add(Calendar.MONDAY);
        DAY_OF_WEEK.add(Calendar.TUESDAY);
        DAY_OF_WEEK.add(Calendar.WEDNESDAY);
        DAY_OF_WEEK.add(Calendar.THURSDAY);
        DAY_OF_WEEK.add(Calendar.FRIDAY);
        DAY_OF_WEEK.add(Calendar.SATURDAY);
        DAY_OF_WEEK.add(Calendar.SUNDAY);
    }

    @Inject
    BusinessDayToDayOfWeekMapper() {
    }

    public int map(int businessDay) {
        return DAY_OF_WEEK.get(businessDay);
    }
}
