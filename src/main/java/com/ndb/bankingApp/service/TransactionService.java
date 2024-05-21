package com.ndb.bankingApp.service;

import com.ndb.bankingApp.entity.Transaction;
import com.ndb.bankingApp.entity.TransactionType;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Long accountId, TransactionType transactionType, double amount);

}
