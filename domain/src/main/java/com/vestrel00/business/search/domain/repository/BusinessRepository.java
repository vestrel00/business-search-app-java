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

package com.vestrel00.business.search.domain.repository;

import com.vestrel00.business.search.domain.Business;
import com.vestrel00.business.search.domain.Coordinates;
import com.vestrel00.business.search.domain.Location;

import java.util.List;

import io.reactivex.Single;

/**
 * Represents repository for retrieving business data.
 */
public interface BusinessRepository {

    /**
     * @param location the location to search for surrounding businesses
     * @return an single containing a list that contains 0 or more businesses
     */
    Single<List<Business>> aroundLocation(Location location);

    /**
     * @param locationString the location to search for surrounding businesses
     * @return an single containing a list that contains 0 or more businesses
     */
    Single<List<Business>> aroundLocationString(String locationString);

    /**
     * @param coordinates the coordinates to search for surrounding businesses
     * @return an single containing a list that contains 0 or more businesses
     */
    Single<List<Business>> aroundCoordinates(Coordinates coordinates);

    /**
     * @param businessId the business id
     * @return a single containing a business with the given id
     */
    Single<Business> withId(String businessId);
}
