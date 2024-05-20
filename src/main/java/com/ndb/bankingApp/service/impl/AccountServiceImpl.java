package com.ndb.bankingApp.service.impl;
import com.ndb.bankingApp.dto.AccountDto;
import com.ndb.bankingApp.entity.Account;
import com.ndb.bankingApp.mappers.AccountMapper;
import com.ndb.bankingApp.repository.AccountRepository;
import com.ndb.bankingApp.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    public AccountDto getAccountById(Long id) {
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

    @Override
    @Transactional
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exist"));
        double currentBalance = account.getBalance();
        if(currentBalance>amount){
            double newBalance = currentBalance-amount;
            account.setBalance(newBalance);
        }
        else{
            throw new RuntimeException("Your account doesn't have sufficient balance");
        }
        accountRepository.save(account);
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map((account)->AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exist"));
        accountRepository.deleteById(id);
    }


}
