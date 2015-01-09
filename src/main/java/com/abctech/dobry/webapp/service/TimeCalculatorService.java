package com.abctech.dobry.webapp.service;

import com.abctech.dobry.webapp.json.PullRequest;
import com.abctech.dobry.webapp.model.PullRequestModel;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeCalculatorService {

    private static final Logger log = LoggerFactory.getLogger(TimeCalculatorService.class);

    /**
     * This method ignore time during weekend and lunch break.
     */
    public String calculateDiffTime(DateTime startDate, DateTime endDate) {

        int countMinute = 0;
        DateTime currentTime = startDate;
        while(currentTime.isBefore(endDate)) {
            currentTime = currentTime.plusMinutes(1);
            if(currentTime.getDayOfWeek() != DateTimeConstants.SATURDAY
                    && currentTime.getDayOfWeek() != DateTimeConstants.SUNDAY) {

                // 9.00 - 12.30
                DateTime morningStart = new DateTime(
                        currentTime.getYear(),
                        currentTime.getMonthOfYear(),
                        currentTime.getDayOfMonth(),
                        9, 1, 0);
                DateTime morningEnd = new DateTime(
                        currentTime.getYear(),
                        currentTime.getMonthOfYear(),
                        currentTime.getDayOfMonth(),
                        12, 31, 0);
                Interval morningInterval = new Interval(morningStart, morningEnd);

                // 13.30 - 18.00
                DateTime afternoonStart = new DateTime(
                        currentTime.getYear(),
                        currentTime.getMonthOfYear(),
                        currentTime.getDayOfMonth(),
                        13, 31, 0);
                DateTime afternoonEnd = new DateTime(
                        currentTime.getYear(),
                        currentTime.getMonthOfYear(),
                        currentTime.getDayOfMonth(),
                        18, 1, 0);
                Interval afternoonInterval = new Interval(afternoonStart, afternoonEnd);

                if(morningInterval.contains(currentTime) || afternoonInterval.contains(currentTime)) {
//                    log.debug("currentTime = {}", currentTime.toString());
                    countMinute++;
                }
            }
        }
        log.debug("countMinute = {}", countMinute);

        int totalMinute = countMinute;
        int day = totalMinute / 1440;
        int hour = (totalMinute % 1440) / 60;
        int minute = totalMinute % 60;

        String result = "";
        if(day > 0) {
            result += day + "d";
        }
        if(hour > 0) {
            if(day > 0) {
                result += " ";
            }
            result += hour + "h";
        }
        if(minute > 0) {
            if(day > 0 || hour > 0) {
                result += " ";
            }
            result += minute + "m";
        }

        log.debug("Result = {}", result);
        return result;
    }

    public List<PullRequestModel> calculateDiffPullRequestList(List<PullRequest> pullRequestList) {
        List<PullRequestModel> pullRequestModelList = new ArrayList<>();
        String timeZoneId = "Asia/Bangkok";
        for(PullRequest pullRequest : pullRequestList) {
            DateTime startDate = pullRequest.getCreatedAt().withZone(DateTimeZone.forID(timeZoneId));
            DateTime endDate = DateTime.now();
            if(pullRequest.getMergedAt() != null) {
                endDate = pullRequest.getMergedAt().withZone(DateTimeZone.forID(timeZoneId));
            } else if(pullRequest.getClosedAt() != null) {
                endDate = pullRequest.getClosedAt().withZone(DateTimeZone.forID(timeZoneId));
            }
            PullRequestModel pullRequestModel = new PullRequestModel();
            pullRequestModel.setPullRequest(pullRequest);
            pullRequestModel.setDiffTime(calculateDiffTime(startDate, endDate));
            String dateTimePattern = "dd/MM/yyyy HH:mm";
            pullRequestModel.setStartDate(startDate.toString(dateTimePattern));
            pullRequestModel.setEndDate(endDate.toString(dateTimePattern));
            pullRequestModelList.add(pullRequestModel);
        }
        return pullRequestModelList;
    }
}
