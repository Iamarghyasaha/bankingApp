package com.ndb.bankingApp.service.impl;

import com.ndb.bankingApp.entity.Account;
import com.ndb.bankingApp.entity.Transaction;
import com.ndb.bankingApp.entity.TransactionType;
import com.ndb.bankingApp.repository.AccountRepository;
import com.ndb.bankingApp.repository.TransactionRepository;
import com.ndb.bankingApp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Transaction> getTransactionHistory(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    @Override
    public Transaction createTransaction(Long accountId, TransactionType transactionType, double amount) {
        // Retrieve the Account
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Create the Transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setTransactionType(transactionType);
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());

        // Save the Transaction
        return transactionRepository.save(transaction);
    }
}