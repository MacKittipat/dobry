package com.abctech.dobry;

import com.abctech.dobry.webapp.service.BasicAuthenticationService;
import com.abctech.dobry.webapp.service.github.GitHubPullRequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class AppTest {

    @Autowired
    private GitHubPullRequestService gitHubPullRequestService;

    @Autowired
    private BasicAuthenticationService basicAuthenticationService;

    @Test
    public void testFetchPullRequest() {
        // TODO input yout authValue here. Genreate from BasicAuthenticationService.createAuthorizationValue
        String authValue = "";
        gitHubPullRequestService.fetchPullRequest(authValue, 263);
    }
}
