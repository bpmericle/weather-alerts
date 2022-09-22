package com.mericle.weather.airnow.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Builder
public class Trigger {
    private int index;
    private String issuedDate;
    private String forecastDate;
    private String parameterName;
    private boolean actionDay;
    private AQI aqi;
}
