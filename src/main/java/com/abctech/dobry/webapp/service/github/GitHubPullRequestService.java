package com.abctech.dobry.webapp.service.github;

import com.abctech.dobry.form.GitHubPullRequestForm;
import com.abctech.dobry.webapp.json.PullRequest;
import com.abctech.dobry.webapp.model.PullRequestModel;
import com.abctech.dobry.webapp.model.PullRequestPaginationModel;
import com.abctech.dobry.webapp.service.TimeCalculatorService;
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

    @Autowired
    private TimeCalculatorService timeCalculatorService;

    public PullRequestPaginationModel fetchPullRequests(String accessToken,
                                                        GitHubPullRequestForm gitHubPullRequestForm) {

        PullRequestPaginationModel pullRequestPaginationModel = new PullRequestPaginationModel();

        log.debug("Fetching all pull requests");
        ResponseEntity<PullRequest[]> response = restTemplate.exchange(
                "https://api.github.com/repos/amedia/" + gitHubPullRequestForm.getRepo() +
                        "/pulls?state=" + gitHubPullRequestForm.getState() +
                        "&page=" + gitHubPullRequestForm.getPage(),
                HttpMethod.GET,
                createHeaderAuthorization(accessToken),
                PullRequest[].class);

        List<PullRequest> pullRequestList = Arrays.asList(response.getBody());
        List<PullRequestModel> pullRequestModelList =
                timeCalculatorService.calculateDiffPullRequestList(pullRequestList);
        pullRequestPaginationModel.setPullRequestModelList(pullRequestModelList);
        updatePagination(response.getHeaders(), pullRequestPaginationModel);

        return pullRequestPaginationModel;
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

    private void updatePagination(HttpHeaders httpHeaders,
                                  PullRequestPaginationModel pullRequestPaginationModel) {
        String linkHeader = httpHeaders.getFirst("Link");
        log.debug("Link header = {}", linkHeader);
        if(linkHeader == null) {
            return;
        }
        String[] links = linkHeader.split(",");
        for(String link : links) {
            if(link.contains("prev")) {
                pullRequestPaginationModel.setPreviousPage(findPageNumber(link));
                if(pullRequestPaginationModel.getPreviousPage() != null) {
                    pullRequestPaginationModel.setCurrentPage(
                            pullRequestPaginationModel.getPreviousPage() + 1);
                }
            }
            if(link.contains("next")) {
                pullRequestPaginationModel.setNextPage(findPageNumber(link));
                if(pullRequestPaginationModel.getNextPage() != null) {
                    pullRequestPaginationModel.setCurrentPage(
                            pullRequestPaginationModel.getNextPage() - 1);
                }
            }
        }
        log.debug("Pull request pagination. Current page = {}, Previous page = {}, Next page = {}",
                pullRequestPaginationModel.getCurrentPage(),
                pullRequestPaginationModel.getPreviousPage(),
                pullRequestPaginationModel.getNextPage());
    }

    private Integer findPageNumber(String link) {
        String prefix = "page=";
        String suffix = ">;";
        return Integer.parseInt(link.substring(
                link.indexOf(prefix) + prefix.length(),
                link.indexOf(suffix)
        ));
    }
}
