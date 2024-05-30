package com.security.service;

import com.security.dto.UserRegistrationDTO;
import com.security.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface DefaultUserService extends UserDetailsService {
    User save(UserRegistrationDTO userRegistrationDTO);
}
