package com.abctech.dobry.webapp.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

public class Event {

    private long id;
    private String type;
    private User actor;

    @JsonProperty(value = "created_at")
    private DateTime createdAt;

    private Payload payload;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getActor() {
        return actor;
    }

    public void setActor(User actor) {
        this.actor = actor;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Event{");
        sb.append("id=").append(id);
        sb.append(", type='").append(type).append('\'');
        sb.append(", actor=").append(actor);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", payload=").append(payload);
        sb.append('}');
        return sb.toString();
    }
}
