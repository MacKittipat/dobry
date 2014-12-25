package com.abctech.dobry.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:github.properties")
public class GitHubConfig {

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
}
