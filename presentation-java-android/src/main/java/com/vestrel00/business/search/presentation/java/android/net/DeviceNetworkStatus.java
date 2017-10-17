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

package com.vestrel00.business.search.presentation.java.android.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.vestrel00.business.search.data.net.NetworkStatus;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Android implementation of {@link NetworkStatus}.
 */
@Reusable
final class DeviceNetworkStatus implements NetworkStatus {

    private final ConnectivityManager connectivityManager;

    @Inject
    DeviceNetworkStatus(ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    @Override
    public boolean hasNetworkConnectivity() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}
