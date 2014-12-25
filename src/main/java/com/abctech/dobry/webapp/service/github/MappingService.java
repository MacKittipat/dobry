package com.abctech.dobry.webapp.service.github;

import com.abctech.dobry.webapp.json.PullRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MappingService {

    private static final Logger log = LoggerFactory.getLogger(MappingService.class);

    @Autowired
    private ObjectMapper objectMapper;

    private void initObjectMapper() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JodaModule());
    }

    public PullRequest mapJsonToPullRequestObject(String json) {
        log.debug("Mapping JSON to PullRequest object");

        PullRequest pullRequest = null;
        try {
            initObjectMapper();
            pullRequest = objectMapper.readValue(json, PullRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pullRequest;
    }

    public List<PullRequest> mapJsonToPullRequestList(String json) {
        log.debug("Mapping JSON to PullRequest list");

        List<PullRequest> pullRequestList = null;
        try {
            initObjectMapper();
            pullRequestList = objectMapper.readValue(json,
                    TypeFactory.defaultInstance().constructCollectionType(List.class, PullRequest.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pullRequestList;
    }
}
