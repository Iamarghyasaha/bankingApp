package com.ndb.bankingApp.service;

import com.ndb.bankingApp.dao.Transaction;
import com.ndb.bankingApp.dao.TransactionType;

public interface TransactionService {
    Transaction createTransaction(Long accountId, TransactionType transactionType, double amount);

}
