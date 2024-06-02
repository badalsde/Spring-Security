package com.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class OauthSecurityController {

    @GetMapping("/auth")
    public String getRequest(Principal user) {
        return "welcome";
    }
}
