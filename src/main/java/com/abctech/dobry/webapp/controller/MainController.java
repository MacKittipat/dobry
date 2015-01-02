package com.abctech.dobry.webapp.controller;

import com.abctech.dobry.webapp.service.github.GitHubPullRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private GitHubPullRequestService gitHubPullRequestService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("pageContent", "content/main/index");
        model.addAttribute("gitHubLoginUrl", "/auth/github");
        return "layout";
    }
}
