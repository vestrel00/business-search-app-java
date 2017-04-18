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

package com.vestrel00.business.search.presentation.java.nogui.mvp.userlist;

import com.vestrel00.business.search.presentation.java.model.BusinessModel;
import com.vestrel00.business.search.presentation.java.model.CoordinatesModel;
import com.vestrel00.business.search.presentation.java.model.LocationModel;
import com.vestrel00.business.search.presentation.java.nogui.mvp.display.Display;
import com.vestrel00.business.search.presentation.java.nogui.mvp.view.NoGuiView;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Shows the user list, including user-prompts related to the user-list. This view completes when
 * a business id has been entered by the user.
 */
@Singleton
public final class BusinessListView implements NoGuiView<String> {

    private static final String OPTIONS = "You may display a list of businesses using the "
            + "following options:\n"
            + "l: list by location (address, city, state, zip, and/or country)\n"
            + "c: list by coordinates (latitude, longitude)\n"
            + "s: enter a business id to view more details for that business\n";
    private static final String OPTION_LOCATION = "l";
    private static final String OPTION_COORDINATES = "c";
    private static final String OPTION_SELECT = "s";

    private final BusinessListPresenter presenter;
    private final Display display;

    @Inject
    BusinessListView(BusinessListPresenter presenter, Display display) {
        this.presenter = presenter;
        this.display = display;
    }

    /**
     * @return the business id entered by the user.
     */
    @Override
    public String show() {
        presenter.setView(this);

        String businessId = "";
        while (businessId.isEmpty()) {
            businessId = Observable.just(OPTIONS)
                    .map(display::getUserInput)
                    .doOnNext(this::validateOption)
                    .map(this::handleOption)
                    .onErrorReturnItem("")
                    .blockingSingle();
        }
        return businessId;
    }

    @Override
    public void showError(Throwable error) {
        display.showError(error);
    }

    void showBusiness(BusinessModel business) {
        display.showMessage(business.name() + ", id: " + business.id());
    }

    private LocationModel getLocation() {
        // Use an observable to build the LocationModel in order to wrap any exceptions that may
        // occur when retrieving user input
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

    private CoordinatesModel getCoordinates() {
        // Use an observable to build the CoordinatesModel in order to wrap any exceptions that may
        // occur when retrieving user input
        return Observable.just("Enter latitude:")
                .map(display::getUserInput)
                .map(Long::valueOf)
                .map(latitude -> CoordinatesModel.builder().latitude(latitude))
                .doOnNext(coordinatesBuilder -> coordinatesBuilder.longitude(
                        Long.valueOf(display.getUserInput("Enter longitude:")))
                )
                .map(CoordinatesModel.Builder::build)
                .doOnError(this::showError)
                .retry()
                .blockingSingle();
    }

    private void validateOption(String option) throws IllegalArgumentException {
        if (OPTION_LOCATION.equalsIgnoreCase(option)
                || OPTION_COORDINATES.equalsIgnoreCase(option)
                || OPTION_SELECT.equalsIgnoreCase(option)) {
            return;
        }
        throw new IllegalArgumentException("Invalid option: " + option);
    }

    private String handleOption(String option) {
        switch (option) {
            case OPTION_LOCATION:
                presenter.showBusinessesAroundLocation(getLocation());
                break;
            case OPTION_COORDINATES:
                presenter.showBusinessesAroundCoordinates(getCoordinates());
                break;
            case OPTION_SELECT:
                return display.getUserInput();
            default:
                // Should never get here. This is for checkstyle and paranoia
                throw new IllegalArgumentException("Invalid option: " + option);
        }

        return "";
    }
}
