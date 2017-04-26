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

package com.vestrel00.business.search.presentation.android.mvp;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * The debug Android {@link Application}, which uses {@link LeakCanary} for leak detection.
 */
public class DebugAndroidApplication extends AndroidApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initializeLeakDetection();
    }

    private void initializeLeakDetection() {
        LeakCanary.install(this);
    }
}
