package com.abctech.dobry.webapp.service;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
}
