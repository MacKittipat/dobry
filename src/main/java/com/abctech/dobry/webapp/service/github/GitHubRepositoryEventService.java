package com.abctech.dobry.webapp.service.github;

import com.abctech.dobry.webapp.json.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GitHubRepositoryEventService {

    private static final Logger log = LoggerFactory.getLogger(GitHubRepositoryEventService.class);

    private static final int EVENT_DISPLAY_AMOUNT = 10;
    private static final List<String> EVENT_TYPE = new ArrayList<String>() {
        {
            add("CommitCommentEvent");
            add("IssueCommentEvent");
            add("PullRequestEvent");
            add("PullRequestReviewCommentEvent");
            add("PushEvent");
        }
    };

    @Autowired
    private RestTemplate restTemplate;

    public List<Event> fetchRepositoryEvents(String accessToken, String owner, String repository) {
        log.debug("Fetching repository events");
        ResponseEntity<Event[]> response = restTemplate.exchange(
                "https://api.github.com/repos/" + owner + "/" + repository + "/events?per_page=100'",
                HttpMethod.GET,
                createHeaderAuthorization(accessToken),
                Event[].class);

        return filterEvents(Arrays.asList(response.getBody()));
    }

    private List<Event> filterEvents(List<Event> eventList){
        List<Event> filterEventList = new ArrayList<>();
        int i = 0;
        for (Event event: eventList){
            if(EVENT_TYPE.contains(event.getType())){
                filterEventList.add(event);
                i++;
                if(i == EVENT_DISPLAY_AMOUNT){
                    break;
                }
            }
        }
        return filterEventList;
    }

    private HttpEntity<String> createHeaderAuthorization(String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "token " + value);
        return new HttpEntity<>(headers);
    }
}
