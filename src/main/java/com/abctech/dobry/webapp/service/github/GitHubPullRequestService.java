package com.abctech.dobry.webapp.service.github;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GitHubPullRequestService {

    private static final Logger log = LoggerFactory.getLogger(GitHubPullRequestService.class);

    @Autowired
    private RestTemplate restTemplate;

    public List<Object> fetchPullRequests(String authorizationValue) {
        log.debug("Fetching all pull requests");
        return null;
    }

    public Object fetchPullRequest(String authorizationValue, int pullRequestId) {
        log.debug("Fetching pull request. id={}", pullRequestId);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.github.com/repos/amedia/hanuman/pulls/" + pullRequestId,
                HttpMethod.GET,
                createHeaderAuthorization(authorizationValue),
                String.class);

        log.debug(response.getBody());

        return null;
    }

    private HttpEntity<String> createHeaderAuthorization(String base64Auth) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Auth);
        return new HttpEntity<>(headers);
    }
}
