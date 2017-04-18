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

import com.vestrel00.business.search.data.net.BusinessDataService;
import com.vestrel00.business.search.data.util.LocationFormatter;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Lazy;

/**
 * Provides business data stores that stores business data in memory, disk, or network depending
 * on circumstances.
 * <p>
 * Currently, this uses the {@link SmartBusinessDataStore}, which provides data from memory, disk,
 * or network (hence "smart").
 * <p>
 * Note that it is understood that this provider is not needed in the current implementation as we
 * only have 1 data store that handles memory, disk, or network storage. We could get rid of this
 * and just inject the {@link SmartBusinessDataStore} directly into consumers. However, we may
 * decide to provide different data stores given a circumstance. Hence, the purpose of this class.
 */
@Singleton
public final class BusinessDataStoreProvider {

    private final Lazy<BusinessDataService> businessDataServiceProvider;
    private final LocationFormatter locationFormatter;

    private SmartBusinessDataStore smartDataStore;

    @Inject
    BusinessDataStoreProvider(Lazy<BusinessDataService> businessDataServiceProvider,
                              LocationFormatter locationFormatter) {
        this.businessDataServiceProvider = businessDataServiceProvider;
        this.locationFormatter = locationFormatter;
    }

    public BusinessDataStore get() {
        if (smartDataStore == null) {
            smartDataStore = new SmartBusinessDataStore(businessDataServiceProvider,
                    locationFormatter);
        }
        return smartDataStore;
    }
}
