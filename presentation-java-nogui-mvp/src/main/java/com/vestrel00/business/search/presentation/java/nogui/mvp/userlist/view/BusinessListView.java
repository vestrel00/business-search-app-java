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

package com.vestrel00.business.search.presentation.java.nogui.mvp.userlist.view;

import com.vestrel00.business.search.presentation.java.model.BusinessModel;
import com.vestrel00.business.search.presentation.java.model.CoordinatesModel;
import com.vestrel00.business.search.presentation.java.model.LocationModel;
import com.vestrel00.business.search.presentation.java.nogui.mvp.display.Display;
import com.vestrel00.business.search.presentation.java.nogui.mvp.userlist.presenter.BusinessListPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import static com.vestrel00.business.search.presentation.java.nogui.mvp.userlist.view.BusinessListViewOption.QUIT;
import static com.vestrel00.business.search.presentation.java.nogui.mvp.userlist.view.BusinessListViewOption.SHOW_AROUND_COORDINATES;
import static com.vestrel00.business.search.presentation.java.nogui.mvp.userlist.view.BusinessListViewOption.SHOW_AROUND_LOCATION;
import static com.vestrel00.business.search.presentation.java.nogui.mvp.userlist.view.BusinessListViewOption.SHOW_BUSINESS_DETAILS;
import static com.vestrel00.business.search.presentation.java.nogui.mvp.userlist.view.BusinessListViewOption.UNKNOWN;

/**
 * Shows the user list, including user-prompts related to the user-list. This view completes when
 * a business id has been entered by the user.
 */
@Singleton
public final class BusinessListView {

    private static final String OPTION_MESSAGE = "\nYou have the following options:\n"
            + "l: list businesses around a location (address, city, state, zip, and/or country)\n"
            + "c: list businesses around coordinates (latitude, longitude)\n"
            + "s: show more business details\n"
            + "q: quit\n";

    private final BusinessListPresenter presenter;
    private final Display display;

    @Inject
    BusinessListView(BusinessListPresenter presenter, Display display) {
        this.presenter = presenter;
        this.display = display;
    }

    public void initialize() {
        presenter.setView(this);
    }

    /**
     * Start showing this view's options.
     * <p>
     * This should not be invoked by the presenter, otherwise an infinite call loop may occur.
     *
     * @return the {@link BusinessListViewResult} of showing this.
     */
    public BusinessListViewResult showOptions() {
        return Observable.just(OPTION_MESSAGE)
                .map(display::getUserInput)
                .map(this::parseOption)
                .map(presenter::handleOption)
                .blockingSingle();
    }

    public void showError(Throwable error) {
        display.showError(error);
    }

    public void showBusiness(BusinessModel business) {
        display.showMessage(business.name() + ", id: " + business.id());
    }

    public LocationModel getLocation() {
        // Use an observable to build the LocationModel for more granular code
        return Observable.just("Enter the address or just enter to skip:")
                .map(display::getUserInput)
                .map(address -> LocationModel.builder().address(address))
                .doOnNext(locationBuilder -> locationBuilder.city(
                        display.getUserInput("Enter the city or just enter to skip:")))
                .doOnNext(locationBuilder -> locationBuilder.state(
                        display.getUserInput("Enter the state or just enter to skip:"))
                )
                .doOnNext(locationBuilder -> locationBuilder.zipCode(
                        display.getUserInput("Enter the zip or just enter to skip:"))
                )
                .doOnNext(locationBuilder -> locationBuilder.country(
                        display.getUserInput("Enter the country or just enter to skip:"))
                )
                .map(LocationModel.Builder::build)
                .blockingSingle();
    }

    public CoordinatesModel getCoordinates() {
        // Use an observable to build the CoordinatesModel in order to wrap any exceptions that may
        // occur when retrieving user input
        return Observable.just("Enter latitude:")
                .map(display::getUserInput)
                .map(Double::valueOf)
                .map(latitude -> CoordinatesModel.builder().latitude(latitude))
                .doOnNext(coordinatesBuilder -> coordinatesBuilder.longitude(
                        Double.valueOf(display.getUserInput("Enter longitude:")))
                )
                .map(CoordinatesModel.Builder::build)
                .doOnError(this::showError)
                .retry()
                .blockingSingle();
    }

    public String getBusinessId() {
        return display.getUserInput("Enter business id:");
    }

    private BusinessListViewOption parseOption(String userInput) {
        switch (userInput.toLowerCase()) {
            case "l":
                return SHOW_AROUND_LOCATION;
            case "c":
                return SHOW_AROUND_COORDINATES;
            case "s":
                return SHOW_BUSINESS_DETAILS;
            case "q":
                return QUIT;
            default:
                return UNKNOWN;
        }
    }
}
