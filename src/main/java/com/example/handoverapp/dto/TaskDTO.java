package com.example.handoverapp.dto;

import java.util.Date;

public class TaskDTO {

    private boolean completed;
    private Date dateCompleted;
    private String description;
    private String outcome;
    private String escalationPlan;
    private String creatorIdentifier;
    private String completerIdentifier;
    private String patientHospitalNum;
    private String patientLocation;
    private String patientDiagnosis;


    public TaskDTO(boolean completed, Date dateCompleted, String description, String outcome, String escalationPlan, String creatorIdentifier, String completerIdentifier, String patientHospitalNum, String patientLocation, String patientDiagnosis) {
        this.completed = completed;
        this.dateCompleted = dateCompleted;
        this.description = description;
        this.outcome = outcome;
        this.escalationPlan = escalationPlan;
        this.creatorIdentifier = creatorIdentifier;
        this.completerIdentifier = completerIdentifier;
        this.patientHospitalNum = patientHospitalNum;
        this.patientLocation = patientLocation;
        this.patientDiagnosis = patientDiagnosis;
    }

    public TaskDTO() {
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getEscalationPlan() {
        return escalationPlan;
    }

    public void setEscalationPlan(String escalationPlan) {
        this.escalationPlan = escalationPlan;
    }

    public String getCreatorIdentifier() {
        return creatorIdentifier;
    }

    public void setCreatorIdentifier(String creatorIdentifier) {
        this.creatorIdentifier = creatorIdentifier;
    }

    public String getCompleterIdentifier() {
        return completerIdentifier;
    }

    public void setCompleterIdentifier(String completerIdentifier) {
        this.completerIdentifier = completerIdentifier;
    }

    public String getPatientHospitalNum() {
        return patientHospitalNum;
    }

    public void setPatientHospitalNum(String patientHospitalNum) {
        this.patientHospitalNum = patientHospitalNum;
    }

    public String getPatientLocation() {
        return patientLocation;
    }

    public void setPatientLocation(String patientLocation) {
        this.patientLocation = patientLocation;
    }

    public String getPatientDiagnosis() {
        return patientDiagnosis;
    }

    public void setPatientDiagnosis(String patientDiagnosis) {
        this.patientDiagnosis = patientDiagnosis;
    }
}
