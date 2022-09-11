package com.mericle.weather.airnow.alarm;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mericle.weather.airnow.model.AQI;
import com.mericle.weather.airnow.model.Category;
import com.mericle.weather.airnow.model.Forecast;
import org.junit.jupiter.api.Test;

class AirQualityTest {
    @Test
    void givenForecastsCategoryAboveThreshold_whenCheckingAlarm_thenAlarmIsTriggered() {
        Forecast[] forecasts = {
                new Forecast()
                        .withCategory(new Category().withNumber(3))
                        .withParameterName("O3")
                        .withActionDay(true)
        };

        AirQuality airQuality = new AirQuality();
        AQI[] aqis = airQuality.alarmTriggered(forecasts);
        assertTrue(aqis.length > 0, "should be triggered when category is 3 or higher");
    }

    @Test
    void givenForecastCategoryBelowThreshold_whenCheckingAlarm_thenAlarmIsTriggered() {
        Forecast[] forecasts = {
                new Forecast()
                        .withCategory(new Category().withNumber(1))
                        .withParameterName("O3")
                        .withActionDay(false)
        };

        AirQuality airQuality = new AirQuality();
        AQI[] aqis = airQuality.alarmTriggered(forecasts);
        assertTrue(aqis.length == 0, "should not be triggered when category is below 3");
    }
}
