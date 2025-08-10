
package com.Sprints.BankManagementSystem.Controller;


import com.Sprints.BankManagementSystem.DTO.BankAccountDto;
import com.Sprints.BankManagementSystem.Service.BankAccount.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/account")
    public BankAccountDto createBankAccount(@RequestBody BankAccountDto bankAccountDto) {
        return bankAccountService.createBankAccount(bankAccountDto);

    }

    @GetMapping("/account/{account_id}")
    public BankAccountDto getBankAccount(@PathVariable("account_id") Long accountId) {
        return bankAccountService.getAccountById(accountId);
    }
}