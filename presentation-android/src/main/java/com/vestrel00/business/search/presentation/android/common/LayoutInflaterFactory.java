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

package com.vestrel00.business.search.presentation.android.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Creates instances of {@link LayoutInflater}.
 */
@Singleton
public final class LayoutInflaterFactory {

    @Inject
    LayoutInflaterFactory() {
    }

    public LayoutInflater from(View activityView) {
        return from(activityView.getContext());
    }

    public LayoutInflater from(Context activityContext) {
        return LayoutInflater.from(activityContext);
    }
}
