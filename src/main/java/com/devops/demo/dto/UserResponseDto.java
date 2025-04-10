package com.devops.demo.dto;

import com.devops.demo.database.enums.Gender;
import com.devops.demo.database.enums.Role;

public class UserResponseDto {
    private Long id;
    private String username;
    private String mobileNumber;
    private Gender gender;
    private Role role;

    // Default Constructor
    public UserResponseDto() {}

    // Parameterized Constructor (5 parameters)
    public UserResponseDto(Long id, String username, String mobileNumber, Gender gender, Role role) {
        this.id = id;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.role = role;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public Role getRole() {
        return role;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}