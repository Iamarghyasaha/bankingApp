package com.ndb.bankingApp.service;

import com.ndb.bankingApp.dto.AccountDto;

public interface AccountService {
   AccountDto createAccount(AccountDto accountDto);
   AccountDto getsAccountById(Long id);
   AccountDto updateAccount(AccountDto accountDto);
   AccountDto deposit(Long id,double amount);
   AccountDto withdraw(Long id,double amount);
}
