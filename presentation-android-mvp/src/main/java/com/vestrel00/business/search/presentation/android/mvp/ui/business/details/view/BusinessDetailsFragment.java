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

package com.vestrel00.business.search.presentation.android.mvp.ui.business.details.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vestrel00.business.search.presentation.android.mvp.R;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.details.presenter.BusinessDetailsPresenter;
import com.vestrel00.business.search.presentation.android.mvp.ui.common.view.AbstractLoadContentFragment;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;

/**
 * A fragment implementation of {@link BusinessDetailsView}.
 */
public final class BusinessDetailsFragment
        extends AbstractLoadContentFragment<BusinessDetailsPresenter>
        implements BusinessDetailsView {

    @BindView(R.id.photos)
    ViewPager photos;

    @BindView(R.id.photos_indicator)
    CircleIndicator photosIndicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.business_details_fragment, container, false);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        // TODO (IMPLEMENTATION) - photos.setAdapter();
    }
}
