package com.abctech.dobry.webapp.controller;

import com.abctech.dobry.form.GitHubPullRequestForm;
import com.abctech.dobry.webapp.service.github.GitHubPullRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@RequestMapping(value = "/pullrequest")
@Controller
public class PullRequestController {

    @Autowired
    private GitHubPullRequestService gitHubPullRequestService;

    @RequestMapping(value = "/")
    public String index(Model model,
                        HttpServletRequest request,
                        @ModelAttribute GitHubPullRequestForm gitHubPullRequestForm) {
        Map<String, String> repoMap = new LinkedHashMap<>();
        repoMap.put("", "Please select");
        repoMap.put("test", "test");
        repoMap.put("test2", "test2");
        model.addAttribute("repoMap", repoMap);
        String accessToken = request.getSession().getAttribute("accessToken").toString();
        if (accessToken != null) {
            model.addAttribute("pullRequestList",
                    gitHubPullRequestService.fetchPullRequests(accessToken));
        }
        model.addAttribute("pageContent", "content/pullrequest/index");
        return "layout";
    }
}
