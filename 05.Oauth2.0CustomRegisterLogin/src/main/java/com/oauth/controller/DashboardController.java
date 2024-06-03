package com.oauth.controller;

import com.oauth.entity.User;
import com.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private UserRepository userRepo;
    @GetMapping
    public String displayDashboard(Model model){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if(securityContext.getAuthentication().getPrincipal() instanceof DefaultOAuth2User) {
            DefaultOAuth2User user = (DefaultOAuth2User) securityContext.getAuthentication().getPrincipal();
            model.addAttribute("userDetails", user.getAttribute("name")!= null ?user.getAttribute("name"):user.getAttribute("login"));
        }else {
            User user = (User) securityContext.getAuthentication().getPrincipal();
            com.oauth.entity.User users = userRepo.findByEmail(user.getName());
            model.addAttribute("userDetails", users.getName());
        }
        return "dashboard";
    }
}
