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

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vestrel00.business.search.presentation.android.mvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Holds views that are used to bind business data.
 */
public final class BusinessItemViewHolder {

    @BindView(R.id.image)
    SimpleDraweeView image;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.distance)
    TextView distance;

    @BindView(R.id.rating)
    RatingBar rating;

    @BindView(R.id.reviews)
    TextView reviews;

    @BindView(R.id.price)
    TextView price;

    @BindView(R.id.location)
    TextView location;

    @BindView(R.id.categories)
    TextView categories;

    private final View itemView;

    BusinessItemViewHolder(View itemView) {
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public View itemView() {
        return itemView;
    }
}
