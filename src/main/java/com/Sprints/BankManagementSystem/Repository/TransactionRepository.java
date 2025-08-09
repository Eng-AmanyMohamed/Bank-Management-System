package com.Sprints.BankManagementSystem.Repository;

import com.Sprints.BankManagementSystem.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByType(String type);
    List<Transaction> findByAmountGreaterThan(Double amount);
    List<Transaction> findByTimestampBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT t FROM Transaction t WHERE t.amount BETWEEN :min AND :max")
    List<Transaction> findTransactionsInAmountRange(Double min, Double max);

    @Modifying
    @Query("DELETE FROM Transaction t WHERE t.sender.id = :accountId OR t.receiver.id = :accountId")
    int deleteTransactionsByAccountId(Long accountId);
}
