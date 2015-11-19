package com.test.treinamentomobile.series.model;

import android.renderscript.Element;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by treinamentomobile on 11/18/15.
 */

@DatabaseTable
public class TvShow implements Serializable {

    @DatabaseField(id = true)
    private Long id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String summary;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ImageURL image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ImageURL getImage() {
        return image;
    }

    public void setImage(ImageURL image) {
        this.image = image;
    }
}