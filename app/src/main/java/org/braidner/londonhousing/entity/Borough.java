package org.braidner.londonhousing.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by smith / 12.05.2015.
 */
public class Borough implements Indexed, Serializable {

    public static final String NAME = "name";
    public static final String CODE = "code";
    public static final String UNIT_ID = "unit_id";
    public static final String MAP_IT_ID = "map_it_id";

    @DatabaseField(generatedId = true, columnName = COLUMN_NAME_ID)
    private Integer id;

    @DatabaseField(columnName = MAP_IT_ID)
    private Integer mapItId;

    @DatabaseField(columnName = NAME)
    private String name;

    @DatabaseField(columnName = CODE)
    private String code;

    @DatabaseField(columnName = UNIT_ID)
    private String unitId;

    @ForeignCollectionField(eager = true)
    private Collection<Point> polygon;

    @ForeignCollectionField(eager = true)
    private Collection<Ward> wards;

    public Collection<Ward> getWards() {
        return wards;
    }

    public void setWards(Collection<Ward> wards) {
        this.wards = wards;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Collection<Point> getPolygon() {
        if (polygon == null) polygon = new ArrayList<>();
        return polygon;
    }

    public void setPolygon(Collection<Point> polygon) {
        this.polygon = polygon;
    }

    public Integer getMapItId() {
        return mapItId;
    }

    public void setMapItId(Integer mapItId) {
        this.mapItId = mapItId;
    }
}
