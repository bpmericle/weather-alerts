package com.mericle.weather.airnow.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.HttpURLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;

import com.mericle.weather.airnow.model.Forecast;
import com.mericle.weather.airnow.model.Observation;
import com.mericle.weather.utils.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AirNowTest {
    private static final int ZIP = 76226;
    private static final String FORECASTS_BY_ZIP_CODE = "airnow_forecasts_by_zip_code.json";
    private static final String OBSERVATIONS_BY_ZIP_CODE = "airnow_observations_by_zip_code.json";

    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private HttpResponse<String> mockResponse;

    @BeforeEach
    void beforeEach() {
        reset(mockHttpClient, mockResponse);
    }

    @Test
    void givenOfflineMode_whenGetForecastsByZipCodeCalled_thenDataIsReturned() {
        try {
            String contents = DataLoader.loadFromFile(FORECASTS_BY_ZIP_CODE);
            when(mockResponse.statusCode()).thenReturn(HttpURLConnection.HTTP_OK);
            when(mockResponse.body()).thenReturn(contents);

            when(mockHttpClient.send(any(HttpRequest.class), ArgumentMatchers.<BodyHandler<String>>any()))
                    .thenReturn(mockResponse);

            AirNow airNow = new AirNow(mockHttpClient);
            Forecast[] forecasts = airNow.getForecastByZipCode(ZIP);

            verify(mockResponse).statusCode();
            verify(mockResponse).body();
            verify(mockHttpClient).send(any(HttpRequest.class), ArgumentMatchers.<BodyHandler<String>>any());

            assertNotNull(forecasts, "forecasts should not be null");
            assertTrue(forecasts.length > 0, "should contain forecasts");
        } catch (Exception e) {
            fail("No exception is expected when running this test", e);
        }
    }

    @Test
    void givenOfflineMode_whenGetObservationsByZipCodeCalled_thenDataIsReturned() {
        try {
            String contents = DataLoader.loadFromFile(OBSERVATIONS_BY_ZIP_CODE);
            when(mockResponse.statusCode()).thenReturn(HttpURLConnection.HTTP_OK);
            when(mockResponse.body()).thenReturn(contents);

            when(mockHttpClient.send(any(HttpRequest.class), ArgumentMatchers.<BodyHandler<String>>any()))
                    .thenReturn(mockResponse);

            AirNow airNow = new AirNow(mockHttpClient);
            Observation[] observations = airNow.getCurrentObservationByZipCode(ZIP);

            verify(mockResponse).statusCode();
            verify(mockResponse).body();
            verify(mockHttpClient).send(any(HttpRequest.class), ArgumentMatchers.<BodyHandler<String>>any());

            assertNotNull(observations, "observations should not be null");
            assertTrue(observations.length > 0, "should contain observations");
        } catch (Exception e) {
            fail("No exception is expected when running this test", e);
        }
    }
}
