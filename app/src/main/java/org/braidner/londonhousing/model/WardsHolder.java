package org.braidner.londonhousing.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by smith / 04.05.2015.
 */
public class WardsHolder {

    @SerializedName("data")
    private List<Ward> wards;

    public List<Ward> getWards() {
        return wards;
    }

    public void setWards(List<Ward> wards) {
        this.wards = wards;
    }
}
