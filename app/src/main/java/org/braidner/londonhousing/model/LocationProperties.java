package org.braidner.londonhousing.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by smith / 02.05.2015.
 */
public class LocationProperties {

    @SerializedName("POLYGON_ID")
    private Float polygonId;

    @SerializedName("AREA_CODE")
    private String areaCode;

    @SerializedName("name")
    private String name;

    @SerializedName("DESCRIPTIO")
    private String description;

    @SerializedName("CODE")
    private String code;

    public Float getPolygonId() {
        return polygonId;
    }

    public void setPolygonId(Float polygonId) {
        this.polygonId = polygonId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
