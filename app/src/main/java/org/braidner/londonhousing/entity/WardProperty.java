package org.braidner.londonhousing.entity;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by smith / 12.05.2015.
 */
public class WardProperty implements Indexed {

    public static final String PROPERTY_NAME = "property_name";
    public static final String PROPERTY_RATING = "property_rating";

    @DatabaseField(generatedId = true, columnName = COLUMN_NAME_ID)
    private Integer id;

    @DatabaseField(columnName = PROPERTY_NAME)
    private String propertyName;

    @DatabaseField(columnName = PROPERTY_RATING)
    private Float propertyRating;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Ward ward;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Float getPropertyRating() {
        return propertyRating;
    }

    public void setPropertyRating(Float propertyRating) {
        this.propertyRating = propertyRating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }
}
