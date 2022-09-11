package com.mericle.weather;

import javax.mail.MessagingException;

import com.mericle.weather.airnow.alarm.AirQuality;
import com.mericle.weather.airnow.model.AQI;
import com.mericle.weather.airnow.model.Forecast;
import com.mericle.weather.airnow.service.AirNow;
import com.mericle.weather.util.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String SUBJECT_PATTERN = "Weather Alarm [%s] Has Been Triggered!";
    private static final String CONTENT_TEMPLATE = "<html>" +
            "    <head>" +
            "      <style>" +
            "        .key {" +
            "          font-weight: bold;" +
            "        }" +
            "        #body {" +
            "          font-size: 16px;" +
            "          width: 794px;" +
            "          margin-bottom: 10px;" +
            "        }" +
            "      </style>" +
            "    </head>" +
            "    <body>" +
            "      <div id='body'>" +
            "        %s" +
            "      </div>" +
            "      <img src=\"https://www.epa.gov/sites/default/files/2014-09/aqiguidepm.png\" alt=\"AQI Guide\" />" +
            "      <div>Click <a href=\"https://www.airnow.gov/aqi/aqi-basics/\">here for more information</a>.</div>" +
            "    </body>" +
            "</html>";

    public static void main(String[] args) {
        Forecast[] forecasts = new AirNow().getForecastByZipCode(76226);

        AirQuality alarm = new AirQuality();
        AQI[] aqis = alarm.alarmTriggered(forecasts);
        LOGGER.info("forecasts={}\r\n\r\naqis={}", forecasts, aqis);

        if (aqis.length > 0) {
            LOGGER.info("Alarm has been triggered");

            String subject = String.format(SUBJECT_PATTERN, "Air Quality");
            String content = buildBody(aqis);
            LOGGER.info("sending email, subject={}, body={}", subject, content);

            try {
                Email email = new Email();
                email.send(subject, content);
            } catch (MessagingException e) {
                LOGGER.error("Unable to send email.", e);
            }
        }
    }

    private static String buildBody(AQI[] aqis) {
        StringBuilder builder = new StringBuilder();

        for (AQI aqi : aqis) {
            builder.append(
                    String.format("<div style=\"background-color:#%s;color:white;padding:10px;margin-bottom:5px;\">",
                            aqi.hexColor))
                    // .append("<p><span class='key'>Air Quality Index (AQI):</span> ")
                    // .append(aqi.index)
                    // .append("</p>")
                    .append("<p><span class='key'>Level of Health Concern:</span> ")
                    .append(aqi.description)
                    .append("</p>")
                    .append("<p><span class='key'>Triggered By:</span> ")
                    .append(aqi.parameterName)
                    .append("</p>")
                    .append("</div>");
        }
        return String.format(CONTENT_TEMPLATE, builder.toString());
    }
}
