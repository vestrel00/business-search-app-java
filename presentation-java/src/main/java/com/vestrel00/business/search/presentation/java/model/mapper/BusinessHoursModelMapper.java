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

package com.vestrel00.business.search.presentation.java.model.mapper;

import com.vestrel00.business.search.domain.BusinessHour;
import com.vestrel00.business.search.domain.BusinessHours;
import com.vestrel00.business.search.presentation.java.model.BusinessHourModel;
import com.vestrel00.business.search.presentation.java.model.BusinessHoursModel;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link BusinessHoursModel} to {@link BusinessHours} and vice versa.
 */
@Reusable
final class BusinessHoursModelMapper implements ModelMapper<BusinessHoursModel, BusinessHours> {

    private final ModelMapper<BusinessHourModel, BusinessHour> hoursModelMapper;
    private final ModelListMapper modelListMapper;

    @Inject
    BusinessHoursModelMapper(ModelMapper<BusinessHourModel, BusinessHour> hoursModelMapper,
                             ModelListMapper modelListMapper) {
        this.hoursModelMapper = hoursModelMapper;
        this.modelListMapper = modelListMapper;
    }

    @Override
    public BusinessHours map(BusinessHoursModel hoursModel) {
        return BusinessHours.builder()
                .openNow(hoursModel.openNow())
                .hours(modelListMapper.mapToV(hoursModelMapper, hoursModel.hourModels()))
                .build();
    }

    @Override
    public BusinessHoursModel map(BusinessHours hours) {
        return BusinessHoursModel.builder()
                .openNow(hours.openNow())
                .hourModels(modelListMapper.mapToK(hoursModelMapper, hours.hours()))
                .build();
    }
}
