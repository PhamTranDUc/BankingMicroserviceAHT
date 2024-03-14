package com.aht.UserManagementService.controller;

import com.aht.UserManagementService.entity.Role;
import com.aht.UserManagementService.entity.User;
import com.aht.UserManagementService.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@Validated
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping()
    public User createUser(@RequestBody @Valid User user) {
        return userService.createUser(user);
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(name = "id") Integer id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable(name = "id") Integer id, @RequestBody @Valid User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") Integer id) {
        userService.deleteUser(id);
    }

    @PostMapping("/grant/{id}")
    public ResponseEntity<String> grantRoles(@PathVariable(name = "id") Integer userId, @RequestParam List<Role.RoleName> roleNames) {
        userService.grantRoles(userId, roleNames);
        return ResponseEntity.ok("Roles granted successfully.");
    }

    @DeleteMapping("/revoke/{id}")
    public ResponseEntity<String> revokeRoles(@PathVariable(name = "id") Integer userId) {
        userService.revokeRoles(userId);
        return ResponseEntity.ok("Roles revoked successfully.");
    }
}
