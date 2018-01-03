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

package com.vestrel00.business.search.presentation.java.android.executor;

import com.vestrel00.business.search.domain.executor.PostExecutionThread;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * A {@link PostExecutionThread} that provides the {@link AndroidSchedulers#mainThread()} scheduler.
 */
@Reusable
final class MainPostExecutionThread implements PostExecutionThread {

    @Inject
    MainPostExecutionThread() {
    }

    @Override
    public Scheduler scheduler() {
        return AndroidSchedulers.mainThread();
    }
}
