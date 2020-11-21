package com.example.handoverapp.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Creator  {
    private String creatorId;

    public Creator(String creatorId) {
        this.creatorId = creatorId;
    }

    public Creator() {

    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String id) {
        this.creatorId = id;
    }
}
