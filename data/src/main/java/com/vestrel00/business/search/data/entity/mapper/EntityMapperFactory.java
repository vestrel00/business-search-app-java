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
import com.vestrel00.business.search.data.entity.CoordinatesEntity;
import com.vestrel00.business.search.data.entity.LocationEntity;
import com.vestrel00.business.search.domain.Business;
import com.vestrel00.business.search.domain.Coordinates;
import com.vestrel00.business.search.domain.Location;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Provides instances of {@link EntityMapper}.
 */
@Singleton
public final class EntityMapperFactory {

    private final EntityMapper<BusinessEntity, Business> businessEntityMapper;
    private final EntityMapper<LocationEntity, Location> locationEntityMapper;
    private final EntityMapper<CoordinatesEntity, Coordinates> coordinatesEntityMapper;

    @Inject
    EntityMapperFactory(EntityMapper<BusinessEntity, Business> businessEntityMapper,
                        EntityMapper<LocationEntity, Location> locationEntityMapper,
                        EntityMapper<CoordinatesEntity, Coordinates> coordinatesEntityMapper) {
        this.businessEntityMapper = businessEntityMapper;
        this.locationEntityMapper = locationEntityMapper;
        this.coordinatesEntityMapper = coordinatesEntityMapper;
    }

    public EntityMapper<BusinessEntity, Business> businessEntityMapper() {
        return businessEntityMapper;
    }

    public EntityMapper<LocationEntity, Location> locationEntityMapper() {
        return locationEntityMapper;
    }

    public EntityMapper<CoordinatesEntity, Coordinates> coordinatesEntityMapper() {
        return coordinatesEntityMapper;
    }
}
