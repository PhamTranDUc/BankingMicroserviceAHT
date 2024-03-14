package com.aht.UserManagementService.service;

import com.aht.UserManagementService.entity.Role;
import com.aht.UserManagementService.entity.User;

import java.util.List;

public interface IUserService {
    public User createUser(User user);
    public List<User> getAllUsers();
    public User getUserById(Integer id);
    public User updateUser(Integer id, User newUser);
    public void deleteUser(Integer userId);
    public void revokeRoles(Integer userId);
    public void grantRoles(Integer userId, List<Role.RoleName> roleNames);
    boolean isUserExistsByUsername(String username);
    boolean isAccountExistsByEmail(String email);
}
