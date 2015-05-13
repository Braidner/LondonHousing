package org.braidner.londonhousing.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by smith / 13.05.2015.
 */
public class GeometryResponse {

    @SerializedName("centre_lat")
    private String lat;

    @SerializedName("centre_lon")
    private String lon;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
