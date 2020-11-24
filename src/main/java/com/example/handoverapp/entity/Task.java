package com.example.handoverapp.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private long id;

    private boolean completed = false;
    private Date dateCreated = new Date();
    private Date dateCompleted = null;
    private String description = "";
    private String outcome = "";
    private String escalationPlan = "";

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn
    private Doctor creator;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn
    private Doctor completer;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn
    private Patient patient;


    public Task(boolean completed, Date dateCompleted, String description, String outcome, String escalationPlan) {
        this.completed = completed;
        this.dateCompleted = dateCompleted;
        this.description = description;
        this.outcome = outcome;
        this.escalationPlan = escalationPlan;
    }

    public Task() {
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

    public Doctor getCreator() {
        return creator;
    }

    public void setCreator(Doctor creator) {
        this.creator = creator;
    }

    public Doctor getCompleter() {
        return completer;
    }

    public void setCompleter(Doctor completer) {
        this.completer = completer;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                Objects.equals(dateCreated, task.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCreated);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
