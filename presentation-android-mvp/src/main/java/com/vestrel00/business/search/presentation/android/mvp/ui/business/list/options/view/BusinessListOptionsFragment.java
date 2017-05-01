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

package com.vestrel00.business.search.presentation.android.mvp.ui.business.list.options.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vestrel00.business.search.presentation.android.mvp.R;
import com.vestrel00.business.search.presentation.android.mvp.ui.common.view.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * The fragment view that displays options that can be applied to list businesses.
 */
public class BusinessListOptionsFragment extends BaseFragment {

    @BindView(R.id.location_field)
    EditText locationField;

    @Inject
    BusinessListOptionsFragmentListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.business_list_options_fragment, container, false);
    }

    @OnClick(R.id.search_around_location)
    void onSearchAroundLocationClicked() {
        String location = locationField.getText().toString();
        listener.onSearchAroundLocationClicked(location);
    }

    @OnClick(R.id.search_around_current_location)
    void onSearchAroundCurrentLocationClicked() {
        listener.onSearchAroundCurrentLocationClicked();
    }

    @OnCheckedChanged(R.id.toggle_map_list_view)
    void onToggleMapListView(boolean isChecked) {
        if (isChecked) {
            listener.onShowMapView();
        } else {
            listener.onShowListView();
        }
    }
}
