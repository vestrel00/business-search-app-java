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

package com.vestrel00.business.search.presentation.android.mvp.ui.business.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vestrel00.business.search.presentation.android.mvp.R;
import com.vestrel00.business.search.presentation.android.mvp.ui.business.details.view.BusinessDetailsFragment;
import com.vestrel00.business.search.presentation.android.mvp.ui.common.BaseActivity;

/**
 * An activity that displays a the details of a business.
 */
public final class BusinessDetailsActivity extends BaseActivity {

    private static final String EXTRA_BUSINESS_ID = "BusinessDetailsActivity.businessId";

    public static Intent intentForBusinessId(Context context, String businessId) {
        Intent intent = new Intent(context, BusinessDetailsActivity.class);
        intent.putExtra(EXTRA_BUSINESS_ID, businessId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_details_activity);

        if (savedInstanceState == null) {
            addFragment(R.id.content_container,
                    BusinessDetailsFragment.forBusinessId(getBusinessIdExtra()));
        }
    }

    private String getBusinessIdExtra() {
        return getIntent().getStringExtra(EXTRA_BUSINESS_ID);
    }
}
