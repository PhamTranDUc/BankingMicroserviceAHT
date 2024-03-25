package com.aht.securityriskmangement.repository;

import com.aht.UserManagementService.entity.User;
import com.aht.securityriskmangement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);
}
