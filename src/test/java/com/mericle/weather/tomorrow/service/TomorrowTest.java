package com.mericle.weather.tomorrow.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mericle.weather.tomorrow.model.Timelines;
import com.mericle.weather.utils.DataLoader;

@ExtendWith(MockitoExtension.class)
class TomorrowTest {
    private static final String TIMELINES_BY_LATLONG = "tomorrow_retrieve_timelines.json";
    private static final String latitude = "33.123753";
    private static final String longitude = "-97.184547";

    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private HttpResponse<String> mockResponse;

    @BeforeEach
    void beforeEach() {
        reset(mockHttpClient, mockResponse);
    }

    @Test
    void givenOfflineMode_whenRequestTimelinesCalled_thenDataIsReturned() {
        try {
            String contents = DataLoader.loadFromFile(TIMELINES_BY_LATLONG);
            when(mockResponse.statusCode()).thenReturn(HttpURLConnection.HTTP_OK);
            when(mockResponse.body()).thenReturn(contents);

            when(mockHttpClient.send(any(HttpRequest.class), ArgumentMatchers.<BodyHandler<String>>any()))
                    .thenReturn(mockResponse);

            Tomorrow tomorrow = new Tomorrow(mockHttpClient);
            Timelines timelines = tomorrow.retrieveTimelines(latitude, longitude);

            verify(mockResponse).statusCode();
            verify(mockResponse).body();
            verify(mockHttpClient).send(any(HttpRequest.class), ArgumentMatchers.<BodyHandler<String>>any());

            assertNotNull(timelines, "timelines should not be null");
        } catch (Exception e) {
            fail("No exception is expected when running this test", e);
        }
    }
}
