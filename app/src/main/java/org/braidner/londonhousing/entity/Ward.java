package org.braidner.londonhousing.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.util.Collection;
import java.util.List;

/**
 * Created by smith / 12.05.2015.
 */
public class Ward implements Indexed {

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
}
