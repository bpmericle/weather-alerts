package com.mericle.weather;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mericle.weather.airnow.alarm.AirQuality;
import com.mericle.weather.airnow.model.Forecast;
import com.mericle.weather.airnow.service.AirNow;
import com.mericle.weather.tomorrow.alarm.TemperatureApparent;
import com.mericle.weather.tomorrow.model.Timelines;
import com.mericle.weather.tomorrow.service.Tomorrow;
import com.mericle.weather.util.Email;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static Properties properties = new Properties();
    private static Email email;

    public static void main(String[] args) {
        loadConfiguration();

        int zipCode = Integer.parseInt(getConfig("ZIP_CODE", null));
        String latitude = getConfig("LATITUDE", null);
        String longitude = getConfig("LONGITUDE", null);

        String fromAddress = getConfig("FROM_ADDRESS", null);
        String toAddress = getConfig("TO_ADDRESS", null);
        String smtpUser = getConfig("SMTP_USER", null);
        String smtpPassword = getConfig("SMTP_PASSWORD", null);
        String smtpHost = getConfig("SMTP_HOST", null);
        String smtpPort = getConfig("SMTP_PORT", null);
        String apiPattern = getConfig("API_PATTERN",
                "https://api.tomorrow.io/v4/timelines?apikey=%s&location=%s,%s&fields=temperature,temperatureApparent,uvIndex,uvHealthConcern&startTime=now&endTime=nowPlus6h&timesteps=1h&units=imperial");
        String apiKey = getConfig("API_KEY", null);

        logLoadedConfig(fromAddress, toAddress, smtpUser, smtpHost, smtpPort, apiPattern, apiKey);

        email = new Email(fromAddress, toAddress, smtpUser, smtpPassword, smtpHost, smtpPort);

        Forecast[] forecasts = new AirNow().getForecastByZipCode(zipCode);

        AirQuality aqAlarm = new AirQuality();
        boolean aqAlarmTriggered = aqAlarm.isTriggered(forecasts);
        LOGGER.info("forecasts={}\r\n\r\ntriggered={}", forecasts, aqAlarmTriggered);

        if (aqAlarmTriggered) {
            sendEmail(aqAlarm.getReportSubject(), aqAlarm.getReportContent());
        } else {
            LOGGER.info("Air Quality Alarm was not triggered.");
        }

        Timelines timelines = new Tomorrow(apiPattern, apiKey).retrieveTimelines(latitude, longitude);

        TemperatureApparent taAlarm = new TemperatureApparent();
        boolean taAlarmTriggered = taAlarm.isTriggered(timelines);
        LOGGER.info("timelines={}\r\n\r\ntriggered={}", timelines, taAlarmTriggered);

        if (taAlarmTriggered) {
            sendEmail(taAlarm.getReportSubject(), taAlarm.getReportContent());
        } else {
            LOGGER.info("Temperature Apparent Alarm was not triggered.");
        }
    }

    private static void sendEmail(String subject, String content) {
        LOGGER.info("Alarm has been triggered");
        LOGGER.info("sending email, subject={}, body={}", subject, content);

        try {
            email.send(subject, content);
        } catch (MessagingException e) {
            LOGGER.error("Unable to send email.", e);
        }
    }

    private static void loadConfiguration() {
        File envFile = new File(".env");
        if (envFile.exists()) {
            try (FileInputStream fis = new FileInputStream(envFile)) {
                properties.load(fis);
                LOGGER.info("Loaded configuration from .env file");
            } catch (IOException e) {
                LOGGER.error("Failed to load .env file", e);
            }
        } else {
            LOGGER.info("No .env file found.");
        }
    }

    private static String getConfig(String key, String defaultValue) {
        String envValue = System.getenv(key);
        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }

        String fileValue = properties.getProperty(key);
        if (fileValue != null && !fileValue.isEmpty()) {
            return fileValue;
        }

        if (defaultValue != null) {
            return defaultValue;
        }

        throw new RuntimeException("Missing required configuration for key: " + key);
    }

    private static void logLoadedConfig(String fromAddress, String toAddress, String smtpUser, String smtpHost,
            String smtpPort, String apiPattern, String apiKey) {
        LOGGER.info("Loaded Configuration:");
        LOGGER.info("FROM_ADDRESS={}", fromAddress);
        LOGGER.info("TO_ADDRESS={}", toAddress);
        LOGGER.info("SMTP_USER={}", smtpUser);
        LOGGER.info("SMTP_PASSWORD=********");
        LOGGER.info("SMTP_HOST={}", smtpHost);
        LOGGER.info("SMTP_PORT={}", smtpPort);
        LOGGER.info("API_PATTERN={}", apiPattern);
        LOGGER.info("API_KEY=********");
    }

    public static List<String> getToAddresses() {
        String toAddresses = getConfig("TO_ADDRESS", null);
        return Arrays.asList(toAddresses.split(","));
    }
}