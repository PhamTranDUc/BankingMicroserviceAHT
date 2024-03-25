package com.aht.UserManagementService.repository;

import com.aht.UserManagementService.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    public Role findByRoleName(Role.RoleName role_name);
}
