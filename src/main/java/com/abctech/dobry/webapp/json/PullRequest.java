package com.abctech.dobry.webapp.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

public class PullRequest {

    private long id;
    private long number;
    private String state;
    private String title;
    private String body;

    @JsonProperty(value = "created_at")
    private DateTime createdAt;

    @JsonProperty(value = "updated_at")
    private DateTime updatedAt;

    @JsonProperty(value = "closed_at")
    private DateTime closedAt;

    @JsonProperty(value = "merged_at")
    private DateTime mergedAt;

    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public DateTime getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(DateTime closedAt) {
        this.closedAt = closedAt;
    }

    public DateTime getMergedAt() {
        return mergedAt;
    }

    public void setMergedAt(DateTime mergedAt) {
        this.mergedAt = mergedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PullRequest{");
        sb.append("id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", state='").append(state).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", closedAt=").append(closedAt);
        sb.append(", mergedAt=").append(mergedAt);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
