package com.mericle.weather.airnow.service;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.mericle.weather.airnow.model.Forecast;
import com.mericle.weather.airnow.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AirNow {
    private static final Logger LOGGER = LoggerFactory.getLogger(AirNow.class);
    private static final String FORECASTS_BY_ZIP_CODE_URI_PATTERN = "https://www.airnowapi.org/aq/forecast/zipCode/?format=application/json&zipCode=%d&date=%s&distance=1&API_KEY=%s";
    private static final String OBSERVATIONS_BY_ZIP_CODE_URI_PATTERN = "https://www.airnowapi.org/aq/observation/zipCode/current/?format=application/json&zipCode=%d&distance=1&API_KEY=%s";
    private static final String API_KEY = "7418FF02-9E13-4C03-8239-77539B85D91C";
    private static final Gson GSON = new Gson();
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    private HttpClient httpClient;

    public AirNow() {
        this(HttpClient.newBuilder()
                .connectTimeout(TIMEOUT)
                .build());
    }

    public AirNow(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Forecast[] getForecastByZipCode(int zipCode) {
        try {
            String date = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now());

            URI uri = new URI(String.format(FORECASTS_BY_ZIP_CODE_URI_PATTERN, zipCode, date, API_KEY));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            LOGGER.debug("request={}", request);

            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString(StandardCharsets.UTF_8));
            int status = response.statusCode();
            String body = response.body();
            LOGGER.debug("response code={}, body={}", status, body);

            if (HttpURLConnection.HTTP_OK == status) {
                return GSON.fromJson(body, Forecast[].class);
            } else {
                LOGGER.error("Received unexpected http response status code={}", status);
            }
        } catch (Exception e) {
            LOGGER.error("An issue occurred processing request", e);
        }

        return null;
    }

    public Observation[] getCurrentObservationByZipCode(int zipCode) {
        try {
            URI uri = new URI(String.format(OBSERVATIONS_BY_ZIP_CODE_URI_PATTERN, zipCode, API_KEY));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            LOGGER.debug("request={}", request);

            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString(StandardCharsets.UTF_8));
            int status = response.statusCode();
            String body = response.body();
            LOGGER.debug("response code={}, body={}", status, body);

            if (HttpURLConnection.HTTP_OK == status) {
                return GSON.fromJson(body, Observation[].class);
            } else {
                LOGGER.error("Received unexpected http response status code={}", status);
            }
        } catch (Exception e) {
            LOGGER.error("An issue occurred processing request", e);
        }

        return null;
    }
}
