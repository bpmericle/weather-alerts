package com.mericle.weather.tomorrow.model;

import java.io.Serializable;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.validation.Valid;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Data implements Serializable {

    @SerializedName("timelines")
    @Expose
    @Valid
    private List<Timeline> timelines = null;
    private final static long serialVersionUID = 21520011493574448L;

    public List<Timeline> getTimelines() {
        return timelines;
    }

    public void setTimelines(List<Timeline> timelines) {
        this.timelines = timelines;
    }

    public Data withTimelines(List<Timeline> timelines) {
        this.timelines = timelines;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("timelines");
        sb.append('=');
        sb.append(((this.timelines == null) ? "<null>" : this.timelines));
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
        result = ((result * 31) + ((this.timelines == null) ? 0 : this.timelines.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Data) == false) {
            return false;
        }
        Data rhs = ((Data) other);
        return ((this.timelines == rhs.timelines)
                || ((this.timelines != null) && this.timelines.equals(rhs.timelines)));
    }

}