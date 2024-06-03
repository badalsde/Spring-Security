package com.oauth.service;

import com.oauth.dto.UserRegistrationDTO;
import com.oauth.entity.User;

public interface DefaultUserService {
    User save(UserRegistrationDTO userRegistrationDTO);
}
