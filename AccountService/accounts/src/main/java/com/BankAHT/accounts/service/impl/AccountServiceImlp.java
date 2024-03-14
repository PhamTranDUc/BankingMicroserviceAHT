package com.BankAHT.accounts.service.impl;

import com.BankAHT.accounts.dto.AccountDto;
import com.BankAHT.accounts.entity.Accounts;
import com.BankAHT.accounts.exception.ResourceNoFoundException;
import com.BankAHT.accounts.mapper.AccountMapper;
import com.BankAHT.accounts.repository.AccountRepository;
import com.BankAHT.accounts.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class AccountServiceImlp implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public void createAccount(AccountDto accountDto) {
        Accounts accounts= AccountMapper.AccountDtoToAccount(accountDto);
        accounts.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        accounts.setCreatedBy("PTD-PTIT");
        accountRepository.save(accounts);
  }

    @Override
    public AccountDto fetchAccount(Long accountNumber) {
       Accounts accounts= accountRepository.findById(accountNumber).orElseThrow(()->new ResourceNoFoundException("Account","accountNumber",accountNumber.toString()));
       AccountDto accountDto= AccountMapper.AccounttoAccountDto(accounts);
       return  accountDto;
    }

    @Override
    public boolean updateAccount(AccountDto accountDto) {

        Accounts oldAccouts= accountRepository.findById(accountDto.getAccountId()).orElseThrow(()-> new ResourceNoFoundException("Accouts",accountDto.getAccountId().toString(),"AccoutsNumber"));
        Accounts accounts= AccountMapper.AccountDtoToAccount(accountDto);
        accounts.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        accounts.setUpdatedBy("PTD-PTIT");
        accounts.setEnable(oldAccouts.getEnable());
        accountRepository.save(accounts);
        return false;
    }

    @Override
    public boolean deleteAccount(Long accountNumber) {
      Accounts accounts= accountRepository.findById(accountNumber).orElseThrow(()-> new ResourceNoFoundException("Accouts",accountNumber.toString(),"AccoutsNumber"));
      accounts.setEnable(false);
      accountRepository.save(accounts);
      return true;
    }
}
