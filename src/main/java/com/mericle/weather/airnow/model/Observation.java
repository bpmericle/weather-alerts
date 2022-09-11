package com.mericle.weather.airnow.model;

import java.io.Serializable;

import javax.annotation.processing.Generated;
import javax.validation.Valid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Observation implements Serializable {

    @SerializedName("DateObserved")
    @Expose
    private String dateObserved;
    @SerializedName("HourObserved")
    @Expose
    private int hourObserved;
    @SerializedName("LocalTimeZone")
    @Expose
    private String localTimeZone;
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
    private final static long serialVersionUID = -7812530047572936938L;

    public String getDateObserved() {
        return dateObserved;
    }

    public void setDateObserved(String dateObserved) {
        this.dateObserved = dateObserved;
    }

    public Observation withDateObserved(String dateObserved) {
        this.dateObserved = dateObserved;
        return this;
    }

    public int getHourObserved() {
        return hourObserved;
    }

    public void setHourObserved(int hourObserved) {
        this.hourObserved = hourObserved;
    }

    public Observation withHourObserved(int hourObserved) {
        this.hourObserved = hourObserved;
        return this;
    }

    public String getLocalTimeZone() {
        return localTimeZone;
    }

    public void setLocalTimeZone(String localTimeZone) {
        this.localTimeZone = localTimeZone;
    }

    public Observation withLocalTimeZone(String localTimeZone) {
        this.localTimeZone = localTimeZone;
        return this;
    }

    public String getReportingArea() {
        return reportingArea;
    }

    public void setReportingArea(String reportingArea) {
        this.reportingArea = reportingArea;
    }

    public Observation withReportingArea(String reportingArea) {
        this.reportingArea = reportingArea;
        return this;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public Observation withStateCode(String stateCode) {
        this.stateCode = stateCode;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Observation withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Observation withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Observation withParameterName(String parameterName) {
        this.parameterName = parameterName;
        return this;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public Observation withAqi(int aqi) {
        this.aqi = aqi;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Observation withCategory(Category category) {
        this.category = category;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Observation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("dateObserved");
        sb.append('=');
        sb.append(((this.dateObserved == null) ? "<null>" : this.dateObserved));
        sb.append(',');
        sb.append("hourObserved");
        sb.append('=');
        sb.append(this.hourObserved);
        sb.append(',');
        sb.append("localTimeZone");
        sb.append('=');
        sb.append(((this.localTimeZone == null) ? "<null>" : this.localTimeZone));
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
        result = ((result * 31) + ((this.dateObserved == null) ? 0 : this.dateObserved.hashCode()));
        result = ((result * 31)
                + ((int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32))));
        result = ((result * 31) + ((this.localTimeZone == null) ? 0 : this.localTimeZone.hashCode()));
        result = ((result * 31) + this.aqi);
        result = ((result * 31) + ((this.stateCode == null) ? 0 : this.stateCode.hashCode()));
        result = ((result * 31) + ((this.parameterName == null) ? 0 : this.parameterName.hashCode()));
        result = ((result * 31) + this.hourObserved);
        result = ((result * 31) + ((this.category == null) ? 0 : this.category.hashCode()));
        result = ((result * 31) + ((this.reportingArea == null) ? 0 : this.reportingArea.hashCode()));
        result = ((result * 31)
                + ((int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32))));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Observation) == false) {
            return false;
        }
        Observation rhs = ((Observation) other);
        return (((((((((((this.dateObserved == rhs.dateObserved)
                || ((this.dateObserved != null) && this.dateObserved.equals(rhs.dateObserved)))
                && (Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(rhs.latitude)))
                && ((this.localTimeZone == rhs.localTimeZone)
                        || ((this.localTimeZone != null) && this.localTimeZone.equals(rhs.localTimeZone))))
                && (this.aqi == rhs.aqi))
                && ((this.stateCode == rhs.stateCode)
                        || ((this.stateCode != null) && this.stateCode.equals(rhs.stateCode))))
                && ((this.parameterName == rhs.parameterName)
                        || ((this.parameterName != null) && this.parameterName.equals(rhs.parameterName))))
                && (this.hourObserved == rhs.hourObserved))
                && ((this.category == rhs.category) || ((this.category != null) && this.category.equals(rhs.category))))
                && ((this.reportingArea == rhs.reportingArea)
                        || ((this.reportingArea != null) && this.reportingArea.equals(rhs.reportingArea))))
                && (Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(rhs.longitude)));
    }

}