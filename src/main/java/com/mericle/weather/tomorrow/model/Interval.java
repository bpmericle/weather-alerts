package com.mericle.weather.tomorrow.model;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import javax.validation.Valid;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Interval implements Serializable {

    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("values")
    @Expose
    @Valid
    private Values values;
    private final static long serialVersionUID = -6798607641468412517L;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Interval withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public Values getValues() {
        return values;
    }

    public void setValues(Values values) {
        this.values = values;
    }

    public Interval withValues(Values values) {
        this.values = values;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Interval.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("startTime");
        sb.append('=');
        sb.append(((this.startTime == null) ? "<null>" : this.startTime));
        sb.append(',');
        sb.append("values");
        sb.append('=');
        sb.append(((this.values == null) ? "<null>" : this.values));
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
        result = ((result * 31) + ((this.startTime == null) ? 0 : this.startTime.hashCode()));
        result = ((result * 31) + ((this.values == null) ? 0 : this.values.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Interval) == false) {
            return false;
        }
        Interval rhs = ((Interval) other);
        return (((this.startTime == rhs.startTime)
                || ((this.startTime != null) && this.startTime.equals(rhs.startTime)))
                && ((this.values == rhs.values) || ((this.values != null) && this.values.equals(rhs.values))));
    }

}