package com.mericle.weather.tomorrow.alarm;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mericle.weather.tomorrow.model.Data;
import com.mericle.weather.tomorrow.model.Interval;
import com.mericle.weather.tomorrow.model.Timeline;
import com.mericle.weather.tomorrow.model.Timelines;
import com.mericle.weather.tomorrow.model.Values;

class TemperatureApparentTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureApparentTest.class);

    @Test
    void givenTimelineIntervalAboveThreshold_whenCheckingAlarm_thenAlarmIsTriggered() {
        Values values = new Values()
                .withTemperature(90)
                .withTemperatureApparent(96)
                .withUvIndex(1)
                .withUvHealthConcern(1);

        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval().withStartTime("2022-09-11T08:00:00Z").withValues(values));

        List<Timeline> timelineList = new ArrayList<>();
        timelineList.add(new Timeline().withStartTime("2022-09-11T07:00:00Z").withEndTime("2022-09-11T13:00:00Z")
                .withIntervals(intervals));

        Timelines timelines = new Timelines().withData(new Data().withTimelines(timelineList));

        TemperatureApparent temperatureApparent = new TemperatureApparent();
        boolean triggered = temperatureApparent.isTriggered(timelines);
        assertTrue(triggered, "should be triggered when apparent temperature is higher than 95");
        assertFalse(temperatureApparent.getReportSubject().isBlank());
        assertFalse(temperatureApparent.getReportContent().isBlank());

        LOGGER.info("{}\r\n\r\n{}", temperatureApparent.getReportSubject(), temperatureApparent.getReportContent());
    }

    @Test
    void givenTimelineIntervalBelowThreshold_whenCheckingAlarm_thenAlarmIsTriggered() {
        Values values = new Values()
                .withTemperature(40)
                .withTemperatureApparent(39)
                .withUvIndex(1)
                .withUvHealthConcern(1);

        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval().withStartTime("2022-09-11T08:00:00Z").withValues(values));

        List<Timeline> timelineList = new ArrayList<>();
        timelineList.add(new Timeline().withStartTime("2022-09-11T07:00:00Z").withEndTime("2022-09-11T13:00:00Z")
                .withIntervals(intervals));

        Timelines timelines = new Timelines().withData(new Data().withTimelines(timelineList));

        TemperatureApparent temperatureApparent = new TemperatureApparent();
        boolean triggered = temperatureApparent.isTriggered(timelines);
        assertTrue(triggered, "should be triggered when apparent temperature is lower than 40");
        assertFalse(temperatureApparent.getReportSubject().isBlank());
        assertFalse(temperatureApparent.getReportContent().isBlank());

        LOGGER.info("{}\r\n\r\n{}", temperatureApparent.getReportSubject(), temperatureApparent.getReportContent());
    }

    @Test
    void givenTimelineIntervalWithinThreshold_whenCheckingAlarm_thenAlarmIsTriggered() {
        Values values = new Values()
                .withTemperature(80)
                .withTemperatureApparent(81)
                .withUvIndex(1)
                .withUvHealthConcern(1);

        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval().withStartTime("2022-09-11T08:00:00Z").withValues(values));

        List<Timeline> timelineList = new ArrayList<>();
        timelineList.add(new Timeline().withStartTime("2022-09-11T07:00:00Z").withEndTime("2022-09-11T13:00:00Z")
                .withIntervals(intervals));

        Timelines timelines = new Timelines().withData(new Data().withTimelines(timelineList));

        TemperatureApparent temperatureApparent = new TemperatureApparent();
        boolean triggered = temperatureApparent.isTriggered(timelines);
        assertFalse(triggered, "should NOT be triggered when apparent temperature is within threshold");
        assertTrue(temperatureApparent.getReportSubject().isBlank());
        assertTrue(temperatureApparent.getReportContent().isBlank());
    }

}
