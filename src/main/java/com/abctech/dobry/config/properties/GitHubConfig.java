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

    public String getAuthorizeUrl() {
        return authorizeUrl;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }
}
