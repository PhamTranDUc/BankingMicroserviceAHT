package com.BankAHT.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MessageKafka {

    @Id
    private String accountsId;

    private String balance;
}
