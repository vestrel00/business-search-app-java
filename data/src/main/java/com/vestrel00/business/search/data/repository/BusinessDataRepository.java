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

import com.vestrel00.business.search.data.entity.BusinessEntity;
import com.vestrel00.business.search.data.entity.CoordinatesEntity;
import com.vestrel00.business.search.data.entity.LocationEntity;
import com.vestrel00.business.search.data.entity.mapper.BusinessEntityMapper;
import com.vestrel00.business.search.data.entity.mapper.CoordinatesEntityMapper;
import com.vestrel00.business.search.data.entity.mapper.LocationEntityMapper;
import com.vestrel00.business.search.data.entity.validator.EntityValidatorFactory;
import com.vestrel00.business.search.data.repository.datasource.BusinessDataStoreFactory;
import com.vestrel00.business.search.domain.Business;
import com.vestrel00.business.search.domain.Coordinates;
import com.vestrel00.business.search.domain.Location;
import com.vestrel00.business.search.domain.repository.BusinessRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * An implementation of {@link BusinessRepository}.
 */
@Singleton
public final class BusinessDataRepository implements BusinessRepository {

    private final BusinessDataStoreFactory dataStoreProvider;
    private final BusinessEntityMapper businessEntityMapper;
    private final LocationEntityMapper locationEntityMapper;
    private final CoordinatesEntityMapper coordinatesEntityMapper;
    private final EntityValidatorFactory entityValidatorFactory;

    @Inject
    BusinessDataRepository(BusinessDataStoreFactory dataStoreProvider,
                           BusinessEntityMapper businessEntityMapper,
                           LocationEntityMapper locationEntityMapper,
                           CoordinatesEntityMapper coordinatesEntityMapper,
                           EntityValidatorFactory entityValidatorFactory) {
        this.dataStoreProvider = dataStoreProvider;
        this.businessEntityMapper = businessEntityMapper;
        this.locationEntityMapper = locationEntityMapper;
        this.coordinatesEntityMapper = coordinatesEntityMapper;
        this.entityValidatorFactory = entityValidatorFactory;
    }


    /* FIXME (LAMBDA) - Delete below code and uncomment this code block to use lambdas instead
    @Override
    public Single<List<Business>> aroundLocation(Location location) {
        return Observable.just(location)
                .map(locationEntityMapper::map)
                .doOnNext(entityValidatorFactory.locationEntityValidator()::validate)
                // Ordering via concatMap is unnecessary since the source only emits 1 item
                .flatMap(dataStoreProvider.create()::aroundLocation)
                .filter(entityValidatorFactory.businessEntityValidator()::isValid)
                .map(businessEntityMapper::map)
                .toList();
    }

    @Override
    public Single<List<Business>> aroundCoordinates(Coordinates coordinates) {
        return Observable.just(coordinates)
                .map(coordinatesEntityMapper::map)
                .doOnNext(entityValidatorFactory.coordinatesEntityValidator()::validate)
                // Ordering via concatMap is unnecessary since the source only emits 1 item
                .flatMap(dataStoreProvider.create()::aroundCoordinates)
                .filter(entityValidatorFactory.businessEntityValidator()::isValid)
                .map(businessEntityMapper::map)
                .toList();
    }
    */

    @Override
    public Single<List<Business>> aroundLocation(Location location) {
        return Observable.just(location)
                .map(new Function<Location, LocationEntity>() {
                    @Override
                    public LocationEntity apply(@NonNull Location location) throws Exception {
                        return locationEntityMapper.map(location);
                    }
                })
                .doOnNext(new Consumer<LocationEntity>() {
                    @Override
                    public void accept(@NonNull LocationEntity locationEntity) throws Exception {
                        entityValidatorFactory.locationEntityValidator().validate(locationEntity);
                    }
                })
                // Ordering via concatMap is unnecessary since the source only emits 1 item
                .flatMap(new Function<LocationEntity, ObservableSource<BusinessEntity>>() {
                    @Override
                    public ObservableSource<BusinessEntity>
                    apply(@NonNull LocationEntity locationEntity) throws Exception {
                        return dataStoreProvider.create().aroundLocation(locationEntity);
                    }
                })
                .filter(new Predicate<BusinessEntity>() {
                    @Override
                    public boolean test(@NonNull BusinessEntity businessEntity) throws Exception {
                        return entityValidatorFactory.businessEntityValidator()
                                .isValid(businessEntity);
                    }
                })
                .map(new Function<BusinessEntity, Business>() {
                    @Override
                    public Business apply(@NonNull BusinessEntity businessEntity) throws Exception {
                        return businessEntityMapper.map(businessEntity);
                    }
                })
                .toList();
    }

    @Override
    public Single<List<Business>> aroundCoordinates(Coordinates coordinates) {
        return Observable.just(coordinates)
                .map(new Function<Coordinates, CoordinatesEntity>() {
                    @Override
                    public CoordinatesEntity apply(@NonNull Coordinates coordinates)
                            throws Exception {
                        return coordinatesEntityMapper.map(coordinates);
                    }
                })
                .doOnNext(new Consumer<CoordinatesEntity>() {
                    @Override
                    public void accept(@NonNull CoordinatesEntity coordinatesEntity)
                            throws Exception {
                        entityValidatorFactory.coordinatesEntityValidator()
                                .validate(coordinatesEntity);
                    }
                })
                // Ordering via concatMap is unnecessary since the source only emits 1 item
                .flatMap(new Function<CoordinatesEntity, ObservableSource<BusinessEntity>>() {
                    @Override
                    public ObservableSource<BusinessEntity>
                    apply(@NonNull CoordinatesEntity coordinatesEntity) throws Exception {
                        return dataStoreProvider.create().aroundCoordinates(coordinatesEntity);
                    }
                })
                .filter(new Predicate<BusinessEntity>() {
                    @Override
                    public boolean test(@NonNull BusinessEntity businessEntity) throws Exception {
                        return entityValidatorFactory.businessEntityValidator()
                                .isValid(businessEntity);
                    }
                })
                .map(new Function<BusinessEntity, Business>() {
                    @Override
                    public Business apply(@NonNull BusinessEntity businessEntity) throws Exception {
                        return businessEntityMapper.map(businessEntity);
                    }
                })
                .toList();
    }
}
