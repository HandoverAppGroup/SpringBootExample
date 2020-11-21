package com.example.handoverapp.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Completer {
    private String completerId;

    public Completer(String id) {
        this.completerId = id;
    }

    public Completer() {

    }

    public String getCompleterId() {
        return completerId;
    }

    public void setCompleterId(String id) {
        this.completerId = id;
    }
}
