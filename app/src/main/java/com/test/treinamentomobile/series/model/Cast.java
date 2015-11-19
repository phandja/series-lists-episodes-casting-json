package com.test.treinamentomobile.series.model;

import java.io.Serializable;

/**
 * Created by treinamentomobile on 11/18/15.
 */
public class Cast implements Serializable {

    private Person person;

    private Persona character;

    public Person getPerson() {
        person.setCharacter(character);
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
