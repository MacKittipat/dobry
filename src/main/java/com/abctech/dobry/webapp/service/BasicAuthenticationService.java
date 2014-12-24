package com.abctech.dobry.webapp.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class BasicAuthenticationService {

    public String createAuthorizationValue(String username, String password) {
        return new String(Base64.encodeBase64((username + ":" + password).getBytes()));
    }
}
