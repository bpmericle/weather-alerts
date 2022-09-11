package com.mericle.weather.airnow.model;

import java.io.Serializable;

import javax.annotation.processing.Generated;
import javax.validation.Valid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Forecast implements Serializable {

    @SerializedName("DateIssue")
    @Expose
    private String dateIssue;
    @SerializedName("DateForecast")
    @Expose
    private String dateForecast;
    @SerializedName("ReportingArea")
    @Expose
    private String reportingArea;
    @SerializedName("StateCode")
    @Expose
    private String stateCode;
    @SerializedName("Latitude")
    @Expose
    private double latitude;
    @SerializedName("Longitude")
    @Expose
    private double longitude;
    @SerializedName("ParameterName")
    @Expose
    private String parameterName;
    @SerializedName("AQI")
    @Expose
    private int aqi;
    @SerializedName("Category")
    @Expose
    @Valid
    private Category category;
    @SerializedName("ActionDay")
    @Expose
    private boolean actionDay;
    @SerializedName("Discussion")
    @Expose
    private String discussion;
    private final static long serialVersionUID = 6227477364173017670L;

    public String getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(String dateIssue) {
        this.dateIssue = dateIssue;
    }

    public Forecast withDateIssue(String dateIssue) {
        this.dateIssue = dateIssue;
        return this;
    }

    public String getDateForecast() {
        return dateForecast;
    }

    public void setDateForecast(String dateForecast) {
        this.dateForecast = dateForecast;
    }

    public Forecast withDateForecast(String dateForecast) {
        this.dateForecast = dateForecast;
        return this;
    }

    public String getReportingArea() {
        return reportingArea;
    }

    public void setReportingArea(String reportingArea) {
        this.reportingArea = reportingArea;
    }

    public Forecast withReportingArea(String reportingArea) {
        this.reportingArea = reportingArea;
        return this;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public Forecast withStateCode(String stateCode) {
        this.stateCode = stateCode;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Forecast withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Forecast withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Forecast withParameterName(String parameterName) {
        this.parameterName = parameterName;
        return this;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public Forecast withAqi(int aqi) {
        this.aqi = aqi;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Forecast withCategory(Category category) {
        this.category = category;
        return this;
    }

    public boolean isActionDay() {
        return actionDay;
    }

    public void setActionDay(boolean actionDay) {
        this.actionDay = actionDay;
    }

    public Forecast withActionDay(boolean actionDay) {
        this.actionDay = actionDay;
        return this;
    }

    public String getDiscussion() {
        return discussion;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }

    public Forecast withDiscussion(String discussion) {
        this.discussion = discussion;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Forecast.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("dateIssue");
        sb.append('=');
        sb.append(((this.dateIssue == null) ? "<null>" : this.dateIssue));
        sb.append(',');
        sb.append("dateForecast");
        sb.append('=');
        sb.append(((this.dateForecast == null) ? "<null>" : this.dateForecast));
        sb.append(',');
        sb.append("reportingArea");
        sb.append('=');
        sb.append(((this.reportingArea == null) ? "<null>" : this.reportingArea));
        sb.append(',');
        sb.append("stateCode");
        sb.append('=');
        sb.append(((this.stateCode == null) ? "<null>" : this.stateCode));
        sb.append(',');
        sb.append("latitude");
        sb.append('=');
        sb.append(this.latitude);
        sb.append(',');
        sb.append("longitude");
        sb.append('=');
        sb.append(this.longitude);
        sb.append(',');
        sb.append("parameterName");
        sb.append('=');
        sb.append(((this.parameterName == null) ? "<null>" : this.parameterName));
        sb.append(',');
        sb.append("aqi");
        sb.append('=');
        sb.append(this.aqi);
        sb.append(',');
        sb.append("category");
        sb.append('=');
        sb.append(((this.category == null) ? "<null>" : this.category));
        sb.append(',');
        sb.append("actionDay");
        sb.append('=');
        sb.append(this.actionDay);
        sb.append(',');
        sb.append("discussion");
        sb.append('=');
        sb.append(((this.discussion == null) ? "<null>" : this.discussion));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.dateForecast == null) ? 0 : this.dateForecast.hashCode()));
        result = ((result * 31)
                + ((int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32))));
        result = ((result * 31) + (this.actionDay ? 1 : 0));
        result = ((result * 31) + this.aqi);
        result = ((result * 31) + ((this.stateCode == null) ? 0 : this.stateCode.hashCode()));
        result = ((result * 31) + ((this.parameterName == null) ? 0 : this.parameterName.hashCode()));
        result = ((result * 31) + ((this.discussion == null) ? 0 : this.discussion.hashCode()));
        result = ((result * 31) + ((this.category == null) ? 0 : this.category.hashCode()));
        result = ((result * 31) + ((this.reportingArea == null) ? 0 : this.reportingArea.hashCode()));
        result = ((result * 31) + ((this.dateIssue == null) ? 0 : this.dateIssue.hashCode()));
        result = ((result * 31)
                + ((int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32))));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Forecast) == false) {
            return false;
        }
        Forecast rhs = ((Forecast) other);
        return ((((((((((((this.dateForecast == rhs.dateForecast)
                || ((this.dateForecast != null) && this.dateForecast.equals(rhs.dateForecast)))
                && (Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(rhs.latitude)))
                && (this.actionDay == rhs.actionDay)) && (this.aqi == rhs.aqi))
                && ((this.stateCode == rhs.stateCode)
                        || ((this.stateCode != null) && this.stateCode.equals(rhs.stateCode))))
                && ((this.parameterName == rhs.parameterName)
                        || ((this.parameterName != null) && this.parameterName.equals(rhs.parameterName))))
                && ((this.discussion == rhs.discussion)
                        || ((this.discussion != null) && this.discussion.equals(rhs.discussion))))
                && ((this.category == rhs.category) || ((this.category != null) && this.category.equals(rhs.category))))
                && ((this.reportingArea == rhs.reportingArea)
                        || ((this.reportingArea != null) && this.reportingArea.equals(rhs.reportingArea))))
                && ((this.dateIssue == rhs.dateIssue)
                        || ((this.dateIssue != null) && this.dateIssue.equals(rhs.dateIssue))))
                && (Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(rhs.longitude)));
    }

}