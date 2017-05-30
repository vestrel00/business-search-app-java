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

package com.vestrel00.business.search.presentation.android.mvp.navigation;

import android.content.Context;
import android.content.Intent;

import com.vestrel00.business.search.presentation.android.mvp.ui.business.details.BusinessDetailsActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Provides methods to navigate to the different Activities in the application.
 */
@Singleton
public final class Navigator {

    @Inject
    Navigator() {
    }

    public void toBusinessDetails(Context context, String businessId) {
        Intent intent = BusinessDetailsActivity.intentForBusinessId(context, businessId);
        context.startActivity(intent);
    }

    /*
     * We could pass in the entire BusinessModel object as an extra in order to avoid having
     * to make another network call using the businessId to fetch the data. However, we are not
     * doing that to keep the details activity as simple as possible. There would be no code
     * branching in case we support deep linking (it would use businessId as well). Furthermore,
     * passing in the business model object would add logic for handling refreshes and retries.
     *
     * Another thing to note is that some data may not be available in the given BusinessModel,
     * which is retrieved from the search. More details, including opening hours and photosUrls,
     * must be retrieved via the id.
     */
    // public void toBusinessDetails(BusinessModel businessModel) {
    // }
}
