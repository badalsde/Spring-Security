package com.oauth.config;

import com.oauth.dto.UserRegistrationDTO;
import com.oauth.repository.UserRepository;
import com.oauth.service.DefaultUserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DefaultUserServiceImpl userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String redirectUrl = null;
        if(authentication.getPrincipal() instanceof DefaultOAuth2User) {
            DefaultOAuth2User  userDetails = (DefaultOAuth2User ) authentication.getPrincipal();
            String username = userDetails.getAttribute("email") !=null?userDetails.getAttribute("email"):userDetails.getAttribute("login")+"@gmail.com" ;

            if(userRepository.findByEmail(username) == null) {
                UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
                userRegistrationDTO.setEmail(username);
                userRegistrationDTO.setName(userDetails.getAttribute("email") !=null?userDetails.getAttribute("email"):userDetails.getAttribute("login"));
                userRegistrationDTO.setPassword(("Dummy"));
                userRegistrationDTO.setRole("USER");
                userService.save(userRegistrationDTO);
            }
        }
        redirectUrl = "/dashboard";
        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
