package com.abctech.dobry.webapp.service.github;

import com.abctech.dobry.webapp.json.OrganizationRepository;
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
public class GitHubOrganizationRepositoryService {

    private static final Logger log = LoggerFactory.getLogger(GitHubOrganizationRepositoryService.class);

    @Autowired
    private RestTemplate restTemplate;

    public List<OrganizationRepository> fetchAmediaRepositories(String accessToken) {
        log.debug("Fetching all amedia repositories");
        ResponseEntity<OrganizationRepository[]> response = restTemplate.exchange(
                "https://api.github.com/orgs/amedia/repos",
                HttpMethod.GET,
                createHeaderAuthorization(accessToken),
                OrganizationRepository[].class);
        return Arrays.asList(response.getBody());
    }

    private HttpEntity<String> createHeaderAuthorization(String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "token " + value);
        return new HttpEntity<>(headers);
    }
}
