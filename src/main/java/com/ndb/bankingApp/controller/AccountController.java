package com.ndb.bankingApp.controller;

import com.ndb.bankingApp.dto.AccountDto;
import com.ndb.bankingApp.dto.TransactionDto;
import com.ndb.bankingApp.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Account", description = "Account management APIs")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService theAccountService) {
        accountService = theAccountService;
    }

    @Operation(summary = "Create a new account")
    @PostMapping("/createAccount")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        accountDto.setId(0L);
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get account by ID")
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
    }

    @Operation(summary = "Update account details")
    @PutMapping()
    public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.updateAccount(accountDto), HttpStatus.OK);
    }

    @Operation(summary = "Deposit amount")
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @Operation(summary = "Withdraw amount")
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @Operation(summary = "Get all accounts")
    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @Operation(summary = "Delete account by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Deleted successfully account id: " + id);
    }

    @Operation(summary = "Get transaction history for account")
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDto>> getTransactionHistory(@PathVariable Long id) {
        List<TransactionDto> transactions = accountService.getTransactionHistory(id);
        return ResponseEntity.ok(transactions);
    }
}
