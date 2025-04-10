package com.devops.demo.service.auth;



import org.springframework.context.annotation.Primary;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devops.demo.database.entity.project.UserEntity;
import com.devops.demo.database.repository.projectrepository.userrepository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user.getRole())
        );
    }

    private List<GrantedAuthority> getAuthorities(int role) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        switch (role) {
            case 1:
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                break;
            case 2:
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;
            case 4:
                authorities.add(new SimpleGrantedAuthority("ROLE_SUPERADMIN"));
                break;
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }

        return authorities;
    }
}
