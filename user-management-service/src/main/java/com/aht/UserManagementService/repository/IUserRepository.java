package com.aht.UserManagementService.repository;

import com.aht.UserManagementService.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<Users, Integer> {
    List<Users> findAllByUserId(Integer id);
    public Users findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
