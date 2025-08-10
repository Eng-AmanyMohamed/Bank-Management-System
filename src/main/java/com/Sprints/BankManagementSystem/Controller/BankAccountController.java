package com.Sprints.BankManagementSystem.Controller;


import com.Sprints.BankManagementSystem.DTO.BankAccountDto;
import com.Sprints.BankManagementSystem.DTO.TransactionDto;
import com.Sprints.BankManagementSystem.DTO.TransferDto;
import com.Sprints.BankManagementSystem.Model.BankAccount;
import com.Sprints.BankManagementSystem.Service.BankAccount.BankAccountService;
import com.Sprints.BankManagementSystem.Service.BankAccount.IBankAccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/account")
public class BankAccountController {
    private final IBankAccountService bankAccountService;

    public BankAccountController(IBankAccountService bankAccountService) {
        this.bankAccountService=bankAccountService;
    }

    @PostMapping
    public ResponseEntity<BankAccountDto> createAccount(@Valid @RequestBody BankAccountDto bankAccountDto){
        return ResponseEntity.ok(bankAccountService.createBankAccount(bankAccountDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountDto> getAccountById(@PathVariable Long id){
        return ResponseEntity.ok(bankAccountService.getAccountById(id));
    }

    @GetMapping
    public ResponseEntity<List<BankAccountDto>> getAccountsByMinBalance(@RequestParam Double minBalance){
        return ResponseEntity.ok(bankAccountService.getAccountsByMinBalance(minBalance));
    }

    @PostMapping("/deposit/{id}")
    public ResponseEntity<BankAccountDto> deposit(@PathVariable Long id,@Valid @RequestBody double amount ){
        return ResponseEntity.ok(bankAccountService.deposit(id,amount));
    }

    @PostMapping("/withdraw/{id}")
    public ResponseEntity<BankAccountDto> withdraw(@PathVariable Long id,@Valid @RequestBody double amount ){
        return ResponseEntity.ok(bankAccountService.withdraw(id,amount));
    }

    @PostMapping("/transfer")
    public ResponseEntity<BankAccountDto> transfer(@RequestBody TransferDto transferDto) {
        return ResponseEntity.ok(bankAccountService.transfer(transferDto));
    }

 @GetMapping("/range")
     public ResponseEntity<List<BankAccountDto>> getAccountsInRange(@RequestParam Double min,@RequestParam Double max){
         return ResponseEntity.ok(bankAccountService.findAccountsInRange(min,max));
     }


}
