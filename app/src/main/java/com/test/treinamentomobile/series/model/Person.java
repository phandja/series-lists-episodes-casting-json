package com.test.treinamentomobile.series.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by treinamentomobile on 11/18/15.
 */
@DatabaseTable
public class Person extends Persona {

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Persona character;

    public Persona getCharacter() {
        return character;
    }

    public void setCharacter(Persona character) {
        this.character = character;
    }
}
