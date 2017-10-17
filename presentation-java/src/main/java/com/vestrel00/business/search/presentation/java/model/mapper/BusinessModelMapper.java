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
import com.vestrel00.business.search.domain.BusinessHours;
import com.vestrel00.business.search.domain.BusinessTransactionType;
import com.vestrel00.business.search.domain.Coordinates;
import com.vestrel00.business.search.domain.Location;
import com.vestrel00.business.search.presentation.java.model.BusinessHoursModel;
import com.vestrel00.business.search.presentation.java.model.BusinessModel;
import com.vestrel00.business.search.presentation.java.model.BusinessTransactionTypeModel;
import com.vestrel00.business.search.presentation.java.model.CoordinatesModel;
import com.vestrel00.business.search.presentation.java.model.LocationModel;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link BusinessModel} to {@link Business} and vice versa.
 */
@Reusable
final class BusinessModelMapper implements ModelMapper<BusinessModel, Business> {

    private final ModelMapper<BusinessTransactionTypeModel, BusinessTransactionType>
            transactionTypeModelMapper;
    private final ModelMapper<BusinessHoursModel, BusinessHours> hoursModelMapper;
    private final ModelMapper<LocationModel, Location> locationModelMapper;
    private final ModelMapper<CoordinatesModel, Coordinates> coordinatesModelMapper;
    private final ModelListMapper modelListMapper;

    @Inject
    BusinessModelMapper(ModelMapper<BusinessTransactionTypeModel, BusinessTransactionType>
                                transactionTypeModelMapper,
                        ModelMapper<BusinessHoursModel, BusinessHours> hoursModelMapper,
                        ModelMapper<LocationModel, Location> locationModelMapper,
                        ModelMapper<CoordinatesModel, Coordinates> coordinatesModelMapper,
                        ModelListMapper modelListMapper) {
        this.transactionTypeModelMapper = transactionTypeModelMapper;
        this.hoursModelMapper = hoursModelMapper;
        this.locationModelMapper = locationModelMapper;
        this.coordinatesModelMapper = coordinatesModelMapper;
        this.modelListMapper = modelListMapper;
    }

    @Override
    public Business map(BusinessModel businessModel) {
        return Business.builder()
                .id(businessModel.id())
                .name(businessModel.name())
                .phoneNumber(businessModel.phoneNumber())
                .imageUrl(businessModel.imageUrl())
                .price(businessModel.price())
                .url(businessModel.url())
                .transactionTypes(modelListMapper.mapToV(transactionTypeModelMapper,
                        businessModel.transactionTypes()))
                .categories(businessModel.categories())
                .photosUrls(businessModel.photosUrls())
                .reviewCount(businessModel.reviewCount())
                .rating(businessModel.rating())
                .distanceInMeters(businessModel.distanceInMeters())
                .hours(hoursModelMapper.map(businessModel.hoursModel()))
                .location(locationModelMapper.map(businessModel.locationModel()))
                .coordinates(coordinatesModelMapper.map(businessModel.coordinatesModel()))
                .build();
    }

    @Override
    public BusinessModel map(Business business) {
        return BusinessModel.builder()
                .id(business.id())
                .name(business.name())
                .phoneNumber(business.phoneNumber())
                .imageUrl(business.imageUrl())
                .price(business.price())
                .url(business.url())
                .transactionTypes(modelListMapper.mapToK(transactionTypeModelMapper,
                        business.transactionTypes()))
                .categories(business.categories())
                .photosUrls(business.photosUrls())
                .reviewCount(business.reviewCount())
                .rating(business.rating())
                .distanceInMeters(business.distanceInMeters())
                .hoursModel(hoursModelMapper.map(business.hours()))
                .locationModel(locationModelMapper.map(business.location()))
                .coordinatesModel(coordinatesModelMapper.map(business.coordinates()))
                .build();
    }
}
