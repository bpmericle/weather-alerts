package com.mericle.weather.tomorrow.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Builder
public class Trigger {
    private String startDateRange;
    private String endDateRange;
    private String date;
    private double temperature;
    private double temperatureApparent;
    private int uvIndex;
    private int uvHealthConcern;

    public String getUvIndexDisplay() {
        return getUvDisplay(this.uvIndex);
    }

    public String getUvHealthConcernDisplay() {
        return getUvDisplay(this.uvHealthConcern);
    }

    private String getUvDisplay(int index) {
        if (index <= 2) {
            return "Low";
        } else if (index >= 3 && index <= 5) {
            return "Moderate";
        } else if (index >= 6 && index <= 7) {
            return "High";
        } else if (index >= 8 && index <= 10) {
            return "Very High";
        } else {
            return "Extreme";
        }
    }
}
