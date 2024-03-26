package com.aht.UserManagementService.controller;

import com.aht.UserManagementService.dto.UserDTO;
import com.aht.UserManagementService.entity.Role;
import com.aht.UserManagementService.entity.Users;
import com.aht.UserManagementService.form.CreateUserForAdminForm;
import com.aht.UserManagementService.form.CreateUserForm;
import com.aht.UserManagementService.form.UpdateUserForm;
import com.aht.UserManagementService.form.UpdateUserPasswordForm;
import com.aht.UserManagementService.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user")
@Validated
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserForm form) {
        userService.createUser(form);
        userService.sendUserCreatedMessage(form);
        return ResponseEntity.ok("Create Successfully!");
    }

    @PostMapping("/ad")
    public ResponseEntity<String> createUserFromAdmin(@RequestBody @Valid CreateUserForAdminForm form) {
        userService.createUserFromAdmin(form);
        userService.sendCreateUserAdminForAdminMessage(form);
        return ResponseEntity.ok("Create Successfully!");
    }

    @GetMapping()
    public List<UserDTO> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (Users user : users) {
            userDTOS.add(userToUserDTO(user));
        }
        return userDTOS;
    }

    @GetMapping("/ad/role")
    public List<Users> getAllUserWithRoles() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable(name = "id") Integer id) {
        Users user = userService.getUserById(id);
        UserDTO userDTO = userToUserDTO(user);
        return userDTO;
    }

    @GetMapping("/ad/role/{id}")
    public Users getUserWithRoleById(@PathVariable(name = "id") Integer id) {
        return userService.getUserById(id);
    }

    @PutMapping()
    public ResponseEntity<String> updateUser(@RequestBody @Valid UpdateUserForm form) {
        userService.updateUser(form);
        return ResponseEntity.ok("Update Successfully!");
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping("/password/{id}")
    public Users updateUserPassword(@PathVariable(name = "id") Integer id, @RequestBody @Valid UpdateUserPasswordForm form) {

        return userService.updatePassword(id, form);
    }

    @PostMapping("/ad/grant/{id}")
    public ResponseEntity<String> grantRoles(@PathVariable(name = "id") Integer userId, @RequestParam List<Role.RoleName> roleNames) {
        userService.grantRoles(userId, roleNames);
        return ResponseEntity.ok("Roles granted successfully.");
    }

    @DeleteMapping("/ad/revoke/{id}")
    public ResponseEntity<String> revokeRoles(@PathVariable(name = "id") Integer userId) {
        userService.revokeRoles(userId);
        return ResponseEntity.ok("Roles revoked successfully.");
    }

    private UserDTO userToUserDTO(Users user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullname(user.getFullname());
        userDTO.setCreated_at(user.getCreated_at());

        return userDTO;
    }
}
