package com.abctech.dobry.webapp.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class BasicAuthenticationService {

    public String createAuthorizationValue(String username, String password) {
        return new String(Base64.encodeBase64((username + ":" + password).getBytes()));
    }

    public String getAuthorizationValue() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if (request.getSession().getAttribute("authorization") == null) {
            return null;
        }
        return request.getSession().getAttribute("authorization").toString();
    }
}
