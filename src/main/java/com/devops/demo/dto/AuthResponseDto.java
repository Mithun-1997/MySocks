package com.devops.demo.dto;

public class AuthResponseDto {
    private String token;
    private String username;
    private int role; // Role should be stored as an integer

    // Default Constructor
    public AuthResponseDto() {}

    // Parameterized Constructor
    public AuthResponseDto(String token, String username, int role) {
        this.token = token;
        this.username = username;
        this.role = role;
    }

    // Getters
    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public int getRole() { // Change return type to int
        return role;
    }

    // Setters
    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(int role) { // Ensure role is stored as int
        this.role = role;
    }
}
