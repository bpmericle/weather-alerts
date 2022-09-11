package com.mericle.weather.airnow.alarm;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mericle.weather.airnow.model.Category;
import com.mericle.weather.airnow.model.Forecast;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class AirQualityTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AirQualityTest.class);

    @Test
    void givenForecastsCategoryAboveThreshold_whenCheckingAlarm_thenAlarmIsTriggered() {
        Forecast[] forecasts = {
                new Forecast()
                        .withCategory(new Category().withNumber(3))
                        .withParameterName("O3")
                        .withAqi(1)
                        .withActionDay(true)
                        .withDateIssue("2022-09-01")
                        .withDateForecast("2022-09-02")
        };

        AirQuality airQuality = new AirQuality();
        boolean triggered = airQuality.isTriggered(forecasts);
        assertTrue(triggered, "should be triggered when category is 3 or higher");
        assertFalse(airQuality.getReportSubject().isBlank());
        assertFalse(airQuality.getReportContent().isBlank());
        
        LOGGER.info("{}\r\n\r\n{}", airQuality.getReportSubject(), airQuality.getReportContent());
    }

    @Test
    void givenForecastCategoryBelowThreshold_whenCheckingAlarm_thenAlarmIsTriggered() {
        Forecast[] forecasts = {
                new Forecast()
                        .withCategory(new Category().withNumber(1))
                        .withParameterName("O3")
                        .withAqi(-1)
                        .withActionDay(false)
        };

        AirQuality airQuality = new AirQuality();
        boolean triggered = airQuality.isTriggered(forecasts);
        assertFalse(triggered, "should not be triggered when category is below 3");
        assertTrue(airQuality.getReportSubject().isBlank());
        assertTrue(airQuality.getReportContent().isBlank());
    }
}
