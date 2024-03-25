package com.aht.UserManagementService.repository;

import com.aht.UserManagementService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByUserId(Integer id);
    public User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
