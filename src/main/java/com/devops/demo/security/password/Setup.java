package com.devops.demo.security.password;


import jakarta.annotation.PostConstruct;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.devops.demo.database.entity.project.UserEntity;
import com.devops.demo.database.repository.projectrepository.userrepository.UserRepository;

import java.time.LocalDateTime;

@Component
public class Setup {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ Explicit constructor to ensure dependencies are injected
    public Setup(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        try {
            if (!userRepository.existsByUsername("superadminuser")) {  
            	UserEntity superAdmin = new UserEntity();
                superAdmin.setUsername("superadminuser");
                superAdmin.setEmail("superadmin@example.com");
                superAdmin.setPassword(passwordEncoder.encode("SuperSecurePassword"));
                superAdmin.setMobileNumber("1234567890");
                superAdmin.setGender(1);  // Male = 1
                superAdmin.setRole(4);    // SuperAdmin = 4
                superAdmin.setCreationTime(LocalDateTime.now());
                superAdmin.setValidTill(LocalDateTime.now().plusYears(2));

                userRepository.save(superAdmin);
                System.out.println("✅ SuperAdmin user created successfully!");
            } else {
                System.out.println("ℹ️ SuperAdmin user already exists.");
            }
        } catch (Exception e) {
            System.err.println("❌ Setup failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
