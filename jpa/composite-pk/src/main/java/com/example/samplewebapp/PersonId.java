package com.example.samplewebapp;

import java.io.Serializable;
import java.util.Objects;

public class PersonId implements Serializable {
    String name;
    String surname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonId)) return false;
        PersonId personId = (PersonId) o;
        return Objects.equals(name, personId.name) &&
                Objects.equals(surname, personId.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    public PersonId() {
    }

    public PersonId(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
