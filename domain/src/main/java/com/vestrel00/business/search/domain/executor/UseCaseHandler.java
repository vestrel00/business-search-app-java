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

package com.vestrel00.business.search.domain.executor;

import com.vestrel00.business.search.domain.interactor.UseCase;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Provides default handling of use cases.
 * <p>
 * This handler executes a use cases in the given {@link ExecutionThread} and then publishes results
 * in the given {@link PostExecutionThread}.
 */
public final class UseCaseHandler {

    private final ExecutionThread executionThread;
    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable disposables;

    private UseCase previousUseCase;
    private Object previousUseCaseParams;

    @Inject
    UseCaseHandler(ExecutionThread executionThread,
                   PostExecutionThread postExecutionThread,
                   CompositeDisposable disposables) {
        this.executionThread = executionThread;
        this.postExecutionThread = postExecutionThread;
        this.disposables = disposables;
    }

    public <K, V> void execute(UseCase<K, V> useCase, K params,
                               DisposableSingleObserver<V> observer) {
        setLastUseCase(useCase, params);
        Disposable disposable = useCase.execute(params)
                .subscribeOn(executionThread.scheduler())
                .observeOn(postExecutionThread.scheduler())
                .subscribeWith(observer);
        disposables.add(disposable);
    }

    public void executePreviousUseCase(DisposableSingleObserver observer) {
        if (previousUseCase != null) {
            execute(previousUseCase, previousUseCaseParams, observer);
        }
    }

    public void clear() {
        // clear only and not dispose the composite to enable composite reuse
        disposables.clear();
    }

    private void setLastUseCase(UseCase previousUseCase, Object previousUseCaseParams) {
        this.previousUseCase = previousUseCase;
        this.previousUseCaseParams = previousUseCaseParams;
    }
}
