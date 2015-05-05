package org.braidner.londonhousing.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by smith / 01.05.2015.
 */
public class Location {

    @SerializedName("type")
    private String type;

    @SerializedName("geometry")
    private Geometry geometry;

    @SerializedName("properties")
    private LocationProperties properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public LocationProperties getProperties() {
        return properties;
    }

    public void setProperties(LocationProperties properties) {
        this.properties = properties;
    }
}
