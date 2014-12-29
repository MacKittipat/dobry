package com.abctech.dobry.webapp.controller;

import com.abctech.dobry.config.properties.GitHubConfig;
import com.abctech.dobry.form.GitHubPullRequestForm;
import com.abctech.dobry.webapp.json.PullRequest;
import com.abctech.dobry.webapp.service.github.GitHubOrganizationRepositoryService;
import com.abctech.dobry.webapp.service.github.GitHubPullRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/pullrequest")
@Controller
public class PullRequestController {

    @Autowired
    private GitHubPullRequestService gitHubPullRequestService;

    @Autowired
    private GitHubOrganizationRepositoryService gitHubOrganizationRepositoryService;

    @Autowired
    private GitHubConfig gitHubConfig;

    @RequestMapping(value = "/")
    public String index(Model model,
                        HttpServletRequest request,
                        @ModelAttribute GitHubPullRequestForm gitHubPullRequestForm) {
        String accessToken = request.getSession().getAttribute("accessToken").toString();

        Map<String, String> repoMap = new LinkedHashMap<>();
        repoMap.put("", "Please select");
        for(String repo : gitHubConfig.getOrganizationRepos()) {
            repoMap.put(repo, repo);
        }
        model.addAttribute("repoMap", repoMap);

        if (accessToken != null && gitHubPullRequestForm.getRepo() != null) {
            List<PullRequest> pullRequestList =
                    gitHubPullRequestService.fetchPullRequests(
                            accessToken,
                            gitHubPullRequestForm.getRepo());
            if (!pullRequestList.isEmpty()) {
                model.addAttribute("pullRequestList", pullRequestList);
            }
        }

        model.addAttribute("pageContent", "content/pullrequest/index");
        return "layout";
    }
}
