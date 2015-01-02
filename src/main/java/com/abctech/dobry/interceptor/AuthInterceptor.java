package com.abctech.dobry.interceptor;

import com.abctech.dobry.config.properties.GitHubConfig;
import com.abctech.dobry.webapp.service.BasicAuthenticationService;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private GitHubConfig gitHubConfig;

    @Autowired
    private BasicAuthenticationService basicAuthenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception{

        if(request.getSession() != null && request.getSession().getAttribute("accessToken") != null) {
            HttpResponse httpResponse = Request.Get("https://api.github.com/applications/" +
                    gitHubConfig.getAppClientId() +
                    "/tokens/" + request.getSession().getAttribute("accessToken").toString())
                    .addHeader("Authorization", "Basic " + basicAuthenticationService.createAuthorizationValue(gitHubConfig.getAppClientId(), gitHubConfig.getAppClientSecret()))
                    .execute().returnResponse();
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            log.debug("Status code = {}", statusCode);

        }


        String uri = request.getRequestURI().substring(request.getContextPath().length());
        if (!uri.equals("/") &&
            !uri.equals("/auth/github") &&
            !uri.equals("/auth/github/callback")) {
            if(request.getSession() == null ||
                    request.getSession().getAttribute("accessToken") == null) {
                response.sendRedirect(request.getContextPath());
                return false;
            }
        }
        return true;
    }
}
