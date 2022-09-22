package com.mericle.weather.airnow.alarm;

import java.util.ArrayList;
import java.util.List;

import com.mericle.weather.airnow.model.AQI;
import com.mericle.weather.airnow.model.Forecast;
import com.mericle.weather.airnow.model.Trigger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AirQuality {
    private static final Logger LOGGER = LoggerFactory.getLogger(AirQuality.class);
    private static final int ALARM_THRESHOLD = 3;
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

    private List<Trigger> triggers;

    public boolean isTriggered(Forecast[] forecast) {
        triggers = new ArrayList<>();
        for (Forecast f : forecast) {
            LOGGER.debug("inspecting forecast={}", f);

            AQI aqi = AQI.byCategory(f.getCategory().getNumber());
            if (aqi.category >= ALARM_THRESHOLD) {
                Trigger trigger = Trigger.builder()
                        .aqi(aqi)
                        .index(f.getAqi())
                        .issuedDate(f.getDateIssue())
                        .forecastDate(f.getDateForecast())
                        .parameterName(f.getParameterName())
                        .actionDay(f.isActionDay())
                        .build();

                LOGGER.debug("air quality triggered on {}", aqi);
                triggers.add(trigger);
            }
        }

        return !triggers.isEmpty();
    }

    public String getReportSubject() {
        if (triggers.isEmpty()) {
            return "";
        }

        return String.format(SUBJECT_PATTERN, "Air Quality");
    }

    public String getReportContent() {
        if (triggers.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        for (Trigger trigger : triggers) {
            builder.append(
                    String.format("<div style=\"background-color:#%s;color:white;padding:10px;margin-bottom:5px;\">",
                            trigger.getAqi().hexColor))
                    .append("<p><span class='key'>Issued Date:</span> ")
                    .append(trigger.getIssuedDate())
                    .append("</p>")
                    .append("<p><span class='key'>Forecast Date:</span> ")
                    .append(trigger.getForecastDate())
                    .append("</p>");
            if (trigger.getIndex() >= 0) {
                builder.append("<p><span class='key'>Air Quality Index:</span> ")
                        .append(trigger.getIndex())
                        .append("</p>");
            }
            builder.append("<p><span class='key'>Level of Health Concern:</span> ")
                    .append(trigger.getAqi().description)
                    .append("</p>")
                    .append("<p><span class='key'>Triggered By:</span> ")
                    .append(trigger.getParameterName())
                    .append("</p>")
                    .append("</div>");
        }

        return String.format(CONTENT_TEMPLATE, builder.toString());
    }
}
