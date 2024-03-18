package com.BankAHT.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageKafkaRepository extends JpaRepository<MessageKafka,Long> {
}
