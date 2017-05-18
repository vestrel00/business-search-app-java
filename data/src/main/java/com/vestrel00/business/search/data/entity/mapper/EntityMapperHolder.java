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

import com.vestrel00.business.search.data.entity.BusinessEntity;
import com.vestrel00.business.search.data.entity.BusinessHourEntity;
import com.vestrel00.business.search.data.entity.BusinessHoursEntity;
import com.vestrel00.business.search.data.entity.BusinessTransactionTypeEntity;
import com.vestrel00.business.search.data.entity.CoordinatesEntity;
import com.vestrel00.business.search.data.entity.LocationEntity;
import com.vestrel00.business.search.domain.Business;
import com.vestrel00.business.search.domain.BusinessHour;
import com.vestrel00.business.search.domain.BusinessHours;
import com.vestrel00.business.search.domain.BusinessTransactionType;
import com.vestrel00.business.search.domain.Coordinates;
import com.vestrel00.business.search.domain.Location;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Holds instances of {@link EntityMapper}s.
 */
@Singleton
public final class EntityMapperHolder {

    private final EntityMapper<BusinessEntity, Business> businessEntityMapper;
    private final EntityMapper<BusinessTransactionTypeEntity, BusinessTransactionType>
            businessTransactionTypeEntityMapper;
    private final EntityMapper<BusinessHoursEntity, BusinessHours> businessHoursEntityMapper;
    private final EntityMapper<BusinessHourEntity, BusinessHour> businessHourEntityMapper;
    private final EntityMapper<LocationEntity, Location> locationEntityMapper;
    private final EntityMapper<CoordinatesEntity, Coordinates> coordinatesEntityMapper;

    @Inject
    EntityMapperHolder(EntityMapper<BusinessEntity, Business> businessEntityMapper,
                       EntityMapper<BusinessTransactionTypeEntity, BusinessTransactionType>
                               businessTransactionTypeEntityMapper,
                       EntityMapper<BusinessHoursEntity, BusinessHours> businessHoursEntityMapper,
                       EntityMapper<BusinessHourEntity, BusinessHour> businessHourEntityMapper,
                       EntityMapper<LocationEntity, Location> locationEntityMapper,
                       EntityMapper<CoordinatesEntity, Coordinates> coordinatesEntityMapper) {
        this.businessEntityMapper = businessEntityMapper;
        this.businessTransactionTypeEntityMapper = businessTransactionTypeEntityMapper;
        this.businessHoursEntityMapper = businessHoursEntityMapper;
        this.businessHourEntityMapper = businessHourEntityMapper;
        this.locationEntityMapper = locationEntityMapper;
        this.coordinatesEntityMapper = coordinatesEntityMapper;
    }

    public EntityMapper<BusinessEntity, Business> businessEntityMapper() {
        return businessEntityMapper;
    }

    public EntityMapper<BusinessTransactionTypeEntity, BusinessTransactionType>
    businessTransactionTypeEntityMapper() {
        return businessTransactionTypeEntityMapper;
    }

    public EntityMapper<BusinessHoursEntity, BusinessHours> businessHoursEntityMapper() {
        return businessHoursEntityMapper;
    }

    public EntityMapper<BusinessHourEntity, BusinessHour> businessHourEntityMapper() {
        return businessHourEntityMapper;
    }

    public EntityMapper<LocationEntity, Location> locationEntityMapper() {
        return locationEntityMapper;
    }

    public EntityMapper<CoordinatesEntity, Coordinates> coordinatesEntityMapper() {
        return coordinatesEntityMapper;
    }
}
