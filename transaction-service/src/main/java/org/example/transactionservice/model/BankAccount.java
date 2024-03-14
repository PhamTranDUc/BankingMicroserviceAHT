package org.example.transactionservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.transactionservice.common.AccountType;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private Long userId;

    private Double balance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

}

