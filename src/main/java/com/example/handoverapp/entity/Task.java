package com.example.handoverapp.entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "tasks")
public class Task {

    private long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "creatorId", column = @Column(name = "creator_id")),
    })
    private Creator creator = new Creator();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "completerId", column = @Column(name = "completer_id")),
    })
    private Completer completer = new Completer();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "patientId", column = @Column(name = "patient_id")),
            @AttributeOverride( name = "location", column = @Column(name = "patient_location")),
            @AttributeOverride( name = "diagnosis", column = @Column(name = "patient_diagnosis")),
    })
    private Patient patient = new Patient();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "description", column = @Column(name = "description")),
            @AttributeOverride( name = "outcome", column = @Column(name = "outcome")),
            @AttributeOverride( name = "escalationPlan", column = @Column(name = "escalation_plan"))
    })
    private TaskDetails details = new TaskDetails();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "completed", column = @Column(name = "completed")),
            @AttributeOverride( name = "dateCompleted", column = @Column(name = "date_completed")),
            @AttributeOverride( name = "dateCreated", column = @Column(name = "date_created")),
    })
    private TaskStatus status = new TaskStatus();


    public Task() {}

    public Task(Creator creator, Completer completer, Patient patient, TaskDetails details, TaskStatus status) {
        this.creator = creator;
        this.completer = completer;
        this.patient = patient;
        this.details = details;
        this.status = status;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }



    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String created = dateFormat.format(status.getDateCreated());
        String completed = "";
        if (status.getCompleted()) {
            completed = dateFormat.format(status.getDateCompleted());
        } else {
            completed = "Not completed yet";
        }
        return "Task created: "+created+" completed: "+completed;
    }


    public TaskDetails getDetails() {
        return details;
    }

    public void setDetails(TaskDetails details) {
        this.details = details;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Completer getCompleter() {
        return completer;
    }

    public void setCompleter(Completer completer) {
        this.completer = completer;
    }
}
