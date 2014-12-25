package com.abctech.dobry.webapp.controller;

import com.abctech.dobry.form.AuthForm;
import com.abctech.dobry.webapp.service.BasicAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    private BasicAuthenticationService basicAuthenticationService;

    @RequestMapping(value = "/")
    public String home() {
        return "redirect:auth";
    }

    @RequestMapping(value = "/auth")
    public String authenticate(Model model,
                                    HttpServletRequest request,
                                    @ModelAttribute("authForm") AuthForm authForm) {
        if (basicAuthenticationService.getAuthorizationValue() != null) {
            return "index";
        }
        model.addAttribute("pageContent", "auth");
        if (request.getMethod().equals(RequestMethod.POST.toString())) {
            String base64Auth = basicAuthenticationService
                                .createAuthorizationValue(authForm.getUsername(), authForm.getPassword());
            // need to implement Github authentication checking
            request.getSession().setAttribute("authorization", base64Auth);
            if (basicAuthenticationService.getAuthorizationValue() != null) {
                return "redirect:index";
            }
        }
        return "layout";
    }

    @RequestMapping(value = "/index")
    public String index(Model model,
                        HttpServletRequest request,
                        @ModelAttribute("result") AuthForm authForm) {
        String result = basicAuthenticationService.getAuthorizationValue();
        return "index";
    }
}
