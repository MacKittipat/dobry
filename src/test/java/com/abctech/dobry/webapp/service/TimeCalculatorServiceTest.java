package com.abctech.dobry.webapp.service;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class TimeCalculatorServiceTest {

    @Test
    public void testCalculateDiffTime() throws Exception {
        DateTime now = DateTime.now();
        TimeCalculatorService timeCalculatorService = new TimeCalculatorService();

        DateTime startDate = now;
        DateTime endDate = now.plusHours(1);
        Assert.assertEquals("1h", timeCalculatorService.calculateDiffTime(startDate, endDate));

        startDate = now;
        endDate = now.plusHours(1).plusMinutes(1);
        Assert.assertEquals("1h 1m", timeCalculatorService.calculateDiffTime(startDate, endDate));

        startDate = now;
        endDate = now.plusHours(1).plusMinutes(1).plusSeconds(1);
        Assert.assertEquals("1h 1m", timeCalculatorService.calculateDiffTime(startDate, endDate));

        startDate = now;
        endDate = now.plusHours(1).plusMinutes(1).plusSeconds(60);
        Assert.assertEquals("1h 2m", timeCalculatorService.calculateDiffTime(startDate, endDate));

        startDate = now;
        endDate = now.plusDays(3).plusHours(1).plusMinutes(1).plusSeconds(60);
        Assert.assertEquals("3d 1h 2m", timeCalculatorService.calculateDiffTime(startDate, endDate));

        startDate = now;
        endDate = now.plusDays(3).plusHours(25).plusMinutes(59).plusSeconds(60);
        Assert.assertEquals("4d 2h", timeCalculatorService.calculateDiffTime(startDate, endDate));

        startDate = now;
        endDate = now.plusDays(3).plusHours(25).plusMinutes(59).plusSeconds(33);
        Assert.assertEquals("4d 1h 59m", timeCalculatorService.calculateDiffTime(startDate, endDate));
    }
}