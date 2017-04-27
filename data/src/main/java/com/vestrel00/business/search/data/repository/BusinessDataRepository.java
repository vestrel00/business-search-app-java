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
import com.vestrel00.business.search.data.entity.mapper.EntityMapperProvider;
import com.vestrel00.business.search.data.entity.validator.EntityValidatorProvider;
import com.vestrel00.business.search.data.repository.datasource.BusinessDataStoreFactory;
import com.vestrel00.business.search.data.util.StringUtils;
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
final class BusinessDataRepository implements BusinessRepository {

    private final BusinessDataStoreFactory dataStoreFactory;
    private final EntityMapperProvider entityMapperProvider;
    private final EntityValidatorProvider entityValidatorProvider;
    private final StringUtils stringUtils;

    @Inject
    BusinessDataRepository(BusinessDataStoreFactory dataStoreFactory,
                           EntityMapperProvider entityMapperProvider,
                           EntityValidatorProvider entityValidatorProvider,
                           StringUtils stringUtils) {
        this.dataStoreFactory = dataStoreFactory;
        this.entityMapperProvider = entityMapperProvider;
        this.entityValidatorProvider = entityValidatorProvider;
        this.stringUtils = stringUtils;
    }

    /* FIXME (LAMBDA) - Delete below code and uncomment this code block to use lambdas instead
    @Override
    public Single<List<Business>> aroundLocation(Location location) {
        return Observable.just(location)
                .map(entityMapperProvider.locationEntityMapper::map)
                .doOnNext(entityValidatorProvider.locationEntityValidator()::validate)
                // Ordering via concatMap is unnecessary since the source only emits 1 item
                .flatMap(dataStoreFactory.create()::aroundLocation)
                .filter(entityValidatorProvider.businessEntityValidator()::isValid)
                .map(entityMapperProvider.businessEntityMapper::map)
                .toList();
    }

    @Override
    public Single<List<Business>> aroundLocationString(String locationString) {
        return Observable.just(locationString)
                .doOnNext(this::validateLocationString)
                // Ordering via concatMap is unnecessary since the source only emits 1 item
                .flatMap(dataStoreFactory.create()::aroundLocationString)
                .filter(entityValidatorProvider.businessEntityValidator()::isValid)
                .map(entityMapperProvider.businessEntityMapper::map)
                .toList();
    }

    @Override
    public Single<List<Business>> aroundCoordinates(Coordinates coordinates) {
        return Observable.just(coordinates)
                .map(entityMapperProvider.coordinatesEntityMapper::map)
                .doOnNext(entityValidatorProvider.coordinatesEntityValidator()::validate)
                // Ordering via concatMap is unnecessary since the source only emits 1 item
                .flatMap(dataStoreFactory.create()::aroundCoordinates)
                .filter(entityValidatorProvider.businessEntityValidator()::isValid)
                .map(entityMapperProvider.businessEntityMapper::map)
                .toList();
    }

    @Override
    public Single<Business> withId(String businessId) {
        return Observable.just(businessId)
                .doOnNext(this::validateBusinessId)
                // Ordering via concatMap is unnecessary since the source only emits 1 item
                .flatMap(dataStoreFactory.create()::withId)
                .filter(entityValidatorProvider.businessEntityValidator()::isValid)
                .map(entityMapperProvider.businessEntityMapper::map)
                .toList();
    }
    */

    @Override
    public Single<List<Business>> aroundLocation(Location location) {
        return Observable.just(location)
                .map(new Function<Location, LocationEntity>() {
                    @Override
                    public LocationEntity apply(@NonNull Location location) throws Exception {
                        return entityMapperProvider.locationEntityMapper().map(location);
                    }
                })
                .doOnNext(new Consumer<LocationEntity>() {
                    @Override
                    public void accept(@NonNull LocationEntity locationEntity) throws Exception {
                        entityValidatorProvider.locationEntityValidator().validate(locationEntity);
                    }
                })
                // Ordering via concatMap is unnecessary since the source only emits 1 item
                .flatMap(new Function<LocationEntity, ObservableSource<BusinessEntity>>() {
                    @Override
                    public ObservableSource<BusinessEntity>
                    apply(@NonNull LocationEntity locationEntity) throws Exception {
                        return dataStoreFactory.create().aroundLocation(locationEntity);
                    }
                })
                .filter(new Predicate<BusinessEntity>() {
                    @Override
                    public boolean test(@NonNull BusinessEntity businessEntity) throws Exception {
                        return entityValidatorProvider.businessEntityValidator()
                                .isValid(businessEntity);
                    }
                })
                .map(new Function<BusinessEntity, Business>() {
                    @Override
                    public Business apply(@NonNull BusinessEntity businessEntity) throws Exception {
                        return entityMapperProvider.businessEntityMapper().map(businessEntity);
                    }
                })
                .toList();
    }

