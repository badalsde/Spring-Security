package com.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/home")
    public String homeDetails(){
        return "Hello Home...";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminDetails(){
        return "Hello Admin...";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String userDetails(){
        return "Hello User...";
    }
}
