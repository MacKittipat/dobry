package com.abctech.dobry.webapp.json;

public class PullRequest {

    private long id;
    private long number;
    private String state;
    private String title;
    private String body;
    private String created_at;
    private String updated_at;
    private String closed_at;
    private String merged_at;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(String closed_at) {
        this.closed_at = closed_at;
    }

    public String getMerged_at() {
        return merged_at;
    }

    public void setMerged_at(String merged_at) {
        this.merged_at = merged_at;
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
        sb.append(", created_at='").append(created_at).append('\'');
        sb.append(", updated_at='").append(updated_at).append('\'');
        sb.append(", closed_at='").append(closed_at).append('\'');
        sb.append(", merged_at='").append(merged_at).append('\'');
        sb.append(", user='").append(user.toString()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
