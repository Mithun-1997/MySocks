package com.devops.demo.dto;

import com.devops.demo.database.enums.Gender;
import com.devops.demo.database.enums.Role;

public class SignupRequestDto {
    private String username;
    private String email;
    private String mobileNumber;
    private String password;
    private Gender gender;
    private Role role;

    public SignupRequestDto() {}

    public SignupRequestDto(String username, String email, String mobileNumber, String password, Gender gender, Role role) {
        this.username = username;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.gender = gender;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
