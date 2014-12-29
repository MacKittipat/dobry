package com.abctech.dobry.webapp.model;

import java.util.List;

public class PullRequestPaginationModel {

    private List<PullRequestModel> pullRequestModelList;
    private Integer previousPage;
    private Integer currentPage;
    private Integer nextPage;

    public List<PullRequestModel> getPullRequestModelList() {
        return pullRequestModelList;
    }

    public void setPullRequestModelList(List<PullRequestModel> pullRequestModelList) {
        this.pullRequestModelList = pullRequestModelList;
    }

    public Integer getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(Integer previousPage) {
        this.previousPage = previousPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }
}
