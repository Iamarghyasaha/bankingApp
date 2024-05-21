package com.ndb.bankingApp.mappers;

import com.ndb.bankingApp.dto.AccountDto;
import com.ndb.bankingApp.entity.Account;

import java.util.Optional;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance(),
                null
        );
        return account;
    }
    public static AccountDto mapToAccountDto( Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;

    }
}