    @Override
    public Single<List<Business>> aroundLocationString(String locationString) {
        return Observable.just(locationString)
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String locationString) throws Exception {
                        validateLocationString(locationString);
                    }
                })
                // Ordering via concatMap is unnecessary since the source only emits 1 item
                .flatMap(new Function<String, ObservableSource<BusinessEntity>>() {
                    @Override
                    public ObservableSource<BusinessEntity>
                    apply(@NonNull String locationString) throws Exception {
                        return dataStoreFactory.create().aroundLocationString(locationString);
                    }
                })
                .filter(new Predicate<BusinessEntity>() {
                    @Override
                    public boolean test(@NonNull BusinessEntity businessEntity) throws Exception {
                        return entityValidatorProvider.businessEntityValidator()
                                .isValid(businessEntity);
                    }
                })
                .map(new Function<BusinessEntity, Business>() {
                    @Override
                    public Business apply(@NonNull BusinessEntity businessEntity) throws Exception {
                        return entityMapperProvider.businessEntityMapper().map(businessEntity);
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
                        return entityMapperProvider.coordinatesEntityMapper().map(coordinates);
                    }
                })
                .doOnNext(new Consumer<CoordinatesEntity>() {
                    @Override
                    public void accept(@NonNull CoordinatesEntity coordinatesEntity)
                            throws Exception {
                        entityValidatorProvider.coordinatesEntityValidator()
                                .validate(coordinatesEntity);
                    }
                })
                // Ordering via concatMap is unnecessary since the source only emits 1 item
                .flatMap(new Function<CoordinatesEntity, ObservableSource<BusinessEntity>>() {
                    @Override
                    public ObservableSource<BusinessEntity>
                    apply(@NonNull CoordinatesEntity coordinatesEntity) throws Exception {
                        return dataStoreFactory.create().aroundCoordinates(coordinatesEntity);
                    }
                })
                .filter(new Predicate<BusinessEntity>() {
                    @Override
                    public boolean test(@NonNull BusinessEntity businessEntity) throws Exception {
                        return entityValidatorProvider.businessEntityValidator()
                                .isValid(businessEntity);
                    }
                })
                .map(new Function<BusinessEntity, Business>() {
                    @Override
                    public Business apply(@NonNull BusinessEntity businessEntity) throws Exception {
                        return entityMapperProvider.businessEntityMapper().map(businessEntity);
                    }
                })
                .toList();
    }

    @Override
    public Single<Business> withId(String businessId) {
        return Observable.just(businessId)
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String businessId) throws Exception {
                        validateBusinessId(businessId);
                    }
                })
                // Ordering via concatMap is unnecessary since the source only emits 1 item
                .flatMap(new Function<String, ObservableSource<BusinessEntity>>() {
                    @Override
                    public ObservableSource<BusinessEntity>
                    apply(@NonNull String businessId) throws Exception {
                        return dataStoreFactory.create().withId(businessId);
                    }
                }).filter(new Predicate<BusinessEntity>() {
                    @Override
                    public boolean test(@NonNull BusinessEntity businessEntity) throws Exception {
                        return entityValidatorProvider.businessEntityValidator()
                                .isValid(businessEntity);
                    }
                })
                .map(new Function<BusinessEntity, Business>() {
                    @Override
                    public Business apply(@NonNull BusinessEntity businessEntity) throws Exception {
                        return entityMapperProvider.businessEntityMapper().map(businessEntity);
                    }
                })
                .singleOrError();
    }

    private void validateLocationString(String locationString) throws IllegalArgumentException {
        if (stringUtils.isEmpty(locationString)) {
            throw new IllegalArgumentException("Location must not be empty.");
        }
    }

    private void validateBusinessId(String businessId) throws IllegalArgumentException {
        if (stringUtils.isEmpty(businessId)) {
            throw new IllegalArgumentException("Business id must not be empty.");
        }
    }
}
