package com.mericle.weather.tomorrow.service;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.mericle.weather.tomorrow.model.Timelines;

public class Tomorrow {
    private static final Logger LOGGER = LoggerFactory.getLogger(Tomorrow.class);
    private static final Gson GSON = new Gson();
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    private final HttpClient httpClient;
    private final String retrieveTimelinesPattern;
    private final String apiKey;

    public Tomorrow(String retrieveTimelinesPattern, String apiKey) {
        this(retrieveTimelinesPattern, apiKey, HttpClient.newBuilder()
                .connectTimeout(TIMEOUT)
                .build());
    }

    public Tomorrow(String retrieveTimelinesPattern, String apiKey, HttpClient httpClient) {
        this.retrieveTimelinesPattern = retrieveTimelinesPattern;
        this.apiKey = apiKey;
        this.httpClient = httpClient;
    }

    public Timelines retrieveTimelines(String latitude, String longitude) {
        try {
            String apiUrl = String.format(retrieveTimelinesPattern, apiKey, latitude, longitude);
            LOGGER.info("API URL: {}", apiUrl);
            URI uri = new URI(apiUrl);
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
                return GSON.fromJson(body, Timelines.class);
            } else {
                LOGGER.error("Received unexpected HTTP response status code={}", status);
            }
        } catch (Exception e) {
            LOGGER.error("An issue occurred processing the request", e);
        }

        return null;
    }
}
