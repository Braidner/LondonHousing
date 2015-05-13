package org.braidner.londonhousing.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by smith / 12.05.2015.
 */
public class Ward implements Indexed, Serializable {

    public static final String NAME = "name";
    public static final String CODE = "code";

    @DatabaseField(generatedId = true, columnName = COLUMN_NAME_ID)
    private Integer id;

    @DatabaseField(columnName = NAME)
    private String name;

    @DatabaseField(columnName = CODE)
    private String code;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Borough borough;

    @DatabaseField
    private String lat;

    @DatabaseField
    private String lon;

    @DatabaseField
    private String MapItId;

    @ForeignCollectionField(eager = true)
    private Collection<WardProperty> properties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Collection<WardProperty> getProperties() {
        return properties;
    }

    public void setProperties(Collection<WardProperty> properties) {
        this.properties = properties;
    }

    public Borough getBorough() {
        return borough;
    }

    public void setBorough(Borough borough) {
        this.borough = borough;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getMapItId() {
        return MapItId;
    }

    public void setMapItId(String mapItId) {
        MapItId = mapItId;
    }
}
