package com.mericle.weather.tomorrow.model;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Values implements Serializable {

    @SerializedName("temperature")
    @Expose
    private double temperature;
    @SerializedName("temperatureApparent")
    @Expose
    private double temperatureApparent;
    @SerializedName("uvHealthConcern")
    @Expose
    private int uvHealthConcern;
    @SerializedName("uvIndex")
    @Expose
    private int uvIndex;
    private final static long serialVersionUID = 4575562198725566578L;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Values withTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    public double getTemperatureApparent() {
        return temperatureApparent;
    }

    public void setTemperatureApparent(double temperatureApparent) {
        this.temperatureApparent = temperatureApparent;
    }

    public Values withTemperatureApparent(double temperatureApparent) {
        this.temperatureApparent = temperatureApparent;
        return this;
    }

    public int getUvHealthConcern() {
        return uvHealthConcern;
    }

    public void setUvHealthConcern(int uvHealthConcern) {
        this.uvHealthConcern = uvHealthConcern;
    }

    public Values withUvHealthConcern(int uvHealthConcern) {
        this.uvHealthConcern = uvHealthConcern;
        return this;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(int uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Values withUvIndex(int uvIndex) {
        this.uvIndex = uvIndex;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Values.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("temperature");
        sb.append('=');
        sb.append(this.temperature);
        sb.append(',');
        sb.append("temperatureApparent");
        sb.append('=');
        sb.append(this.temperatureApparent);
        sb.append(',');
        sb.append("uvHealthConcern");
        sb.append('=');
        sb.append(this.uvHealthConcern);
        sb.append(',');
        sb.append("uvIndex");
        sb.append('=');
        sb.append(this.uvIndex);
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
        result = ((result * 31) + ((int) (Double.doubleToLongBits(this.temperature)
                ^ (Double.doubleToLongBits(this.temperature) >>> 32))));
        result = ((result * 31) + this.uvHealthConcern);
        result = ((result * 31) + this.uvIndex);
        result = ((result * 31) + ((int) (Double.doubleToLongBits(this.temperatureApparent)
                ^ (Double.doubleToLongBits(this.temperatureApparent) >>> 32))));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Values) == false) {
            return false;
        }
        Values rhs = ((Values) other);
        return ((((Double.doubleToLongBits(this.temperature) == Double.doubleToLongBits(rhs.temperature))
                && (this.uvHealthConcern == rhs.uvHealthConcern)) && (this.uvIndex == rhs.uvIndex))
                && (Double.doubleToLongBits(this.temperatureApparent) == Double
                        .doubleToLongBits(rhs.temperatureApparent)));
    }

}