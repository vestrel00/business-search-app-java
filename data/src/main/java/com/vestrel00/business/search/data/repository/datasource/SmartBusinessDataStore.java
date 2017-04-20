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

package com.vestrel00.business.search.data.repository.datasource;

import com.vestrel00.business.search.data.entity.BusinessEntity;
import com.vestrel00.business.search.data.entity.CoordinatesEntity;
import com.vestrel00.business.search.data.entity.LocationEntity;
import com.vestrel00.business.search.data.net.BusinessDataService;
import com.vestrel00.business.search.data.net.BusinessListResponse;
import com.vestrel00.business.search.data.util.LocationFormatter;

import dagger.Lazy;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * A {@link BusinessDataStore} that provides data from memory, disk, or network (hence "smart").
 */
final class SmartBusinessDataStore implements BusinessDataStore {

    private final Lazy<BusinessDataService> businessDataServiceProvider;
    private final LocationFormatter locationFormatter;

    SmartBusinessDataStore(Lazy<BusinessDataService> businessDataServiceProvider,
                           LocationFormatter locationFormatter) {
        this.businessDataServiceProvider = businessDataServiceProvider;
        this.locationFormatter = locationFormatter;
    }

    @Override
    public Observable<BusinessEntity> aroundLocation(LocationEntity location) {
        String formattedLocation = locationFormatter.formatLocation(location);
        return businessDataServiceProvider.get()
                .aroundLocation(formattedLocation)
                // Ordering via concatMap is unnecessary since the source only emits 1 item
                .flatMapIterable(toList());
    }

    @Override
    public Observable<BusinessEntity> aroundCoordinates(CoordinatesEntity coordinates) {
        return businessDataServiceProvider.get()
                .aroundCoordinates(coordinates.latitude(), coordinates.longitude())
                // Ordering via concatMap is unnecessary since the source only emits 1 item
                .flatMapIterable(toList());
    }

    @Override
    public Observable<BusinessEntity> withId(String businessId) {
        return businessDataServiceProvider.get()
                .withId(businessId);
    }

    // FIXME (LAMBDA) - Use lambdas instead
    private Function<BusinessListResponse, Iterable<BusinessEntity>> toList() {
        return new Function<BusinessListResponse, Iterable<BusinessEntity>>() {
            @Override
            public Iterable<BusinessEntity>
            apply(@NonNull BusinessListResponse businessListResponse)
                    throws Exception {
                return businessListResponse.businesses();
            }
        };
    }
}
