package com.Sprints.BankManagementSystem.Service.BankAccount;

import java.util.List;
import com.Sprints.BankManagementSystem.DTO.BankAccountDto;
import com.Sprints.BankManagementSystem.DTO.TransferDto;

public interface IBankAccountService {
    BankAccountDto createBankAccount(BankAccountDto bankAccountDto);
    BankAccountDto getAccountById(Long accountId);
    List<BankAccountDto> getAccountsByMinBalance(Double minBalance);
    BankAccountDto deposit(Long accountId, Double amount);
    BankAccountDto withdraw(Long accountId, Double amount);
    BankAccountDto transfer(TransferDto transferDto);
    List<BankAccountDto> findAccountsInRange(Double min, Double max);
}
