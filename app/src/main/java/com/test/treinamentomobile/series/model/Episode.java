package com.test.treinamentomobile.series.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by treinamentomobile on 11/18/15.
 */
@DatabaseTable
public class Episode implements Serializable {

    public static final String COLUMN_TVSHOW = "tvshow_id";

    @DatabaseField(foreign = true, columnName = COLUMN_TVSHOW)
    TvShow tvShow;
    @DatabaseField(id = true)
    private Long id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String summary;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ImageURL image;
    @DatabaseField
    private int season;
    @DatabaseField
    private int number;

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

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public TvShow getTvShow() {
        return tvShow;
    }

    public void setTvShow(TvShow tvShow) {
        this.tvShow = tvShow;
    }
}
