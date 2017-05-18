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

package com.vestrel00.business.search.data.entity.mapper;

import com.vestrel00.business.search.data.entity.BusinessHourEntity;
import com.vestrel00.business.search.data.entity.BusinessHoursEntity;
import com.vestrel00.business.search.domain.BusinessHour;
import com.vestrel00.business.search.domain.BusinessHours;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Maps {@link BusinessHoursEntity} to {@link BusinessHours} and vice versa.
 */
@Singleton
final class BusinessHoursEntityMapper implements EntityMapper<BusinessHoursEntity, BusinessHours> {

    private final EntityMapper<BusinessHourEntity, BusinessHour> hoursEntityMapper;
    private final EntityListMapper entityListMapper;

    @Inject
    BusinessHoursEntityMapper(EntityMapper<BusinessHourEntity, BusinessHour> hoursEntityMapper,
                              EntityListMapper entityListMapper) {
        this.hoursEntityMapper = hoursEntityMapper;
        this.entityListMapper = entityListMapper;
    }

    @Override
    public BusinessHours map(BusinessHoursEntity hoursEntity) {
        return BusinessHours.builder()
                .openNow(hoursEntity.openNow())
                .hours(entityListMapper.mapToV(hoursEntityMapper, hoursEntity.hourEntities()))
                .build();
    }

    @Override
    public BusinessHoursEntity map(BusinessHours hours) {
        return BusinessHoursEntity.builder()
                .openNow(hours.openNow())
                .hourEntities(entityListMapper.mapToK(hoursEntityMapper, hours.hours()))
                .build();
    }
}
