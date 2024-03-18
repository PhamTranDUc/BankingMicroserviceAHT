package org.example.transactionservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.internals.Topic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public NewTopic topic(){
        return TopicBuilder.name("balance_updates")
                .build();
    }


}
