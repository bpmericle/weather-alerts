package com.mericle.weather;

import javax.mail.MessagingException;

import com.mericle.weather.airnow.alarm.AirQuality;
import com.mericle.weather.airnow.model.Forecast;
import com.mericle.weather.airnow.service.AirNow;
import com.mericle.weather.util.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Forecast[] forecasts = new AirNow().getForecastByZipCode(76226);

        AirQuality alarm = new AirQuality();
        boolean triggered = alarm.isTriggered(forecasts);
        LOGGER.info("forecasts={}\r\n\r\ntriggered={}", forecasts, triggered);

        if (triggered) {
            LOGGER.info("Alarm has been triggered");

            String subject = alarm.getReportSubject();
            String content = alarm.getReportContent();
            LOGGER.info("sending email, subject={}, body={}", subject, content);

            try {
                Email email = new Email();
                email.send(subject, content);
            } catch (MessagingException e) {
                LOGGER.error("Unable to send email.", e);
            }
        }
    }
}
