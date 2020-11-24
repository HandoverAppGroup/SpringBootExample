package com.example.handoverapp.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class Patient {
    @Id
    @GeneratedValue
    private long id;
    private String location = "";
    private String diagnosis = "";
    private String hospitalNumber = "";

    public Patient(String location, String diagnosis, String hospitalNumber) {
        this.location = location;
        this.diagnosis = diagnosis;
        this.hospitalNumber = hospitalNumber;
    }

    public Patient() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getHospitalNumber() {
        return hospitalNumber;
    }

    public void setHospitalNumber(String hospitalNumber) {
        this.hospitalNumber = hospitalNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id &&
                Objects.equals(hospitalNumber, patient.hospitalNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hospitalNumber);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
