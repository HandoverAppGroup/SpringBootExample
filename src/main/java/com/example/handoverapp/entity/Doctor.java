package com.example.handoverapp.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class Doctor {
    @Id
    @GeneratedValue
    private long id;

    private String identifier = "";

    public Doctor(String identifier) {
        this.identifier = identifier ;
    }

    public Doctor() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id &&
                Objects.equals(identifier, doctor.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identifier);
    }
}
