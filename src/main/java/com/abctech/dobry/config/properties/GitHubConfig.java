package com.abctech.dobry.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource("classpath:github.properties")
public class GitHubConfig {

    @Value("${github.url}")
    private String gitHubUrl;

    @Value("${github.url.authorize}")
    private String authorizeUrl;

    @Value("${github.url.access-token}")
    private String accessTokenUrl;

    @Value("${github.app.client.id}")
    private String appClientId;

    @Value("${github.app.client.secret}")
    private String appClientSecret;

    @Value("${github.authorize.scope}")
    private String authorizeScope;

    @Value("${github.authorize.state}")
    private String authorizeState;

    @Value("${github.organization}")
    private String organization;

    @Value("#{'${github.organization.repos}'.split(',')}")
    private List<String> organizationRepos;

    public String getGitHubUrl() {
        return gitHubUrl;
    }

    public String getAuthorizeUrl() {
        return authorizeUrl;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public String getAppClientId() {
        return appClientId;
    }

    public String getAppClientSecret() {
        return appClientSecret;
    }

    public String getAuthorizeScope() {
        return authorizeScope;
    }

    public String getAuthorizeState() {
        return authorizeState;
    }

    public String getOrganization() {
        return organization;
    }

    public List<String> getOrganizationRepos() {
        return organizationRepos;
    }
}
