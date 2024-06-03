package com.oauth.service;

import com.oauth.dto.UserRegistrationDTO;
import com.oauth.entity.Role;
import com.oauth.entity.User;
import com.oauth.repository.RoleRepository;
import com.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service("defaultUserService")
public class DefaultUserServiceImpl implements DefaultUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    @Override
    public User save(UserRegistrationDTO userRegistrationDTO) {
        Role role = roleRepo.findByRole("USER");
        User user = new User();
        user.setEmail(userRegistrationDTO.getEmail());
        user.setName(userRegistrationDTO.getName());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setRoles((Set<Role>) role);

        return userRepo.save(user);
    }
}
