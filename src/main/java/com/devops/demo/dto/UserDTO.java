package com.devops.demo.dto;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String mobileNumber;
    private Integer role;  // ✅ Role as Integer

    public UserDTO(Long id, String username, String email, String mobileNumber, Integer role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.role = role;
    }

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getMobileNumber() { return mobileNumber; }
    public Integer getRole() { return role; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public void setRole(Integer role) { this.role = role; }
}
