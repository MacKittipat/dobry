package com.abctech.dobry.webapp.service;

import com.abctech.dobry.webapp.json.PullRequest;
import com.abctech.dobry.webapp.model.PullRequestModel;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeCalculatorService {

    private static final Logger log = LoggerFactory.getLogger(TimeCalculatorService.class);

    public String calculateDiffTime(DateTime startDate, DateTime endDate) {

        int totalMinute = Minutes.minutesBetween(startDate, endDate).getMinutes();
        int hour = totalMinute / 60;
        int minute = totalMinute % 60;

        String result = "";
        if(hour > 0) {
            result += hour + "h";
        }
        if(minute > 0) {
            if(hour > 0) {
                result += " ";
            }
            result += minute + "m";
        }

        log.debug("Result = {}", result);
        return result;
    }

    public List<PullRequestModel> calculateDiffPullRequestList(List<PullRequest> pullRequestList) {
        List<PullRequestModel> pullRequestModelList = new ArrayList<>();
        for(PullRequest pullRequest : pullRequestList) {
            DateTime startDate = pullRequest.getCreatedAt();
            DateTime endDate = DateTime.now();
            if(pullRequest.getMergedAt() != null) {
                endDate = pullRequest.getMergedAt();
            } else if(pullRequest.getClosedAt() != null) {
                endDate = pullRequest.getClosedAt();
            }
            PullRequestModel pullRequestModel = new PullRequestModel();
            pullRequestModel.setPullRequest(pullRequest);
            pullRequestModel.setDiffTime(calculateDiffTime(startDate, endDate));
            pullRequestModelList.add(pullRequestModel);
        }
        return pullRequestModelList;
    }
}
