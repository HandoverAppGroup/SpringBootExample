package com.example.handoverapp.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Patient {
    private String patientId = "";
    private String location = "";
    private String diagnosis = "";

    public Patient(String id, String location, String diagnosis) {
        this.patientId = id;
        this.location = location;
        this.diagnosis = diagnosis;
    }

    public Patient() {}

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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
}
