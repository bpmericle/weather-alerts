package com.mericle.weather;

import javax.mail.MessagingException;

import com.mericle.weather.airnow.alarm.AirQuality;
import com.mericle.weather.airnow.model.Forecast;
import com.mericle.weather.airnow.service.AirNow;
import com.mericle.weather.tomorrow.alarm.TemperatureApparent;
import com.mericle.weather.tomorrow.model.Timelines;
import com.mericle.weather.tomorrow.service.Tomorrow;
import com.mericle.weather.util.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Forecast[] forecasts = new AirNow().getForecastByZipCode(76226);

        AirQuality aqAlarm = new AirQuality();
        boolean aqAlarmTriggered = aqAlarm.isTriggered(forecasts);
        LOGGER.info("forecasts={}\r\n\r\ntriggered={}", forecasts, aqAlarmTriggered);

        if (aqAlarmTriggered) {
            sendEmail(aqAlarm.getReportSubject(), aqAlarm.getReportContent());
        } else {
            LOGGER.info("Air Quality Alarm was not triggered.");
        }

        String latitude = "33.123753";
        String longitude = "-97.184547";
        Timelines timelines = new Tomorrow().retrieveTimelines(latitude, longitude);

        TemperatureApparent taAlarm = new TemperatureApparent();
        boolean taAlarmTriggered = taAlarm.isTriggered(timelines);
        LOGGER.info("timelines={}\r\n\r\ntriggered={}", timelines, taAlarmTriggered);

        if (taAlarmTriggered) {
            sendEmail(taAlarm.getReportSubject(), taAlarm.getReportContent());
        } else {
            LOGGER.info("Temperature Aparent Alarm was not triggered.");
        }
    }

    private static void sendEmail(String subject, String content) {
        LOGGER.info("Alarm has been triggered");
        LOGGER.info("sending email, subject={}, body={}", subject, content);

        try {
            Email email = new Email();
            email.send(subject, content);
        } catch (MessagingException e) {
            LOGGER.error("Unable to send email.", e);
        }
    }
}
