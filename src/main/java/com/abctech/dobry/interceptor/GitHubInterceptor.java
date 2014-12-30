package com.abctech.dobry.interceptor;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class GitHubInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception{
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
