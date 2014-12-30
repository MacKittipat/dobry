package com.abctech.dobry.webapp.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payload {

    /* For CommitCommentEvent & IssueCommentEvent & PullRequestReviewCommentEvent */
    private Comment comment;

    /* For PullRequestEvent & PullRequestReviewCommentEvent */
    @JsonProperty(value = "pull_request")
    private PullRequest pullRequest;

    /* For IssueCommentEvent */
    private PullRequest issue;

    /* For PushEvent */
//    private Commits commits;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public PullRequest getPullRequest() {
        return pullRequest;
    }

    public void setPullRequest(PullRequest pullRequest) {
        this.pullRequest = pullRequest;
    }

    public PullRequest getIssue() {
        return issue;
    }

    public void setIssue(PullRequest issue) {
        this.issue = issue;
    }

//    public Commits getCommits() {
//        return commits;
//    }
//
//    public void setCommits(Commits commits) {
//        this.commits = commits;
//    }
}
