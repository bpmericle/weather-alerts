package com.mericle.weather.tomorrow.alarm;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mericle.weather.tomorrow.model.Interval;
import com.mericle.weather.tomorrow.model.Timeline;
import com.mericle.weather.tomorrow.model.Timelines;
import com.mericle.weather.tomorrow.model.Trigger;
import com.mericle.weather.tomorrow.model.Values;

public class TemperatureApparent {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureApparent.class);
    private static final double UPPER_ALARM_THRESHOLD = 95.0;
    private static final double LOWER_ALARM_THRESHOLD = 40.0;
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
            "    </body>" +
            "</html>";

    List<Trigger> triggers = new ArrayList<>();

    public boolean isTriggered(Timelines timelines) {
        for (Timeline t : timelines.getData().getTimelines()) {
            LOGGER.debug("inspecting timeline={}", t);

            for (Interval i : t.getIntervals()) {
                Values v = i.getValues();

                double apparentTemp = v.getTemperatureApparent();
                if (apparentTemp < LOWER_ALARM_THRESHOLD || apparentTemp > UPPER_ALARM_THRESHOLD) {
                    Trigger trigger = Trigger.builder()
                            .startDateRange(t.getStartTime())
                            .endDateRange(t.getEndTime())
                            .date(i.getStartTime())
                            .temperature(v.getTemperature())
                            .temperatureApparent(apparentTemp)
                            .uvIndex(v.getUvIndex())
                            .uvHealthConcern(v.getUvHealthConcern())
                            .build();

                    LOGGER.debug("temperature apparent triggered on {}", trigger);
                    triggers.add(trigger);
                }
            }
        }

        return !triggers.isEmpty();
    }

    public String getReportSubject() {
        if (triggers.isEmpty()) {
            return "";
        }

        return String.format(SUBJECT_PATTERN, "Temperature Apparent");
    }

    public String getReportContent() {
        if (triggers.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        for (Trigger trigger : triggers) {
            String dateDisplay = trigger.getDate();

            try {
                OffsetDateTime localDateTime = Instant.parse(trigger.getDate()).atZone(ZoneId.systemDefault())
                        .toOffsetDateTime();
                dateDisplay = DateTimeFormatter.ofPattern("ha 'on' EEE, MMM dd, yyyy").format(localDateTime);
            } catch (DateTimeParseException e) {
                LOGGER.warn("Unable to parse date={}", dateDisplay, e);
            }

            builder.append(
                    String.format("<div style=\"background-color:#%s;color:white;padding:10px;margin-bottom:5px;\">",
                            "ff0000"))
                    .append("<p><span class='key'>Issued For:</span> ")
                    .append(dateDisplay)
                    .append("</p>")
                    .append("<p><span class='key'>Temperature:</span> ")
                    .append(trigger.getTemperature()).append(" &#8457;")
                    .append("</p>")
                    .append("<p><span class='key'>Temperature (Apparent):</span> ")
                    .append(trigger.getTemperatureApparent()).append(" &#8457;")
                    .append("</p>")
                    .append("<p><span class='key'>UV Index:</span> ")
                    .append(trigger.getUvIndex()).append(" - ").append(trigger.getUvIndexDisplay())
                    .append("</p>")
                    .append("<p><span class='key'>UV Health Concern:</span> ")
                    .append(trigger.getUvHealthConcern()).append(" - ").append(trigger.getUvHealthConcernDisplay())
                    .append("</p>")
                    .append("</div>");
        }

        return String.format(CONTENT_TEMPLATE, builder.toString());
    }
}
