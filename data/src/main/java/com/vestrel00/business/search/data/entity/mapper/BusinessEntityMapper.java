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
import com.vestrel00.business.search.data.entity.BusinessTransactionTypeEntity;
import com.vestrel00.business.search.data.entity.CoordinatesEntity;
import com.vestrel00.business.search.data.entity.LocationEntity;
import com.vestrel00.business.search.domain.Business;
import com.vestrel00.business.search.domain.BusinessTransactionType;
import com.vestrel00.business.search.domain.Coordinates;
import com.vestrel00.business.search.domain.Location;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Maps {@link BusinessEntity} to {@link Business} and vice versa.
 */
@Singleton
final class BusinessEntityMapper implements EntityMapper<BusinessEntity, Business> {

    private final EntityMapper<LocationEntity, Location> locationEntityMapper;
    private final EntityMapper<CoordinatesEntity, Coordinates> coordinatesEntityMapper;
    private final EntityMapper<BusinessTransactionTypeEntity, BusinessTransactionType>
            businessTransactionTypeEntityMapper;
    private final EntityListMapper entityListMapper;

    @Inject
    BusinessEntityMapper(EntityMapper<LocationEntity, Location> locationEntityMapper,
                         EntityMapper<CoordinatesEntity, Coordinates> coordinatesEntityMapper,
                         EntityMapper<BusinessTransactionTypeEntity, BusinessTransactionType>
                                 businessTransactionTypeEntityMapper,
                         EntityListMapper entityListMapper) {
        this.locationEntityMapper = locationEntityMapper;
        this.coordinatesEntityMapper = coordinatesEntityMapper;
        this.businessTransactionTypeEntityMapper = businessTransactionTypeEntityMapper;
        this.entityListMapper = entityListMapper;
    }

    @Override
    public Business map(BusinessEntity businessEntity) {
        return Business.builder()
                .id(businessEntity.id())
                .name(businessEntity.name())
                .phoneNumber(businessEntity.phoneNumber())
                .imageUrl(businessEntity.imageUrl())
                .price(businessEntity.price())
                .url(businessEntity.url())
                .transactionTypes(entityListMapper.mapToV(businessTransactionTypeEntityMapper,
                        businessEntity.transactionTypes()))
                .categories(businessEntity.categories())
                .reviewCount(businessEntity.reviewCount())
                .rating(businessEntity.rating())
                .location(locationEntityMapper.map(businessEntity.locationEntity()))
                .coordinates(coordinatesEntityMapper.map(businessEntity.coordinatesEntity()))
                .build();
    }

    @Override
    public BusinessEntity map(Business business) {
        return BusinessEntity.builder()
                .id(business.id())
                .name(business.name())
                .phoneNumber(business.phoneNumber())
                .imageUrl(business.imageUrl())
                .price(business.price())
                .url(business.url())
                .transactionTypes(entityListMapper.mapToK(businessTransactionTypeEntityMapper,
                        business.transactionTypes()))
                .categories(business.categories())
                .reviewCount(business.reviewCount())
                .rating(business.rating())
                .locationEntity(locationEntityMapper.map(business.location()))
                .coordinatesEntity(coordinatesEntityMapper.map(business.coordinates()))
                .build();
    }
}
