package com.ndb.bankingApp.repository;


import com.ndb.bankingApp.dao.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
