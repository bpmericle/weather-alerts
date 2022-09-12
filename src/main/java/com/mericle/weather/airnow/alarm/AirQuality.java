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

    private List<AQI> aqis;

    public boolean isTriggered(Forecast[] forecast) {
        aqis = new ArrayList<>();
        for (Forecast f : forecast) {
            LOGGER.debug("inspecting forecast={}", f);

            AQI aqi = AQI.byCategory(f.getCategory().getNumber());
            if (aqi.category >= ALARM_THRESHOLD) {
                aqi.index = f.getAqi();
                aqi.parameterName = f.getParameterName();
                aqi.actionDay = f.isActionDay();
                aqi.issuedDate = f.getDateIssue();
                aqi.forecastDate = f.getDateForecast();

                LOGGER.debug("air quality triggered on {}", aqi);
                aqis.add(aqi);
            }
        }

        return !aqis.isEmpty();
    }

    public String getReportSubject() {
        if (aqis.isEmpty()) {
            return "";
        }

        return String.format(SUBJECT_PATTERN, "Air Quality");
    }

    public String getReportContent() {
        if (aqis.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        for (AQI aqi : aqis) {
            builder.append(
                    String.format("<div style=\"background-color:#%s;color:white;padding:10px;margin-bottom:5px;\">",
                            aqi.hexColor))
                    .append("<p><span class='key'>Issued Date:</span> ")
                    .append(aqi.issuedDate)
                    .append("</p>")
                    .append("<p><span class='key'>Forecast Date:</span> ")
                    .append(aqi.forecastDate)
                    .append("</p>");
            if (aqi.index >= 0) {
                builder.append("<p><span class='key'>Air Quality Index:</span> ")
                        .append(aqi.index)
                        .append("</p>");
            }
            builder.append("<p><span class='key'>Level of Health Concern:</span> ")
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
