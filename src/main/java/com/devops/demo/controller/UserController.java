package com.devops.demo.controller;

import com.devops.demo.database.entity.project.UserEntity;
import com.devops.demo.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/server/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        Optional<UserEntity> userOpt = userService.findByUsername(username);

        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return ResponseEntity.ok("✅ Login successful!");
            } else {
                return ResponseEntity.status(401).body("❌ Invalid password");
            }
        }

        return ResponseEntity.status(404).body("❌ User not found");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserEntity user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("❌ Username already exists");
        }

        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("❌ Email already exists");
        }

        userService.saveUser(user);
        return ResponseEntity.ok("✅ User registered successfully");
    }

    @GetMapping("/getUserAll")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id) {
        Optional<UserEntity> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
