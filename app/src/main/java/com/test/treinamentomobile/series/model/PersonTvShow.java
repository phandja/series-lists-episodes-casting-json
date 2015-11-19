package com.test.treinamentomobile.series.model;

/**
 * Created by treinamentomobile on 11/18/15.
 */
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;


@DatabaseTable
public class PersonTvShow {

    public static final String COLUMN_PERSON = "person_id";
    public static final String COLUMN_TVSHOW = "tvshow_id";

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(uniqueCombo = true, foreign = true, foreignAutoRefresh = true, columnName = COLUMN_PERSON)
    private Person person;

    @DatabaseField(uniqueCombo = true, foreign = true, foreignAutoRefresh = true, columnName = COLUMN_TVSHOW)
    private TvShow tvShow;

    public PersonTvShow() {

    }

    public PersonTvShow(Person person, TvShow tvShow) {
        this.person = person;
        this.tvShow = tvShow;
    }

    public static List<Person> toPersonList(List<PersonTvShow> personTvShows) {
        List<Person> list = new ArrayList<Person>(personTvShows.size());
        for (PersonTvShow personTvShow : personTvShows) {
            list.add(personTvShow.getPerson());
        }
        return list;
    }

    public static List<TvShow> toTvShowList(List<PersonTvShow> personTvShows) {
        List<TvShow> list = new ArrayList<TvShow>(personTvShows.size());
        for (PersonTvShow personTvShow : personTvShows) {
            list.add(personTvShow.getTvShow());
        }
        return list;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public TvShow getTvShow() {
        return tvShow;
    }

    public void setTvShow(TvShow tvShow) {
        this.tvShow = tvShow;
    }
}
