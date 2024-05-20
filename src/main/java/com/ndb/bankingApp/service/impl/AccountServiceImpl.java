package com.ndb.bankingApp.service.impl;
import com.ndb.bankingApp.dto.AccountDto;
import com.ndb.bankingApp.entity.Account;
import com.ndb.bankingApp.mappers.AccountMapper;
import com.ndb.bankingApp.repository.AccountRepository;
import com.ndb.bankingApp.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;


@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository theAccountRepository){
        accountRepository = theAccountRepository;
    }

    @Override
    @Transactional
    public AccountDto createAccount(AccountDto accountDto) {
       Account account = AccountMapper.mapToAccount(accountDto);
       Account savedAccount = accountRepository.save(account);
       return AccountMapper.mapToAccountDto(savedAccount);
    }
    @Override
    public AccountDto getsAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    @Transactional
    public AccountDto updateAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account updatedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(updatedAccount);
    }

    @Override
    @Transactional
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exist"));
        double currentAmount = account.getBalance();
        double newBalance = currentAmount + amount;
        account.setBalance(newBalance);
        accountRepository.save(account);
        return AccountMapper.mapToAccountDto(account);
    }


}
