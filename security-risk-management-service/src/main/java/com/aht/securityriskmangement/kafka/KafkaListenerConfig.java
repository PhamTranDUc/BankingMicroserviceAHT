package com.aht.securityriskmangement.kafka;

import com.aht.UserManagementService.form.CreateUserForAdminForm;
import com.aht.UserManagementService.form.CreateUserForm;
import com.aht.securityriskmangement.model.Users;
import com.aht.securityriskmangement.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class KafkaListenerConfig {

    @Autowired
    private final UsersRepository usersRepository;

    @KafkaListener(topics = "create-user-topic")
    public void listenCreateUserTopic(CreateUserForm createUserData) {
        if (!createUserData.equals(null)) {
            Users users = Users.builder()
                    .username(createUserData.getUsername())
                    .email(createUserData.getEmail())
                    .fullName(createUserData.getFullname())
                    .password(createUserData.getPassword())
                    .createdAt(LocalDateTime.now())
                    .build();
            usersRepository.save(users);
        }
    }

    @KafkaListener(topics = "create-user-for-admin-topic")
    public void listenCreateUserForAdminTopic(CreateUserForAdminForm createUserData) {
        if (createUserData != null) {
            Users users = Users.builder()
                    .fullName(createUserData.getFullname())
                    .username(createUserData.getUsername())
                    .password(createUserData.getPassword())
                    .email(createUserData.getEmail())
                    .build();


            usersRepository.save(users);
        }

    }

}
