package org.example.transactionservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageUpdateBalance {
    private String accountId;
    private String balance;
    public String toString(){
        return this.accountId+" "+this.balance;
    }
}
