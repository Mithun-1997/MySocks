package com.devops.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRequestDto {
    private Long userId;
    private String role;
}
