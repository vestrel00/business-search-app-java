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

package com.vestrel00.business.search.presentation.java.android.mvp.ui.business.common.view;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vestrel00.business.search.presentation.java.android.inject.PerFragment;
import com.vestrel00.business.search.presentation.java.android.mvp.R;
import com.vestrel00.business.search.presentation.java.model.BusinessHourModel;
import com.vestrel00.business.search.presentation.java.util.BusinessDayToDayOfWeekMapper;
import com.vestrel00.business.search.commons.CalendarFactory;
import com.vestrel00.business.search.commons.TimeUtils;

import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

/**
 * An adapter that binds a {@link BusinessHourModel} to a given {@link ViewGroup}.
 */
@PerFragment
final class BusinessHourAdapter {

    private final BusinessDayToDayOfWeekMapper businessDayToDayOfWeekMapper;
    private final CalendarFactory calendarFactory;
    private final TimeUtils timeUtils;
    private final LayoutInflater layoutInflater;
    private final Resources resources;
    private final Locale locale;

    @Inject
    BusinessHourAdapter(BusinessDayToDayOfWeekMapper businessDayToDayOfWeekMapper,
                        CalendarFactory calendarFactory, TimeUtils timeUtils,
                        LayoutInflater layoutInflater, Resources resources, Locale locale) {
        this.businessDayToDayOfWeekMapper = businessDayToDayOfWeekMapper;
        this.calendarFactory = calendarFactory;
        this.timeUtils = timeUtils;
        this.layoutInflater = layoutInflater;
        this.resources = resources;
        this.locale = locale;
    }

    void bindHour(ViewGroup hoursContainer, BusinessHourModel hourModel) {
        String openHours = resources.getString(R.string.open_hours, getDayOfWeek(hourModel),
                getStartTime(hourModel), getEndTime(hourModel));

        TextView hourTextView = (TextView) layoutInflater.inflate(R.layout.business_hour,
                hoursContainer);
        hourTextView.setText(openHours);
    }

    private String getDayOfWeek(BusinessHourModel hourModel) {
        Calendar calendar = calendarFactory.create();
        calendar.set(Calendar.DAY_OF_WEEK, businessDayToDayOfWeekMapper.map(hourModel.day()));
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, locale);
    }

    private String getStartTime(BusinessHourModel hourModel) {
        return formatTime(hourModel.start());
    }

    private String getEndTime(BusinessHourModel hourModel) {
        return formatTime(hourModel.end());
    }

    private String formatTime(String raw24hrStr) {
        return timeUtils.fromRaw24hrToAmPm(raw24hrStr);
    }
}
