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

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Provides business data stores that stores business data in memory, disk, or network.
 * <p>
 * Currently, this uses the {@link SmartBusinessDataStore}, which provides data from memory, disk,
 * or network (hence "smart").
 */
@Singleton
public final class BusinessDataStoreProvider {

    private final SmartBusinessDataStore smartDataStore;

    @Inject
    public BusinessDataStoreProvider(SmartBusinessDataStore smartDataStore) {
        this.smartDataStore = smartDataStore;
    }

    public BusinessDataStore get() {
        return smartDataStore;
    }
}
