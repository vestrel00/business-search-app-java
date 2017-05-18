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
import com.vestrel00.business.search.domain.BusinessHour;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Maps {@link BusinessHourEntity} to {@link BusinessHour} and vice versa.
 */
@Singleton
final class BusinessHourEntityMapper implements EntityMapper<BusinessHourEntity, BusinessHour> {

    @Inject
    BusinessHourEntityMapper() {
    }

    @Override
    public BusinessHour map(BusinessHourEntity businessHourEntity) {
        return BusinessHour.builder()
                .day(businessHourEntity.day())
                .start(businessHourEntity.start())
                .end(businessHourEntity.end())
                .build();
    }

    @Override
    public BusinessHourEntity map(BusinessHour businessHour) {
        return BusinessHourEntity.builder()
                .day(businessHour.day())
                .start(businessHour.start())
                .end(businessHour.end())
                .build();
    }
}
