package com.devops.demo.service.auth;

import java.time.LocalDateTime;




import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devops.demo.database.entity.project.UserEntity;
import com.devops.demo.database.repository.projectrepository.userrepository.UserRepository;
import com.devops.demo.dto.AuthRequestDto;
import com.devops.demo.dto.AuthResponseDto;
import com.devops.demo.dto.SignupRequestDto;


@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponseDto login(AuthRequestDto authRequest) {
    	UserEntity user = userRepository.findByUsername(authRequest.getUsernameOrEmail())
                .filter(u -> passwordEncoder.matches(authRequest.getPassword(), u.getPassword()))
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // Generate a placeholder token (replace with JWT logic if needed)
        String token = "mock_token_" + user.getId();

        return new AuthResponseDto(token, user.getUsername(), user.getRole());
    }

    @Override
    public AuthResponseDto signup(SignupRequestDto request) {
        if (userRepository.existsByUsername(request.getUsername()) || userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setMobileNumber(request.getMobileNumber());
        user.setGender(request.getGender().getValue()); // Convert Gender Enum to Integer
        user.setRole(request.getRole().getValue()); // Convert Role Enum to Integer
        user.setCreationTime(LocalDateTime.now());
        user.setValidTill(LocalDateTime.now().plusYears(1));

        UserEntity savedUser = userRepository.save(user);

        // Generate a placeholder token (replace with JWT logic if needed)
        String token = "mock_token_" + savedUser.getId();

        return new AuthResponseDto(token, savedUser.getUsername(), savedUser.getRole());
    }
}
