package com.abctech.dobry.webapp.controller;

import com.abctech.dobry.webapp.json.AccessToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "/test")
    public String test() {
        log.debug("Test ...");
        return "redirect:https://github.com/login/oauth/authorize?client_id=1b6ee3124225fa76fe17&scope=repo:status&state=123";
    }

    @ResponseBody
    @RequestMapping(value = "/github/callback")
    public String gitHubCallBack(@RequestParam String code,
                                 @RequestParam String state) {

        log.debug("Callback ...");

        Request request = Request.Post("https://github.com/login/oauth/access_token")
                .addHeader("Accept", "application/json")
                .bodyForm(Form.form()
                        .add("client_id", "1b6ee3124225fa76fe17")
                        .add("client_secret", "119aa53f37c344f5adfe01a56a185877551ae12a")
                        .add("code", code)
                        .build());
        try {
            Response response = request.execute();
            String result = response.returnContent().asString();
            log.debug(">>" + result);
            ObjectMapper mapper = new ObjectMapper();
            AccessToken accessToken =
                    mapper.readValue(result, AccessToken.class);
            log.debug("access_token = {}", accessToken.getAccessToken());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Callback ...";
    }
}
