package org.example.transactionservice.kafka;

import lombok.AllArgsConstructor;
import org.example.transactionservice.common.AccountType;
import org.example.transactionservice.model.BankAccount;
import org.example.transactionservice.model.MessageCreateAccount;
import org.example.transactionservice.service.IService.IBankAccountService;
import org.example.transactionservice.service.implement.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccountConsumer {
    private static final Logger LOGGER= LoggerFactory.getLogger(CreateAccountConsumer.class);

    @Qualifier("bankAccountService")
    private IBankAccountService accountService;

    @KafkaListener(
            topics = "create_account",
            groupId = "myGroup"
    )
    public void consumer(String evenMessage) {

//        AccountId+" "+CustomerId+" "+AccountType+" "+Balance+" "+enable;
        LOGGER.info(String.format("Event message received => %s", evenMessage));
        String accountId= evenMessage.split(" ")[0];
        String customerId= evenMessage.split(" ")[1];
        String accountType=evenMessage.split(" ")[2];
        String balance=evenMessage.split(" ")[3];
        String enable=evenMessage.split(" ")[4];
        BankAccount bankAccount= new BankAccount();
        bankAccount.setAccountId(Long.parseLong(accountId));
        bankAccount.setUserId(Long.parseLong(customerId));
        if (accountType.equals("SAVINGS")){
            bankAccount.setAccountType(AccountType.SAVINGS);
        }else if (accountType.equals("CHECKOUT")){
            bankAccount.setAccountType(AccountType.CHECKOUT);
        }
        bankAccount.setBalance(Double.parseDouble(balance));
        MessageCreateAccount messageCreateAccount= new MessageCreateAccount(bankAccount.toString());
        accountService.createAccount(bankAccount);
    }
}
