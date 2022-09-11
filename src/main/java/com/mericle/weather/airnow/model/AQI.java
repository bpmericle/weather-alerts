package com.mericle.weather.airnow.model;

public enum AQI {
    Good(0, 50, 1, "Good", "Green", "00e400"),
    Moderate(51, 100, 2, "Moderate", "Yellow", "ffff00"),
    UnhealthyForSensitiveGroups(101, 150, 3, "Unhealthy for Sensitive Groups", "Orange", "ff7e00"),
    Unhealthy(151, 200, 4, "Unhealthy", "Red", "ff0000"),
    VeryUnhealthy(201, 300, 5, "Very Unhealthy", "Purple", "8f3f97"),
    Hazardous(301, 500, 6, "Hazardous", "Maroon", "7e0023"),
    Unknown;

    public int min;
    public int max;
    public int category;
    public String description;
    public String color;
    public String hexColor;
    public int index;
    public String issuedDate;
    public String forecastDate;
    public String parameterName;
    public boolean actionDay;

    private AQI() {
    }

    private AQI(int min, int max, int category, String description, String color, String hexColor) {
        this.min = min;
        this.max = max;
        this.category = category;
        this.description = description;
        this.color = color;
        this.hexColor = hexColor;
    }

    public static AQI byCategory(int category) {
        for (AQI aqi : values()) {
            if (aqi.category == category) {
                return aqi;
            }
        }
        return Unknown;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AQI.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("min");
        sb.append('=');
        sb.append(this.min);
        sb.append(',');
        sb.append("max");
        sb.append('=');
        sb.append(this.max);
        sb.append(',');
        sb.append("category");
        sb.append('=');
        sb.append(this.category);
        sb.append(',');
        sb.append("AQI");
        sb.append('=');
        sb.append(this.index);
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null) ? "<null>" : this.description));
        sb.append(',');
        sb.append("color");
        sb.append('=');
        sb.append(((this.color == null) ? "<null>" : this.color));
        sb.append(',');
        sb.append("hexColor");
        sb.append('=');
        sb.append(((this.hexColor == null) ? "<null>" : this.hexColor));
        sb.append(',');
        sb.append("index");
        sb.append('=');
        sb.append(this.index);
        sb.append(',');
        sb.append("issuedDate");
        sb.append('=');
        sb.append(((this.issuedDate == null) ? "<null>" : this.issuedDate));
        sb.append(',');
        sb.append("forecastDate");
        sb.append('=');
        sb.append(((this.forecastDate == null) ? "<null>" : this.forecastDate));
        sb.append(',');
        sb.append("parameterName");
        sb.append('=');
        sb.append(((this.parameterName == null) ? "<null>" : this.parameterName));
        sb.append(',');
        sb.append("actionDay");
        sb.append('=');
        sb.append(this.actionDay);
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
