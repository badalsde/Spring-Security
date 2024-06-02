package com.oauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class OauthSecurityController {

    @GetMapping("/")
    public String getRequest(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

            // Get the user's Name
            String userName = oauth2User.getAttribute("name");
            model.addAttribute("userName", userName);

            // Get the user's email address
            String email = oauth2User.getAttribute("email");
            model.addAttribute("email", email);

            // Get the user's gender (if available)
            String gender = oauth2User.getAttribute("gender");
            model.addAttribute("gender", gender);
        }
        // Add model attributes if needed
        model.addAttribute("message", "Hello, welcome to our application!");

        // Return the name of the Thymeleaf template without the file extension
        return "welcome";
    }
}
