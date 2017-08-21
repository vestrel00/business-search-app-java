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

package com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.list.presenter;

import com.vestrel00.business.search.domain.Coordinates;
import com.vestrel00.business.search.domain.Location;
import com.vestrel00.business.search.domain.executor.UseCaseHandler;
import com.vestrel00.business.search.domain.interactor.GetBusinessesAroundCoordinates;
import com.vestrel00.business.search.domain.interactor.GetBusinessesAroundLocation;
import com.vestrel00.business.search.java.AbstractJavaTestCase;
import com.vestrel00.business.search.presentation.java.model.CoordinatesModel;
import com.vestrel00.business.search.presentation.java.model.LocationModel;
import com.vestrel00.business.search.presentation.java.model.mapper.ModelMapper;
import com.vestrel00.business.search.presentation.java.model.mapper.ModelMapperHolder;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.common.presenter.BusinessObserver;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.common.presenter.BusinessObserverFactory;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.business.list.view.BusinessListView;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class BusinessListPresenterImplTest extends AbstractJavaTestCase {

    @InjectMocks
    private BusinessListPresenterImpl testSubject;

    @Mock
    private GetBusinessesAroundLocation getBusinessesAroundLocation;

    @Mock
    private GetBusinessesAroundCoordinates getBusinessesAroundCoordinates;

    @Mock
    private ModelMapperHolder modelMapperHolder;

    @Mock
    private BusinessObserverFactory businessObserverFactory;

    @Mock
    private UseCaseHandler useCaseHandler;

    @Mock
    private BusinessListModelFactory modelFactory;

    @Mock
    private BusinessListView view;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        testSubject.onViewInitialized(view);
    }

    @Test
    public void onShowBusinessesAroundLocation_executesGetBusinessesAroundLocationUseCase() throws Exception {
        // GIVEN
        Location location = mock(Location.class);
        BusinessObserver observer = mock(BusinessObserver.class);

        given_onShowBusinessesAroundLocation(location, observer, "");

        // WHEN
        testSubject.onShowBusinessesAroundLocation();

        // THEN
        verify(useCaseHandler).execute(getBusinessesAroundLocation, location, observer);
    }

    @Test
    public void onShowBusinessesAroundLocation_showsBusinessesAroundMessage() throws Exception {
        // GIVEN
        String locationModelString = "location";

        given_onShowBusinessesAroundLocation(mock(Location.class), mock(BusinessObserver.class),
                locationModelString);

        // WHEN
        testSubject.onShowBusinessesAroundLocation();

        // THEN
        verify(view).showBusinessesAroundMessage(locationModelString);
    }

    @Test
    public void showBusinessesAroundCoordinates_executesGetBusinessesAroundCoordinatesUseCase() throws Exception {
        // GIVEN
        Coordinates coordinates = mock(Coordinates.class);
        BusinessObserver observer = mock(BusinessObserver.class);

        given_onShowBusinessesAroundCoordinates(coordinates, observer, "");

        // WHEN
        testSubject.onShowBusinessesAroundCoordinates();

        // THEN
        verify(useCaseHandler).execute(getBusinessesAroundCoordinates, coordinates, observer);
    }

    @Test
    public void onShowBusinessesAroundCoordinates_showsBusinessesAroundMessage() throws Exception {
        // GIVEN
        String coordinatesModelString = "coordinates";

        given_onShowBusinessesAroundCoordinates(mock(Coordinates.class), mock(BusinessObserver.class),
                coordinatesModelString);

        // WHEN
        testSubject.onShowBusinessesAroundCoordinates();

        // THEN
        verify(view).showBusinessesAroundMessage(coordinatesModelString);
    }

    @SuppressWarnings("unchecked")
    private void given_onShowBusinessesAroundLocation(Location location, BusinessObserver observer,
                                                      String locationModelString) {
        LocationModel locationModel = mock(LocationModel.class);
        ModelMapper<LocationModel, Location> locationModelMapper = mock(ModelMapper.class);

        when(modelFactory.getLocation(view)).thenReturn(locationModel);
        when(locationModel.toString()).thenReturn(locationModelString);
        when(modelMapperHolder.locationModelMapper()).thenReturn(locationModelMapper);
        when(locationModelMapper.map(locationModel)).thenReturn(location);
        when(businessObserverFactory.create(view)).thenReturn(observer);
    }

    @SuppressWarnings("unchecked")
    private void given_onShowBusinessesAroundCoordinates(Coordinates coordinates, BusinessObserver observer,
                                                         String coordinatesModelString) {
        CoordinatesModel coordinatesModel = mock(CoordinatesModel.class);
        ModelMapper<CoordinatesModel, Coordinates> coordinatesModelMapper = mock(ModelMapper.class);

        when(modelFactory.getCoordinates(view)).thenReturn(coordinatesModel);
        when(coordinatesModel.toString()).thenReturn(coordinatesModelString);
        when(modelMapperHolder.coordinatesModelMapper()).thenReturn(coordinatesModelMapper);
        when(coordinatesModelMapper.map(coordinatesModel)).thenReturn(coordinates);
        when(businessObserverFactory.create(view)).thenReturn(observer);
    }
}
