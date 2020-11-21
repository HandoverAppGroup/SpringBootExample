package com.example.handoverapp.entity;

import javax.persistence.Embeddable;

@Embeddable
public class TaskDetails {
    private String description = "";
    private String outcome = "";
    private String escalationPlan = "";

    public TaskDetails() {}

    public TaskDetails(String description, String outcome, String escalationPlan) {
        this.description = description;
        this.outcome = outcome;
        this.escalationPlan = escalationPlan;
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

}
