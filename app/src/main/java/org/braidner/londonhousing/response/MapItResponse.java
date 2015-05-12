package org.braidner.londonhousing.response;

import com.google.gson.annotations.SerializedName;

import org.braidner.londonhousing.model.Code;

/**
 * Created by smith / 12.05.2015.
 */
public class MapItResponse {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("codes")
    private Code codes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Code getCodes() {
        return codes;
    }

    public void setCodes(Code codes) {
        this.codes = codes;
    }
}
