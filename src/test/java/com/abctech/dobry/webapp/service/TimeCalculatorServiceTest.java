package com.abctech.dobry.webapp.service;

import com.abctech.dobry.App;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class TimeCalculatorServiceTest {

    @Autowired
    private TimeCalculatorService timeCalculatorService;

    @Test
    public void testCalculateDiffTime() throws Exception {
        DateTime now = DateTime.now();
        timeCalculatorService.calculateDiffTime(now, now.plusHours(3).plusMinutes(99));
    }
}