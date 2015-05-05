package org.braidner.londonhousing.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by smith / 01.05.2015.
 */
public class Geometry {

    @SerializedName("type")
    private String type;

    @SerializedName("coordinates")
    private List<List<List<Float>>> coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<List<List<Float>>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<List<Float>>> coordinates) {
        this.coordinates = coordinates;
    }
}
