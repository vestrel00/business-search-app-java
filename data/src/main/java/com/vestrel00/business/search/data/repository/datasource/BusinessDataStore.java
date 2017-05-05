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

import io.reactivex.Observable;

/**
 * Provides business data.
 */
public interface BusinessDataStore {
    /**
     * @param location the location to search for surrounding businesses
     * @return an observable of 0 or more businesses
     */
    Observable<BusinessEntity> aroundLocation(LocationEntity location);

    /**
     * @param locationString the location to search for surrounding businesses
     * @return an single containing a list that contains 0 or more businesses
     */
    Observable<BusinessEntity> aroundLocationString(String locationString);

    /**
     * @param coordinates the coordinates to search for surrounding businesses
     * @return an observable of 0 or more businesses
     */
    Observable<BusinessEntity> aroundCoordinates(CoordinatesEntity coordinates);

    /**
     * @param businessId the business id
     * @return an observable of 0 or 1 business
     */
    Observable<BusinessEntity> withId(String businessId);
}
