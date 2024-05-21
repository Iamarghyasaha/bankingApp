package com.ndb.bankingApp.dto;

import com.ndb.bankingApp.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long id;
    private TransactionType transactionType;
    private double amount;
    private LocalDateTime transactionDate;
    private Long accountId;
}

