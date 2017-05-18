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

import com.vestrel00.business.search.domain.Business;
import com.vestrel00.business.search.domain.BusinessHour;
import com.vestrel00.business.search.domain.BusinessHours;
import com.vestrel00.business.search.domain.BusinessTransactionType;
import com.vestrel00.business.search.domain.Coordinates;
import com.vestrel00.business.search.domain.Location;
import com.vestrel00.business.search.presentation.java.model.BusinessHourModel;
import com.vestrel00.business.search.presentation.java.model.BusinessHoursModel;
import com.vestrel00.business.search.presentation.java.model.BusinessModel;
import com.vestrel00.business.search.presentation.java.model.BusinessTransactionTypeModel;
import com.vestrel00.business.search.presentation.java.model.CoordinatesModel;
import com.vestrel00.business.search.presentation.java.model.LocationModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Holds instances of {@link ModelMapper}s.
 */
@Singleton
public final class ModelMapperHolder {

    private final ModelMapper<BusinessModel, Business> businessModelMapper;
    private final ModelMapper<BusinessTransactionTypeModel, BusinessTransactionType>
            businessTransactionTypeModelMapper;
    private final ModelMapper<BusinessHoursModel, BusinessHours> businessHoursModelMapper;
    private final ModelMapper<BusinessHourModel, BusinessHour> businessHourModelMapper;
    private final ModelMapper<LocationModel, Location> locationModelMapper;
    private final ModelMapper<CoordinatesModel, Coordinates> coordinatesEntityMapper;

    @Inject
    ModelMapperHolder(ModelMapper<BusinessModel, Business> businessModelMapper,
                      ModelMapper<BusinessTransactionTypeModel, BusinessTransactionType>
                              businessTransactionTypeModelMapper,
                      ModelMapper<BusinessHoursModel, BusinessHours> businessHoursModelMapper,
                      ModelMapper<BusinessHourModel, BusinessHour> businessHourModelMapper,
                      ModelMapper<LocationModel, Location> locationModelMapper,
                      ModelMapper<CoordinatesModel, Coordinates> coordinatesEntityMapper) {
        this.businessModelMapper = businessModelMapper;
        this.businessTransactionTypeModelMapper = businessTransactionTypeModelMapper;
        this.businessHoursModelMapper = businessHoursModelMapper;
        this.businessHourModelMapper = businessHourModelMapper;
        this.locationModelMapper = locationModelMapper;
        this.coordinatesEntityMapper = coordinatesEntityMapper;
    }

    public ModelMapper<BusinessModel, Business> businessModelMapper() {
        return businessModelMapper;
    }

    public ModelMapper<BusinessTransactionTypeModel, BusinessTransactionType>
    businessTransactionTypeModelMapper() {
        return businessTransactionTypeModelMapper;
    }

    public ModelMapper<BusinessHoursModel, BusinessHours> businessHoursModelMapper() {
        return businessHoursModelMapper;
    }

    public ModelMapper<BusinessHourModel, BusinessHour> businessHourModelMapper() {
        return businessHourModelMapper;
    }

    public ModelMapper<LocationModel, Location> locationModelMapper() {
        return locationModelMapper;
    }

    public ModelMapper<CoordinatesModel, Coordinates> coordinatesModelMapper() {
        return coordinatesEntityMapper;
    }
}
