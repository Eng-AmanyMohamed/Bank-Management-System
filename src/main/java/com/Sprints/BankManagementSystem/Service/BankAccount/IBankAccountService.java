package com.Sprints.BankManagementSystem.Service.BankAccount;

import java.util.List;
import com.Sprints.BankManagementSystem.DTO.BankAccountDto;

public interface IBankAccountService {
    BankAccountDto createBankAccount(BankAccountDto bankAccountDto);
    BankAccountDto getAccountById(Long accountId);
    List<BankAccountDto> getAccountsByMinBalance(Double minBalance);
    BankAccountDto deposit(Long accountId, Double amount);
    BankAccountDto withdraw(Long accountId, Double amount);
    void transfer(Long senderAccountId, Long receiverAccountId, Double amount);
}
