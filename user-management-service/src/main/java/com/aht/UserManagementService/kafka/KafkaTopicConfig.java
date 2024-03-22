package com.aht.UserManagementService.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    // create topic when call create user api
    @Bean
    public NewTopic createUserTopic() {
        return TopicBuilder.name("create-user-topic")
                .partitions(4)
                .replicas(1)
                .build();
    }

    // create topic when call create user for admin api
    @Bean
    public NewTopic createUserForAdminTopic() {
        return TopicBuilder.name("create-user-for-admin-topic")
                .partitions(4)
                .replicas(1)
                .build();
    }
}

