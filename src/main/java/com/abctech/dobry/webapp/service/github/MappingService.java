package com.abctech.dobry.webapp.service.github;

import com.abctech.dobry.webapp.json.PullRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MappingService {

    private static final Logger log = LoggerFactory.getLogger(MappingService.class);

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    public PullRequest mapJsonToPullRequestObj(String json) {
        log.debug("Mapping JSON to PullRequest object");

        PullRequest pullRequest = null;
        try {
            jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            jacksonObjectMapper.registerModule(new JodaModule());
            pullRequest = jacksonObjectMapper.readValue(json, PullRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pullRequest;
    }
}
