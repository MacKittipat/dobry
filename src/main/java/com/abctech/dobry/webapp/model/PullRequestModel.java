package com.abctech.dobry.webapp.model;

import com.abctech.dobry.webapp.json.PullRequest;

public class PullRequestModel {

    private PullRequest pullRequest;
    private String diffTime;

    public PullRequest getPullRequest() {
        return pullRequest;
    }

    public void setPullRequest(PullRequest pullRequest) {
        this.pullRequest = pullRequest;
    }

    public String getDiffTime() {
        return diffTime;
    }

    public void setDiffTime(String diffTime) {
        this.diffTime = diffTime;
    }
}
