package com.abctech.dobry.webapp.controller;

import com.abctech.dobry.config.properties.GitHubConfig;
import com.abctech.dobry.form.GitHubPullRequestForm;
import com.abctech.dobry.webapp.json.PullRequest;
import com.abctech.dobry.webapp.model.PullRequestModel;
import com.abctech.dobry.webapp.service.TimeCalculatorService;
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
    private TimeCalculatorService timeCalculatorService;

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

        if (gitHubPullRequestForm.getRepo() != null) {
            List<PullRequest> pullRequestList =
                    gitHubPullRequestService.fetchPullRequests(
                            accessToken,
                            gitHubPullRequestForm.getRepo());
            List<PullRequestModel> pullRequestModelList =
                    timeCalculatorService.calculateDiffPullRequestList(pullRequestList);
            if (!pullRequestList.isEmpty()) {
                model.addAttribute("pullRequestModelList", pullRequestModelList);
            }
        }

        model.addAttribute("pageContent", "content/pullrequest/index");
        return "layout";
    }
}
