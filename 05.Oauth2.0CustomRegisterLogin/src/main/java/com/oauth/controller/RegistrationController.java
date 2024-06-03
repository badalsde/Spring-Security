package com.oauth.controller;

import com.oauth.dto.UserRegistrationDTO;
import com.oauth.service.DefaultUserService;
import com.oauth.service.DefaultUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private DefaultUserServiceImpl defaultUserService;

    public RegistrationController(DefaultUserServiceImpl defaultUserService) {
        super();
        this.defaultUserService = defaultUserService;
    }

    @ModelAttribute("user")
    public UserRegistrationDTO userRegistrationDto() {
        return new UserRegistrationDTO();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")
                                      UserRegistrationDTO userRegistrationDTO) {
        defaultUserService.save(userRegistrationDTO);
        return "redirect:/login";
    }
}
