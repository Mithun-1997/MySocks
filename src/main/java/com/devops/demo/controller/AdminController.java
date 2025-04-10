package com.devops.demo.controller;

import com.devops.demo.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create")
    public String createAdminUser(@RequestParam String username, 
                                  @RequestParam String email,
                                  @RequestParam String mobileNumber, 
                                  @RequestParam String password) {
        adminService.createAdminUser(username, email, mobileNumber, password);
        return "Admin user created successfully!";
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
        return "User deleted successfully!";
    }

    @PutMapping("/changeRole/{userId}")
    public String changeUserRole(@PathVariable Long userId, @RequestParam String role) {
        adminService.changeUserRole(userId, role);
        return "User role updated successfully!";
    }
}
