package com.ndb.bankingApp.controller;

import com.ndb.bankingApp.dto.AccountDto;
import com.ndb.bankingApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;
    @Autowired
    public AccountController (AccountService theAccountService){
        accountService = theAccountService;
    }

    //Add Account Rest API
    @PostMapping("/createAccount")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        accountDto.setId(0L);
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    // get single account
    //http://localhost:8080/api/accounts/1
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getsAccountById(id),HttpStatus.OK);
    }
    // update Account details
    //http://localhost:8080/api/accounts
    @PutMapping()
    public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.updateAccount(accountDto), HttpStatus.OK ) ;
    }

    // deposite ammount
    //http://localhost:8080/api/accounts/1/deposit
    // response body: json : {"amount":20000}
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit (@PathVariable Long id,
                                               @RequestBody Map<String, Double> request){
        //Map<key,value>
        //request.get("Object key")-> to get the value
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id,amount);
        return new ResponseEntity<>(accountDto,HttpStatus.OK);
    }

    // withdraw amount
    //http://localhost:8080/api/accounts/1/withdraw
    // response body: json : {"amount":20000}
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw (@PathVariable Long id,
                                               @RequestBody Map<String, Double> request){
        //Map<key,value>
        //request.get("Object key")-> to get the value
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return new ResponseEntity<>(accountDto,HttpStatus.OK);
    }



}
