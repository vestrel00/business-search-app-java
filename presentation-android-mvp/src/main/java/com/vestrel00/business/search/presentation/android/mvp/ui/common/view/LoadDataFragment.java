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

package com.vestrel00.business.search.presentation.android.mvp.ui.common.view;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.vestrel00.business.search.presentation.android.mvp.R;
import com.vestrel00.business.search.presentation.android.mvp.ui.common.presenter.Presenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * An abstract {@link BaseFragment} that implements {@link LoadDataView}.
 * <p>
 * This fragment assumes that the following Android views with the given ids exist in the view
 * returned in onCreateView:
 * <ul>
 * <li>R.id.loading_indicator : the loading indicator view</li>
 * <li>R.id.retry_button : the retry button view</li>
 * </ul>
 * This uses a {@link Toast} for showing errors via {@link #showError(String)}.
 *
 * @param <T> the type of the {@link Presenter}.
 */
public abstract class LoadDataFragment<T extends Presenter> extends BaseFragment<T>
        implements LoadDataView {

    @Inject
    protected Context context;

    @BindView(R.id.loading_indicator)
    protected View loadingIndicator;

    @BindView(R.id.retry_button)
    protected View retryButton;

    @Override
    public void showLoading() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        retryButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        retryButton.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
