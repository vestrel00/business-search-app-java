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

package com.vestrel00.business.search.presentation.java.nogui.mvp.ui.businesslist.presenter;

import com.vestrel00.business.search.domain.Coordinates;
import com.vestrel00.business.search.domain.Location;
import com.vestrel00.business.search.domain.executor.UseCaseHandler;
import com.vestrel00.business.search.domain.interactor.GetBusinessesAroundCoordinates;
import com.vestrel00.business.search.domain.interactor.GetBusinessesAroundLocation;
import com.vestrel00.business.search.java.AbstractJavaTestCase;
import com.vestrel00.business.search.presentation.java.model.CoordinatesModel;
import com.vestrel00.business.search.presentation.java.model.LocationModel;
import com.vestrel00.business.search.presentation.java.model.mapper.ModelMapper;
import com.vestrel00.business.search.presentation.java.model.mapper.ModelMapperFactory;
import com.vestrel00.business.search.presentation.java.nogui.mvp.ui.businesslist.view.BusinessListView;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class BusinessListPresenterTest extends AbstractJavaTestCase {

    @InjectMocks
    private BusinessListPresenter testSubject;

    @Mock
    private GetBusinessesAroundLocation getBusinessesAroundLocation;

    @Mock
    private GetBusinessesAroundCoordinates getBusinessesAroundCoordinates;

    @Mock
    private ModelMapperFactory modelMapperFactory;

    @Mock
    private BusinessListObserverFactory businessListObserverFactory;

    @Mock
    private UseCaseHandler useCaseHandler;

    @Mock
    private BusinessListView view;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        testSubject.setView(view);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void showBusinessesAroundLocation_executesGetBusinessesAroundLocationUseCase() throws Exception {
        // GIVEN
        LocationModel locationModel = mock(LocationModel.class);
        ModelMapper<LocationModel, Location> locationModelMapper = mock(ModelMapper.class);
        Location location = mock(Location.class);
        BusinessListObserver observer = mock(BusinessListObserver.class);

        when(view.getLocation()).thenReturn(locationModel);
        when(modelMapperFactory.locationModelMapper()).thenReturn(locationModelMapper);
        when(locationModelMapper.map(locationModel)).thenReturn(location);
        when(businessListObserverFactory.create(view)).thenReturn(observer);

        // WHEN
        testSubject.showBusinessesAroundLocation();

        // THEN
        verify(useCaseHandler).execute(getBusinessesAroundLocation, location, observer);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void showBusinessesAroundCoordinates_executesGetBusinessesAroundCoordinatesUseCase() throws Exception {
        // GIVEN
        CoordinatesModel coordinatesModel = mock(CoordinatesModel.class);
        ModelMapper<CoordinatesModel, Coordinates> coordinatesModelMapper = mock(ModelMapper.class);
        Coordinates coordinates = mock(Coordinates.class);
        BusinessListObserver observer = mock(BusinessListObserver.class);

        when(view.getCoordinates()).thenReturn(coordinatesModel);
        when(modelMapperFactory.coordinatesModelMapper()).thenReturn(coordinatesModelMapper);
        when(coordinatesModelMapper.map(coordinatesModel)).thenReturn(coordinates);
        when(businessListObserverFactory.create(view)).thenReturn(observer);

        // WHEN
        testSubject.showBusinessesAroundCoordinates();

        // THEN
        verify(useCaseHandler).execute(getBusinessesAroundCoordinates, coordinates, observer);
    }
}
