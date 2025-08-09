package com.Sprints.BankManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Sprints.BankManagementSystem.Model.BankAccount;
import jakarta.transaction.Transactional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByAccountType(String account_type);
    List<BankAccount> findByBalanceGreaterThan(Double amount);
    List<BankAccount> findByBalanceLessThan(Double amount);
    @Query("SELECT a FROM BankAccount a WHERE a.balance BETWEEN :min AND :max")
    List<BankAccount> findAccountsInRange(Double min, Double max);

    @Modifying
    @Transactional
    @Query("UPDATE BankAccount a SET a.balance = :balance WHERE a.account_id = :accountId")
    int updateAccountBalance(Long accountId, Double balance);
}