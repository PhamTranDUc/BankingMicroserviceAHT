package org.example.transactionservice.service.implement;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.transactionservice.exception.AccountIsNotValidException;
import org.example.transactionservice.exception.InsufficientBalanceException;
import org.example.transactionservice.model.BankAccount;
import org.example.transactionservice.repository.BankAccountRepository;
import org.example.transactionservice.service.IService.IBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankAccountService implements IBankAccountService {

    @Autowired
    private final BankAccountRepository bankAccountRepository;

    @Override
    public BankAccount getBankAccById(Long accountId) throws Exception {
        BankAccount bankAccount = bankAccountRepository.findBankAccoutById(accountId);
        if (bankAccount != null) {
            return bankAccount;
        }
        throw new AccountIsNotValidException("Account is not exist");
    }

    @Transactional
    @Override
    public void updateAccountBalance(Long accountId, Double amount) throws Exception {
        BankAccount bankAccount = getBankAccById(accountId);

        double updatedBalance = bankAccount.getBalance() + amount;

        bankAccount.setBalance(updatedBalance);
        bankAccountRepository.save(bankAccount);
    }
}
