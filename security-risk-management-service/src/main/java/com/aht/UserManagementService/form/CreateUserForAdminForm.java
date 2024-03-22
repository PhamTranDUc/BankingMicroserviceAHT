package com.aht.UserManagementService.form;

import com.aht.UserManagementService.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserForAdminForm {

    private String username;

    private String password;

    private String email;

    private String fullname;

    private Set<Role> roles;
}
