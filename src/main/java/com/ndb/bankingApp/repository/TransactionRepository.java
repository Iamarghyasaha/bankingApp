package com.ndb.bankingApp.repository;

import com.ndb.bankingApp.dao.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
