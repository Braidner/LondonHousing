package org.braidner.londonhousing.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by smith / 12.05.2015.
 */
public class Code {

    @SerializedName("ons")
    private String ons;

    @SerializedName("gss")
    private String gss;

    @SerializedName("unit_id")
    private String unitId;

    public String getOns() {
        return ons;
    }

    public void setOns(String ons) {
        this.ons = ons;
    }

    public String getGss() {
        return gss;
    }

    public void setGss(String gss) {
        this.gss = gss;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
}
