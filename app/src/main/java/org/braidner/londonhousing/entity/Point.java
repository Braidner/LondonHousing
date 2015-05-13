package org.braidner.londonhousing.entity;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by smith / 12.05.2015.
 */
public class Point implements Indexed, Serializable {

    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String ORDER = "order";

    @DatabaseField(generatedId = true, columnName = COLUMN_NAME_ID)
    private Integer id;

    @DatabaseField(columnName = LATITUDE)
    private Float latitude;

    @DatabaseField(columnName = LONGITUDE)
    private Float longitude;

    @DatabaseField(columnName = ORDER)
    private Integer order;

    @DatabaseField(foreign = true)
    private Borough borough;

    public Point() {
    }

    public Point(Float latitude, Float longitude, Integer order, Borough borough) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.order = order;
        this.borough = borough;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Borough getBorough() {
        return borough;
    }

    public void setBorough(Borough borough) {
        this.borough = borough;
    }
}
