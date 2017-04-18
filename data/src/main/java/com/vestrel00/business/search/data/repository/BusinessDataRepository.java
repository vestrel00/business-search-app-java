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

package com.vestrel00.business.search.data.repository;

import com.vestrel00.business.search.data.entity.mapper.BusinessEntityMapper;
import com.vestrel00.business.search.data.entity.mapper.CoordinatesEntityMapper;
import com.vestrel00.business.search.data.entity.mapper.LocationEntityMapper;
import com.vestrel00.business.search.data.repository.datasource.BusinessDataStoreProvider;
import com.vestrel00.business.search.data.validator.CoordinatesEntityValidator;
import com.vestrel00.business.search.data.validator.LocationEntityValidator;
import com.vestrel00.business.search.domain.Business;
import com.vestrel00.business.search.domain.Coordinates;
import com.vestrel00.business.search.domain.Location;
import com.vestrel00.business.search.domain.repository.BusinessRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * An implementation of {@link BusinessRepository}.
 */
@Singleton
public final class BusinessDataRepository implements BusinessRepository {

    private final BusinessDataStoreProvider dataStoreProvider;
    private final BusinessEntityMapper businessEntityMapper;
    private final LocationEntityMapper locationEntityMapper;
    private final CoordinatesEntityMapper coordinatesEntityMapper;
    private final LocationEntityValidator locationEntityValidator;
    private final CoordinatesEntityValidator coordinatesEntityValidator;

    @Inject
    BusinessDataRepository(BusinessDataStoreProvider dataStoreProvider,
                           BusinessEntityMapper businessEntityMapper,
                           LocationEntityMapper locationEntityMapper,
                           CoordinatesEntityMapper coordinatesEntityMapper,
                           LocationEntityValidator locationEntityValidator,
                           CoordinatesEntityValidator coordinatesEntityValidator) {
        this.dataStoreProvider = dataStoreProvider;
        this.businessEntityMapper = businessEntityMapper;
        this.locationEntityMapper = locationEntityMapper;
        this.coordinatesEntityMapper = coordinatesEntityMapper;
        this.locationEntityValidator = locationEntityValidator;
        this.coordinatesEntityValidator = coordinatesEntityValidator;
    }

    @Override
    public Single<List<Business>> aroundLocation(Location location) {
        return Observable.just(location)
                .map(locationEntityMapper::map)
                .doOnNext(locationEntityValidator::validate)
                .concatMap(dataStoreProvider.get()::aroundLocation)
                .map(businessEntityMapper::map)
                .toList();
    }

    @Override
    public Single<List<Business>> aroundCoordinates(Coordinates coordinates) {
        return Observable.just(coordinates)
                .map(coordinatesEntityMapper::map)
                .doOnNext(coordinatesEntityValidator::validate)
                .concatMap(dataStoreProvider.get()::aroundCoordinates)
                .map(businessEntityMapper::map)
                .toList();
    }
}
