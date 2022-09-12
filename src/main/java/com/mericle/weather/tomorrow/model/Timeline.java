package com.mericle.weather.tomorrow.model;

import java.io.Serializable;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.validation.Valid;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Timeline implements Serializable {

    @SerializedName("timestep")
    @Expose
    private String timestep;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("intervals")
    @Expose
    @Valid
    private List<Interval> intervals = null;
    private final static long serialVersionUID = -1973498760791669049L;

    public String getTimestep() {
        return timestep;
    }

    public void setTimestep(String timestep) {
        this.timestep = timestep;
    }

    public Timeline withTimestep(String timestep) {
        this.timestep = timestep;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Timeline withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Timeline withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public List<Interval> getIntervals() {
        return intervals;
    }

    public void setIntervals(List<Interval> intervals) {
        this.intervals = intervals;
    }

    public Timeline withIntervals(List<Interval> intervals) {
        this.intervals = intervals;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Timeline.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("timestep");
        sb.append('=');
        sb.append(((this.timestep == null) ? "<null>" : this.timestep));
        sb.append(',');
        sb.append("endTime");
        sb.append('=');
        sb.append(((this.endTime == null) ? "<null>" : this.endTime));
        sb.append(',');
        sb.append("startTime");
        sb.append('=');
        sb.append(((this.startTime == null) ? "<null>" : this.startTime));
        sb.append(',');
        sb.append("intervals");
        sb.append('=');
        sb.append(((this.intervals == null) ? "<null>" : this.intervals));
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
        result = ((result * 31) + ((this.intervals == null) ? 0 : this.intervals.hashCode()));
        result = ((result * 31) + ((this.endTime == null) ? 0 : this.endTime.hashCode()));
        result = ((result * 31) + ((this.timestep == null) ? 0 : this.timestep.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Timeline) == false) {
            return false;
        }
        Timeline rhs = ((Timeline) other);
        return (((((this.startTime == rhs.startTime)
                || ((this.startTime != null) && this.startTime.equals(rhs.startTime)))
                && ((this.intervals == rhs.intervals)
                        || ((this.intervals != null) && this.intervals.equals(rhs.intervals))))
                && ((this.endTime == rhs.endTime) || ((this.endTime != null) && this.endTime.equals(rhs.endTime))))
                && ((this.timestep == rhs.timestep)
                        || ((this.timestep != null) && this.timestep.equals(rhs.timestep))));
    }

}