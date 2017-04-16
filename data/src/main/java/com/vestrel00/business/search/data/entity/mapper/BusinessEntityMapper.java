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
import com.vestrel00.business.search.domain.Business;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Transforms from {@link BusinessEntity} to {@link Business} and vice versa.
 */
@Singleton
public final class BusinessEntityMapper {

    private final LocationEntityMapper locationEntityMapper;
    private final CoordinatesEntityMapper coordinatesEntityMapper;

    @Inject
    BusinessEntityMapper(LocationEntityMapper locationEntityMapper,
                         CoordinatesEntityMapper coordinatesEntityMapper) {
        this.locationEntityMapper = locationEntityMapper;
        this.coordinatesEntityMapper = coordinatesEntityMapper;
    }

    public Business map(BusinessEntity businessEntity) {
        return Business.builder()
                .id(businessEntity.id())
                .name(businessEntity.name())
                .phoneNumber(businessEntity.phoneNumber())
                .imageUrl(businessEntity.imageUrl())
                .price(businessEntity.price())
                .categories(businessEntity.categories())
                .rating(businessEntity.rating())
                .closed(businessEntity.closed())
                .location(locationEntityMapper.map(businessEntity.location()))
                .coordinates(coordinatesEntityMapper.map(businessEntity.coordinates()))
                .build();
    }
}
