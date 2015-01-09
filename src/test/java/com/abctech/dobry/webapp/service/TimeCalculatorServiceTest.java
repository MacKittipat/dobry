package com.abctech.dobry.webapp.service;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class TimeCalculatorServiceTest {

    @Ignore
    @Test
    public void testCalculateDiffTime() {
        TimeCalculatorService timeCalculatorService = new TimeCalculatorService();

        DateTime startDate = new DateTime(2014, 1, 3, 8, 0, 0, 0);
        DateTime endDate = new DateTime(2014, 1, 3, 9, 0, 0, 0);
        Assert.assertEquals("", timeCalculatorService.calculateDiffTime(startDate, endDate));

        startDate = new DateTime(2014, 1, 3, 8, 0, 0, 0);
        endDate = new DateTime(2014, 1, 3, 18, 0, 0, 0);
        Assert.assertEquals("8h", timeCalculatorService.calculateDiffTime(startDate, endDate));

        startDate = new DateTime(2014, 1, 3, 8, 0, 0, 0);
        endDate = new DateTime(2014, 1, 3, 23, 0, 0, 0);
        Assert.assertEquals("8h", timeCalculatorService.calculateDiffTime(startDate, endDate));

        startDate = new DateTime(2014, 1, 3, 8, 25, 0, 0);
        endDate = new DateTime(2014, 1, 3, 17, 5, 0, 0);
        Assert.assertEquals("7h 5m", timeCalculatorService.calculateDiffTime(startDate, endDate));

        startDate = new DateTime(2014, 1, 3, 9, 25, 0, 0);
        endDate = new DateTime(2014, 1, 3, 17, 5, 0, 0);
        Assert.assertEquals("6h 40m", timeCalculatorService.calculateDiffTime(startDate, endDate));

        startDate = new DateTime(2014, 1, 3, 8, 0, 0, 0);
        endDate = new DateTime(2014, 1, 6, 18, 0, 0, 0);
        Assert.assertEquals("16h", timeCalculatorService.calculateDiffTime(startDate, endDate));

        startDate = new DateTime(2014, 1, 3, 8, 0, 0, 0);
        endDate = new DateTime(2014, 1, 8, 18, 0, 0, 0);
        Assert.assertEquals("1d 8h", timeCalculatorService.calculateDiffTime(startDate, endDate));
    }
}