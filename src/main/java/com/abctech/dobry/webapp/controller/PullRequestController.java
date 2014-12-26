package com.abctech.dobry.webapp.controller;

import com.abctech.dobry.form.GithubPullRequestForm;
import com.abctech.dobry.webapp.json.PullRequest;
import com.abctech.dobry.webapp.service.github.GitHubPullRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping(value = "/pullrequest")
@Controller
public class PullRequestController {

    @Autowired
    private GitHubPullRequestService gitHubPullRequestService;

    @RequestMapping(value = "/")
    public String index(Model model,
                                  HttpServletRequest request,
                                  @ModelAttribute("githubPullRequestForm")GithubPullRequestForm githubPullRequestForm){
        PullRequest pullRequest = null;
        List<PullRequest> pullRequestList = null;
        String accessToken = request.getSession().getAttribute("accessToken").toString();
        if (githubPullRequestForm.getId() != null && accessToken != null) {
            pullRequest = gitHubPullRequestService.fetchPullRequest(accessToken, githubPullRequestForm.getId());
        }
        if (accessToken != null && pullRequest == null) {
            pullRequestList = gitHubPullRequestService.fetchPullRequests(accessToken);
        }
        model.addAttribute("pageContent", "content/pullrequest/index");
        model.addAttribute("pullRequest", pullRequest);
        model.addAttribute("pullRequestList", pullRequestList);
        return "layout";
    }
}
