package com.ndb.bankingApp.service.impl;
import com.ndb.bankingApp.dto.AccountDto;
import com.ndb.bankingApp.dto.TransactionDto;
import com.ndb.bankingApp.entity.Account;
import com.ndb.bankingApp.entity.Transaction;
import com.ndb.bankingApp.entity.TransactionType;
import com.ndb.bankingApp.mappers.AccountMapper;
import com.ndb.bankingApp.mappers.TransactionMapper;
import com.ndb.bankingApp.repository.AccountRepository;
import com.ndb.bankingApp.repository.TransactionRepository;
import com.ndb.bankingApp.service.AccountService;
import com.ndb.bankingApp.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }


    @Override
    @Transactional
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account doesn't exist"));
        double currentAmount = account.getBalance();
        double newBalance = currentAmount + amount;
        account.setBalance(newBalance);

        // Create and save the transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());

        // Save the transaction
        transactionRepository.save(transaction);

        // Update the account
        accountRepository.save(account);

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    @Transactional
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account doesn't exist"));
        double currentBalance = account.getBalance();
        if (currentBalance >= amount) {
            double newBalance = currentBalance - amount;
            account.setBalance(newBalance);

            // Create and save the transaction
            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setTransactionType(TransactionType.WITHDRAWAL);
            transaction.setAmount(amount);
            transaction.setTransactionDate(LocalDateTime.now());

            // Save the transaction
            transactionRepository.save(transaction);

            // Update the account
            accountRepository.save(account);

            return AccountMapper.mapToAccountDto(account);
        } else {
            throw new RuntimeException("Your account doesn't have sufficient balance");
        }
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


    @Override
    public List<TransactionDto> getTransactionHistory(Long accountId) {
        // Retrieve the account from the database
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Get the list of transactions associated with the account
        List<Transaction> transactions = account.getTransactions();

        // Convert transactions to DTOs if needed
        return transactions.stream()
                .map(TransactionMapper::mapToTransactionDto)
                .collect(Collectors.toList());
    }


}
