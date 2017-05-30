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

package com.vestrel00.business.search.presentation.android.mvp.ui.business.common.view;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vestrel00.business.search.presentation.android.mvp.R;
import com.vestrel00.business.search.presentation.android.widget.LabeledInfoField;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Holds views that are used to bind business data. All views defined here are optional and can be
 * null. This is done to make this view holder reusable across different layout configurations.
 */
public final class BusinessViewHolder {

    @Nullable
    @BindView(R.id.image)
    SimpleDraweeView image;

    @Nullable
    @BindView(R.id.name)
    TextView name;

    @Nullable
    @BindView(R.id.distance)
    TextView distance;

    @Nullable
    @BindView(R.id.rating)
    RatingBar rating;

    @Nullable
    @BindView(R.id.reviews)
    TextView reviews;

    @Nullable
    @BindView(R.id.price)
    TextView price;

    @Nullable
    @BindView(R.id.location)
    TextView location;

    @Nullable
    @BindView(R.id.categories)
    TextView categories;

    @Nullable
    @BindView(R.id.photos)
    ViewPager photos;

    @Nullable
    @BindView(R.id.photos_indicator)
    CircleIndicator photosIndicator;

    @Nullable
    @BindView(R.id.hours)
    LinearLayout hours;

    @Nullable
    @BindView(R.id.open_indicator)
    TextView openIndicator;

    @Nullable
    @BindView(R.id.phone_number)
    LabeledInfoField phoneNumber;

    @Nullable
    @BindView(R.id.website)
    LabeledInfoField website;

    @Nullable
    @BindView(R.id.pickup)
    LabeledInfoField pickup;

    @Nullable
    @BindView(R.id.delivery)
    LabeledInfoField delivery;

    @Nullable
    @BindView(R.id.reservation)
    LabeledInfoField reservation;

    BusinessViewHolder(View itemView) {
        ButterKnife.bind(this, itemView);
    }
}
