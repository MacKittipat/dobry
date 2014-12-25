package com.abctech.dobry.webapp.controller;

import com.abctech.dobry.webapp.json.PullRequest;
import com.abctech.dobry.webapp.service.github.GitHubPullRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private GitHubPullRequestService gitHubPullRequestService;

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("pageContent", "content/main/index");
        model.addAttribute("gitHubLoginUrl", "/auth/github");
        return "layout";
    }

    @ResponseBody
    @RequestMapping(value = "/test")
    public String test(HttpServletRequest request) {
        PullRequest pullRequest = new PullRequest();
        if(request.getSession().getAttribute("accessToken") != null) {
            String accessToken = request.getSession().getAttribute("accessToken").toString();
            log.debug("accessToken={}", accessToken);
            pullRequest = gitHubPullRequestService.fetchPullRequest(accessToken, 263);
        }
        return pullRequest.getTitle() + " - " + pullRequest.getCreatedAt();
    }
}
