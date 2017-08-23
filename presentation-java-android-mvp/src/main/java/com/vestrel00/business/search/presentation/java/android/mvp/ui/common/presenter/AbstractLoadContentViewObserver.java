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

package com.vestrel00.business.search.presentation.java.android.mvp.ui.common.presenter;

import com.vestrel00.business.search.presentation.java.android.mvp.ui.common.view.LoadContentView;
import com.vestrel00.business.search.presentation.java.common.AbstractDisposableObserver;

/**
 * An abstract observer that provides default behavior for
 *
 * @param <V> the type of {@link LoadContentView}
 * @param <T> the received value type
 */
public abstract class AbstractLoadContentViewObserver<V extends LoadContentView, T>
        extends AbstractDisposableObserver<T> {

    protected final V view;

    protected AbstractLoadContentViewObserver(V view) {
        this.view = view;
    }

    @Override
    protected void onStart() {
        view.hideRetry();
        view.hideContent();
        view.showLoading();
    }

    @Override
    public void onNext(T business) {
    }

    @Override
    public void onError(Throwable e) {
        view.hideLoading();
        view.showRetry();
        view.showError(e.getMessage());
    }

    @Override
    public void onComplete() {
        view.hideLoading();
        view.showContent();
    }
}
