package com.Sprints.BankManagementSystem.Service.Transaction;

import com.Sprints.BankManagementSystem.DTO.TransactionDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ITransactionService {
    TransactionDto createTransaction(TransactionDto transactionDto);
    TransactionDto getTransactionById(Long id);
    List<TransactionDto> getAllTransactions();
    TransactionDto updateTransaction(Long id, TransactionDto transactionDto);
    void deleteTransaction(Long id);

    List<TransactionDto> findByType(String type);
    List<TransactionDto> findByAmountGreaterThan(Double amount);
    List<TransactionDto> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    List<TransactionDto> findTransactionsInAmountRange(Double min, Double max);
}
