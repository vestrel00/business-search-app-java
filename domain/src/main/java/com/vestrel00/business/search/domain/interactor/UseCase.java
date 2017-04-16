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

package com.vestrel00.business.search.domain.interactor;

/**
 * TODO remove this javadoc if not valid
 * Use cases are interactors in terms of "clean architecture" that has a single responsibility.
 *
 * <h2>Definition</h2>
 * A use case contains 1 or more collection of methods that perform work that are inextricably
 * linked in order to adhere to it's responsibility. This means that a use case may not be
 * stateless as each method it provides generates state that needs to be taken into account for
 * subsequent method calls.
 *
 * Contrary to common implementations or interpretations of use cases, these use cases
 * do not only provide 1 method for executing a task. This means that a use case may have multiple
 * tasks (methods) that work together to perform it's responsibilities. This gives more meaning
 * to having a separate domain layer with use cases as each use case can contain more business logic
 * than just the typical calling of a repository method.
 * <p>
 * Take for example this scenario. Let's say that we need to show a paginated list of items. If each
 * of our use cases only provide 1 method of execution, then we would end up having to create multiple
 * use cases (1 for getting the initial list of items and another for subsequent items).
 * The presenter would then need to use 2 different use cases and make them work together:
 * <p>
 * <pre class="code"><code class="java">
 * interface GetInitialListOfItemsUseCase {
 *      void execute(Observer o);
 * }
 *
 * interface GetMoreItemsUseCase {
 *     void execute(Observer o);
 * }
 *
 * class ShowListOfItemsPresenter {
 *     GetInitialListOfItemsUseCase getInitialListOfItemsUseCase;
 *     GetMoreItemsUseCase getMoreItemsUseCase;
 *     ...
 * }
 * </code></pre>
 *
 * The above seems fine and dandy but the implementations of each of the above use case would
 * essentially be limited to a stateless call to the repository to retrieve the items. Furthermore,
 * the consumer of the use case (the presenters) would also have to be responsible for maintaining
 * state regarding the items. The use case then just becomes a thin wrapper around repository calls
 * (for the most part). This gives less value to having a separate domain layer.
 * <p>
 * OR, instead of multiple use cases, we would need to add parameters to the method of execution and
 * let the presenter maintain the pagination state:
 *
 * <pre class="code"><code class="java">
 * interface GetListOfItemsUseCase {
 *     void execute(int startIndex, int amountToFetch, Observer o);
 * }
 *
 * class ShowListOfItemsPresenter {
 *     int startIndex;
 *
 *     void showMoreItems() {
 *          getListOfItemsUseCase.execute(startIndex, 5, new Observer() {
 *              void onComplete(List<Item> items) {
 *                  startIndex += items.size();
 *                  // show these additional items
 *              }
 *          });
 *     }
 * }
 * </code></pre>
 *
 * The startIndex in the example above is business logic that should live in the domain layer.
 * Remember that the entire purpose of a separate domain layer is that it contains as much of our
 * business logic as possible. If we do this, our presenters would not need to maintain any state
 * and would only be an observer that reacts to events published by the use cases. Thus, the
 * presenters become pure observers whose sole purpose is to "present" the outputs of a use case.
 * E.G.
 *
 * <pre class="code"><code class="java">
 * interface GetListOfItemsUseCase {
 *
 *      void getItems(Callback c);
 *
 *      void getMoreItems(Callback c);
 *
 *      interface Callback {
 *          void onGetItemsSuccess(List<Item> items);
 *          void onGetMoreItemsSuccess(List<Item> moreItems);
 *          void onGetItemsFailed();
 *          void onGetMoreItemsFailed();
 *      }
 * }
 *
 * class ShowListOfItemsPresenter {
 *     int startIndex;
 *
 *     void showMoreItems() {
 *          getListOfItemsUseCase.execute(startIndex, 5, new Observer() {
 *              void onComplete(List<Item> items) {
 *                  startIndex += items.size();
 *                  // show these additional items
 *              }
 *          });
 *     }
 * }
 * </code></pre>
 *
 * <h2>Execution</h2>
 * Use cases are executed in the given
 * {@link com.vestrel00.business.search.domain.executor.ExecutionThread}
 * and publishes events to subscribers in the given
 * {@link com.vestrel00.business.search.domain.executor.PostExecutionThread}.
 *
 * <h2>Lifecycle</h2>
 * Use cases does not (and should not) know how it is being presented. As such, the only lifecycle
 * event use cases have is when it should be disposed ("destroyed"). Thus, use cases are also
 * {@link io.reactivex.disposables.Disposable}s.
 */

/**
 * Use cases are interactors in terms of "clean architecture", which encapsulate a focused unit of
 * work. These do not, by themselves, determine where (what thread) the work will be done.
 *
 * @param <K> the type of the input passed as a parameter to {@link #execute(K)}
 * @param <V> the type {@link #execute(V)} returns. E.G. Single, Observable, etc
 */
public interface UseCase<K, V> {

    V execute(K params);
}
