package org.braidner.londonhousing.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by smith / 04.05.2015.
 */
public class WardsHolder {

    @SerializedName("data")
    private List<WardJson> wardJsons;

    public List<WardJson> getWardJsons() {
        return wardJsons;
    }

    public void setWardJsons(List<WardJson> wardJsons) {
        this.wardJsons = wardJsons;
    }
}
