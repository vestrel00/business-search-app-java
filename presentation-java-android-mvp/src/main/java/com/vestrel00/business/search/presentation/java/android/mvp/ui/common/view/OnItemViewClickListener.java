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

package com.vestrel00.business.search.presentation.java.android.mvp.ui.common.view;

import android.view.View;

/**
 * A {@link View.OnClickListener} that invokes an {@link OnItemClickListener}'s
 * {@link OnItemClickListener#onItemClicked(Object)} {@link #onClick(View)}.
 *
 * @param <T> the type of the item that is clicked
 */
public final class OnItemViewClickListener<T> implements View.OnClickListener {

    private final OnItemClickListener<T> onItemClickListener;
    private final T item;

    OnItemViewClickListener(OnItemClickListener<T> onItemClickListener, T item) {
        this.onItemClickListener = onItemClickListener;
        this.item = item;
    }

    @Override
    public void onClick(View view) {
        onItemClickListener.onItemClicked(item);
    }
}
