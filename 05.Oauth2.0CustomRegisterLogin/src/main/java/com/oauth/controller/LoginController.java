package com.oauth.implementation.controller;

import com.oauth.service.DefaultUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oauth.repository.UserRepository;
import com.oauth.dto.UserLoginDTO;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private DefaultUserServiceImpl defaultUserService;

    @Autowired
    private UserRepository userRepo;

    @ModelAttribute("user")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public void loginUser(@ModelAttribute("user") UserLoginDTO userLoginDTO) {
        System.out.println("UserDTO "+userLoginDTO);
        defaultUserService.loadUserByUsername(userLoginDTO.getEmail());
    }

}
