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

package com.vestrel00.business.search.presentation.java.android.mvp.ui.common.view;

import android.view.View;
import android.widget.Toast;

import com.vestrel00.business.search.presentation.java.android.mvp.R;
import com.vestrel00.business.search.presentation.java.android.mvp.ui.common.presenter.Presenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * An abstract {@link BaseViewFragment} that implements {@link LoadContentView}.
 * <p>
 * This fragment assumes that the following Android views with the given ids exist in the view
 * returned in onCreateView:
 * <ul>
 * <li>R.id.content_view : the view that contains the content</li>
 * <li>R.id.loading_indicator : the loading indicator view</li>
 * <li>R.id.retry_button : the retry button view</li>
 * </ul>
 * The above required views must be declared invisible in xml.
 * <p>
 * This uses a {@link Toast} for showing errors via {@link #showError(String)}.
 *
 * @param <T> the type of the {@link Presenter}.
 */
public abstract class AbstractLoadContentFragment<T extends Presenter> extends BaseViewFragment<T>
        implements LoadContentView {

    @BindView(R.id.content_pane)
    protected View contentPane;

    @BindView(R.id.loading_indicator)
    protected View loadingIndicator;

    @BindView(R.id.retry_button)
    protected View retryButton;

    @Override
    public void showContent() {
        contentPane.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContent() {
        contentPane.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoading() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingIndicator.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showRetry() {
        retryButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        retryButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(activityContext, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.retry_button)
    protected void onRetryButtonClicked() {
    }
}
