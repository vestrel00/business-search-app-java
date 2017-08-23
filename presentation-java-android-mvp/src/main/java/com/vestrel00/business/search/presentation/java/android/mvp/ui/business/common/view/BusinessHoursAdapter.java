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
import android.support.annotation.ColorRes;
import android.support.v4.content.res.ResourcesCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vestrel00.business.search.presentation.java.android.inject.PerFragment;
import com.vestrel00.business.search.presentation.java.android.mvp.R;
import com.vestrel00.business.search.presentation.java.model.BusinessHourModel;
import com.vestrel00.business.search.presentation.java.model.BusinessHoursModel;

import javax.inject.Inject;

/**
 * An adapter that binds a {@link BusinessHoursModel} to a set of views.
 */
@PerFragment
final class BusinessHoursAdapter {

    private final BusinessHourAdapter hourAdapter;
    private final Resources resources;

    @Inject
    BusinessHoursAdapter(BusinessHourAdapter hourAdapter, Resources resources) {
        this.hourAdapter = hourAdapter;
        this.resources = resources;
    }

    void bindHours(ViewGroup hoursContainer, BusinessHoursModel hoursModel) {
        for (BusinessHourModel hourModel : hoursModel.hourModels()) {
            hourAdapter.bindHour(hoursContainer, hourModel);
        }
    }

    void bindOpenIndicator(TextView openIndicator, BusinessHoursModel hoursModel) {
        boolean openNow = hoursModel.openNow();
        openIndicator.setText(openNow ? R.string.open : R.string.closed);

        @ColorRes int colorRes = openNow ? android.R.color.holo_green_dark
                : android.R.color.holo_red_dark;
        openIndicator.setTextColor(ResourcesCompat.getColor(resources, colorRes, null));
    }
}
