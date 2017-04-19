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
import com.vestrel00.business.search.presentation.java.model.BusinessModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Transforms from {@link BusinessModel} to {@link Business} and vice versa.
 */
@Singleton
public final class BusinessModelMapper {

    private final LocationModelMapper locationModelMapper;
    private final CoordinatesModelMapper coordinatesEntityMapper;

    @Inject
    BusinessModelMapper(LocationModelMapper locationModelMapper,
                        CoordinatesModelMapper coordinatesEntityMapper) {
        this.locationModelMapper = locationModelMapper;
        this.coordinatesEntityMapper = coordinatesEntityMapper;
    }

    public BusinessModel map(Business business) {
        return BusinessModel.builder()
                .id(business.id())
                .name(business.name())
                .phoneNumber(business.phoneNumber())
                .imageUrl(business.imageUrl())
                .price(business.price())
                .rating(business.rating())
                .closed(business.closed())
                .location(locationModelMapper.map(business.location()))
                .coordinates(coordinatesEntityMapper.map(business.coordinates()))
                .build();
    }

    Business map(BusinessModel businessModel) {
        return Business.builder()
                .id(businessModel.id())
                .name(businessModel.name())
                .phoneNumber(businessModel.phoneNumber())
                .imageUrl(businessModel.imageUrl())
                .price(businessModel.price())
                .rating(businessModel.rating())
                .closed(businessModel.closed())
                .location(locationModelMapper.map(businessModel.location()))
                .coordinates(coordinatesEntityMapper.map(businessModel.coordinates()))
                .build();
    }
}
