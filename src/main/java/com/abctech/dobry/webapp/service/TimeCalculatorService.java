package com.abctech.dobry.webapp.service;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TimeCalculatorService {

    private static final Logger log = LoggerFactory.getLogger(TimeCalculatorService.class);

    public String calculateDiffTime(DateTime startDate, DateTime endDate) {
        int totalSecs = Seconds.secondsBetween(startDate, endDate).getSeconds();

        int hours = totalSecs / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;

        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        log.debug("sec = {}", timeString);

        return "";
    }
}
