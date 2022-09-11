package com.mericle.weather.airnow.alarm;

import java.util.ArrayList;
import java.util.List;

import com.mericle.weather.airnow.model.AQI;
import com.mericle.weather.airnow.model.Forecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AirQuality {
    private static final Logger LOGGER = LoggerFactory.getLogger(AirQuality.class);
    private static final int ALARM_THRESHOLD = 3;

    public AQI[] alarmTriggered(Forecast[] forecast) {
        List<AQI> triggered = new ArrayList<>();
        for (Forecast f : forecast) {
            AQI aqi = AQI.byCategory(f.getCategory().getNumber());
            if (aqi.category >= ALARM_THRESHOLD) {
                aqi.index = f.getAqi();
                aqi.parameterName = f.getParameterName();
                aqi.actionDay = f.isActionDay();

                LOGGER.debug("air quality triggered on {}", aqi);
                triggered.add(aqi);
            }
        }

        return triggered.toArray(new AQI[0]);
    }
}
