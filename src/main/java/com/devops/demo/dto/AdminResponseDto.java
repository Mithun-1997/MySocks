package com.devops.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponseDto {
    private Long userId;
    private String username;
    private String email;
    private String role;
}
