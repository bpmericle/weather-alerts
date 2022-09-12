package com.mericle.weather.tomorrow.model;

import java.io.Serializable;
import javax.annotation.processing.Generated;
import javax.validation.Valid;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Timelines implements Serializable {

    @SerializedName("data")
    @Expose
    @Valid
    private Data data;
    private final static long serialVersionUID = -8673212947596719330L;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Timelines withData(Data data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Timelines.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null) ? "<null>" : this.data));
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
        result = ((result * 31) + ((this.data == null) ? 0 : this.data.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Timelines) == false) {
            return false;
        }
        Timelines rhs = ((Timelines) other);
        return ((this.data == rhs.data) || ((this.data != null) && this.data.equals(rhs.data)));
    }

}