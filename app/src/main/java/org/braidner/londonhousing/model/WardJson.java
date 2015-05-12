package org.braidner.londonhousing.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by smith / 04.05.2015.
 */
public class WardJson {

    @SerializedName("location")
    private String location;

    @SerializedName("borough")
    private String borough;

    @SerializedName("town")
    private String town;

    @SerializedName("postcode")
    private String postcode;

    @SerializedName("code")
    private String code;

    @SerializedName("os")
    private String os;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
