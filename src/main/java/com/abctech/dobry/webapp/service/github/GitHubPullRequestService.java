package com.abctech.dobry.webapp.service.github;

import com.abctech.dobry.webapp.json.PullRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class GitHubPullRequestService {

    private static final Logger log = LoggerFactory.getLogger(GitHubPullRequestService.class);

    @Autowired
    private RestTemplate restTemplate;

    public List<PullRequest> fetchPullRequests(String accessToken, String repository) {
        log.debug("Fetching all pull requests");
        ResponseEntity<PullRequest[]> response = restTemplate.exchange(
                "https://api.github.com/repos/amedia/" + repository + "/pulls",
                HttpMethod.GET,
                createHeaderAuthorization(accessToken),
                PullRequest[].class);
        return Arrays.asList(response.getBody());
    }

    public PullRequest fetchPullRequest(String accessToken, int pullRequestId) {
        log.debug("Fetching pull request. id={}", pullRequestId);
        ResponseEntity<PullRequest> response = restTemplate.exchange(
                "https://api.github.com/repos/amedia/hanuman/pulls/" + pullRequestId,
                HttpMethod.GET,
                createHeaderAuthorization(accessToken),
                PullRequest.class);
        return response.getBody();
    }

    private HttpEntity<String> createHeaderAuthorization(String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "token " + value);
        return new HttpEntity<>(headers);
    }
}
