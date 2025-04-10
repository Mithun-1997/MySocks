package com.devops.demo.service.auth;

import com.devops.demo.dto.AuthRequestDto;
import com.devops.demo.dto.AuthResponseDto;
import com.devops.demo.dto.SignupRequestDto;

public interface AuthService {
    AuthResponseDto login(AuthRequestDto authRequest);
    AuthResponseDto signup(SignupRequestDto request);  // Ensure return type matches
}


