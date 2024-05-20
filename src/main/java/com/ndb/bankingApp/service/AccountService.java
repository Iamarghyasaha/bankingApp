package com.ndb.bankingApp.service;

import com.ndb.bankingApp.dto.AccountDto;

import java.util.List;

public interface AccountService {
   AccountDto createAccount(AccountDto accountDto);
   AccountDto getAccountById(Long id);
   AccountDto updateAccount(AccountDto accountDto);
   AccountDto deposit(Long id,double amount);
   AccountDto withdraw(Long id,double amount);
   List<AccountDto> getAllAccounts();
   void deleteAccount(Long id);
}
