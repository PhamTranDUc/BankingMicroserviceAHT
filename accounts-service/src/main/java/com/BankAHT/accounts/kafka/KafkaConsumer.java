package com.BankAHT.accounts.kafka;

import com.BankAHT.accounts.dto.AccountDto;
import com.BankAHT.accounts.repository.MessageKafkaRepository;
import com.BankAHT.accounts.service.impl.AccountServiceImlp;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumer {

    private static final Logger LOGGER= LoggerFactory.getLogger(KafkaConsumer.class);
    private MessageKafkaRepository messageKafkaRepository;
    private AccountServiceImlp accountService;

    @KafkaListener(
            topics = "balance_updates",
            groupId = "myGroup"
    )
    public void cosumer(String evenMessage){

        LOGGER.info(String.format("Event message received => %s", evenMessage));
        String accountId= evenMessage.split(" ")[0];
        String balance= evenMessage.split(" ")[1];
        AccountDto accounts= accountService.fetchAccount(Long.parseLong(accountId));
        accounts.setBalance(Long.parseLong(balance));
        accountService.updateAccount(accounts);

//        MessageKafka messageKafka= new MessageKafka();
//
//        messageKafka.setAmount(evenMessage);
//
//        messageKafkaRepository.save(messageKafka);

    }
}
