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

import com.vestrel00.business.search.domain.Business;
import com.vestrel00.business.search.domain.Coordinates;
import com.vestrel00.business.search.domain.Location;
import com.vestrel00.business.search.domain.repository.BusinessRepository;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * An implementation of {@link}
 */
@Singleton
public final class BusinessDataRepository implements BusinessRepository {

    @Override
    public Single<List<Business>> aroundCurrentLocation() {
        return null;
    }

    @Override
    public Single<List<Business>> aroundLocation(Location location) {
        return null;
    }

    @Override
    public Single<List<Business>> aroundCoordinates(Coordinates coordinates) {
        return null;
    }
}
