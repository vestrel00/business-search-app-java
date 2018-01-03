/*
 * Copyright 2018 Vandolf Estrellado
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

package com.vestrel00.business.search.presentation.java.android.mvp.ui.business.list.view;

import com.vestrel00.business.search.presentation.java.android.mvp.ui.common.view.LoadContentView;
import com.vestrel00.business.search.presentation.java.model.BusinessModel;

/**
 * The view that displays a list of businesses.
 */
public interface BusinessListView extends LoadContentView {

    /**
     * Adds a business to be shown in the UI, which will all be rendered when
     * {@link #showBusinesses()} is called.
     *
     * @param businessModel the business to be rendered when {@link #showBusinesses()} is called
     */
    void addBusinessToShow(BusinessModel businessModel);

    /**
     * Shows all businesses that have been added in {@link #addBusinessToShow(BusinessModel)}.
     */
    void showBusinesses();

    /**
     * Removes all businesses added in {@link #addBusinessToShow(BusinessModel)} from showing.
     */
    void removeAllBusinessesFromShowing();

    /**
     * View a business' details.
     *
     * @param businessModel the businesses to view the details of
     */
    void showBusinessDetails(BusinessModel businessModel);
}
