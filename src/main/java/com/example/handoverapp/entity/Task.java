package com.example.handoverapp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private boolean completed = false;
    @Column(nullable = false)
    private Date dateCreated = new Date();
    @Column
    private Date dateCompleted = null;
    @Column
    private String description = "";
    @Column
    private String outcome = "";
    @Column
    private String escalationPlan = "";
    @Column
    private String patientLocation = "";
    @Column
    private String patientDiagnosis = "";
    @Column
    private String completer = "";
    @Column
    private String creator = "";

    public Task(boolean completed, Date dateCreated, Date dateCompleted, String description, String outcome, String escalationPlan, String patientLocation, String patientDiagnosis, String completer, String creator) {
        this.completed = completed;
        this.dateCreated = dateCreated;
        this.dateCompleted = dateCompleted;
        this.description = description;
        this.outcome = outcome;
        this.escalationPlan = escalationPlan;
        this.patientLocation = patientLocation;
        this.patientDiagnosis = patientDiagnosis;
        this.completer = completer;
        this.creator = creator;
    }

    public Task(String description, String outcome, String escalationPlan, String patientLocation, String patientDiagnosis, String creator) {
        this.description = description;
        this.outcome = outcome;
        this.escalationPlan = escalationPlan;
        this.patientLocation = patientLocation;
        this.patientDiagnosis = patientDiagnosis;
        this.creator = creator;
    }

    public Task() {}

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    public String getCompleter() {
        return completer;
    }

    public void setCompleter(String completer) {
        this.completer = completer;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
