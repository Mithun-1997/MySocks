package com.devops.demo.service.user;




import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devops.demo.database.entity.project.UserEntity;
import com.devops.demo.database.repository.projectrepository.userrepository.UserRepository;




@Service("userDetailsServiceImpl") // ✅ Explicit name for @Qualifier
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    // ✅ Explicit constructor for proper dependency injection
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(getRoleName(user.getRole())) // Convert role ID to role name
                .build();
    }

    private String getRoleName(int role) {
        switch (role) {
            case 1:
                return "ROLE_USER";
            case 2:
                return "ROLE_ADMIN";
            case 4:
                return "ROLE_SUPERADMIN";
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }
    }

