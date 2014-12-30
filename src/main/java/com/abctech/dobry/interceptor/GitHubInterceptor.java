package com.abctech.dobry.interceptor;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class GitHubInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        // exclude the root web context and the login event url
        if (!request.getRequestURI().equals("/dobry/") &&
            !request.getRequestURI().equals("/dobry/auth/github") &&
            !request.getRequestURI().equals("/dobry/auth/github/callback")) {
            try {
                String accessToken = request.getSession().getAttribute("accessToken").toString();
            }  catch (NullPointerException ex) {
                response.sendRedirect("/dobry/");
                return false;
            }
        }
        return true;
    }
}
