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
    private static final String RETRIEVE_TIMELINES_PATTERN = "https://data.climacell.co/v4/timelines?apikey=%s&location=%s,%s&fields=temperature,temperatureApparent,uvIndex,uvHealthConcern&startTime=now&endTime=nowPlus6h&timesteps=1h&units=imperial";
    private static final String API_KEY = "9eIYlnscx6VtOEBUgPk34LdKjCTMJeCD";
    private static final Gson GSON = new Gson();
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    private HttpClient httpClient;

    public Tomorrow() {
        this(HttpClient.newBuilder()
                .connectTimeout(TIMEOUT)
                .build());
    }

    public Tomorrow(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Timelines retrieveTimelines(String latitude, String longitude) {
        try {
            URI uri = new URI(String.format(RETRIEVE_TIMELINES_PATTERN, API_KEY, latitude, longitude));
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
                LOGGER.error("Received unexpected http response status code={}", status);
            }
        } catch (Exception e) {
            LOGGER.error("An issue occurred processing request", e);
        }

        return null;
    }
}
