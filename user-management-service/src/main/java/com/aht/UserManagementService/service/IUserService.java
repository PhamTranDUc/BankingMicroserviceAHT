package com.aht.UserManagementService.service;

import com.aht.UserManagementService.entity.Role;
import com.aht.UserManagementService.entity.User;
import com.aht.UserManagementService.form.CreateUserForAdminForm;
import com.aht.UserManagementService.form.CreateUserForm;
import com.aht.UserManagementService.form.UpdateUserForm;
import com.aht.UserManagementService.form.UpdateUserPasswordForm;

import java.util.List;

public interface IUserService {
    public void createUser(CreateUserForm user);
    public void createUserFromAdmin(CreateUserForAdminForm user);
    public List<User> getAllUsers();
    public User getUserById(Integer id);
    public User updateUser(UpdateUserForm form);
    public void deleteUser(Integer userId);
    public User updatePassword(Integer id, UpdateUserPasswordForm form);
    public void revokeRoles(Integer userId);
    public void grantRoles(Integer userId, List<Role.RoleName> roleNames);
    boolean isUserExistsByUsername(String username);
    boolean isUserExistsByEmail(String email);
    public boolean isUserExistsByID(Integer id);
    void sendUserCreatedMessage(CreateUserForm user);
    void sendCreateUserAdminForAdminMessage(CreateUserForAdminForm createUserForAdminForm);
}
