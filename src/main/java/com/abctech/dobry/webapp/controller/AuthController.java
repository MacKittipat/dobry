package com.abctech.dobry.webapp.controller;

import com.abctech.dobry.config.properties.GitHubConfig;
import com.abctech.dobry.webapp.json.AccessToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private GitHubConfig gitHubConfig;

    @RequestMapping(value = "/github")
    public String authGitHub() {
        String gitHubAuthorizeUrl = gitHubConfig.getAuthorizeUrl() +
                "?client_id=" + gitHubConfig.getAppClientId() +
                "&scope=" + gitHubConfig.getAuthorizeScope() +
                "&state=" + gitHubConfig.getAuthorizeState();
        log.debug("Sending request to GitHub authorize.");
        return "redirect:" + gitHubAuthorizeUrl;
    }

    @RequestMapping(value = "/github/callback")
    public String gitHubCallBack(@RequestParam String code,
                                 @RequestParam String state,
                                 HttpServletRequest request) {
        log.debug("Sending request to GitHub access token.");
        Request postRequest = Request.Post(gitHubConfig.getAccessTokenUrl())
                .addHeader("Accept", "application/json")
                .bodyForm(Form.form()
                        .add("client_id", gitHubConfig.getAppClientId())
                        .add("client_secret", gitHubConfig.getAppClientSecret())
                        .add("code", code)
                        .build());
        try {
            Response response = postRequest.execute();
            String accessTokenResult = response.returnContent().asString();
            ObjectMapper mapper = new ObjectMapper();
            AccessToken accessToken =
                    mapper.readValue(accessTokenResult, AccessToken.class);
            request.getSession().setAttribute("accessToken", accessToken.getAccessToken());
        } catch (IOException e) {
            log.error("Cannot get access token from GitHub.", e);
        }
        return "redirect:/pullrequest";
    }
}
