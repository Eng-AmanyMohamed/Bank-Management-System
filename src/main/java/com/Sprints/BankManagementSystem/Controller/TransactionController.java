package com.Sprints.BankManagementSystem.Controller;

import com.Sprints.BankManagementSystem.DTO.TransactionDto;
import com.Sprints.BankManagementSystem.Service.Transaction.ITransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final ITransactionService transactionService;

    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@Valid @RequestBody TransactionDto dto) {
        return ResponseEntity.ok(transactionService.createTransaction(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id,
                                                            @Valid @RequestBody TransactionDto dto) {
        return ResponseEntity.ok(transactionService.updateTransaction(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<TransactionDto>> findByType(@PathVariable String type) {
        return ResponseEntity.ok(transactionService.findByType(type));
    }

    @GetMapping("/amountGreater/{amount}")
    public ResponseEntity<List<TransactionDto>> findByAmountGreater(@PathVariable Double amount) {
        return ResponseEntity.ok(transactionService.findByAmountGreaterThan(amount));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<TransactionDto>> findByDateRange(@RequestParam LocalDateTime start,
                                                                @RequestParam LocalDateTime end) {
        return ResponseEntity.ok(transactionService.findByTimestampBetween(start, end));
    }

    @GetMapping("/amount-range")
    public ResponseEntity<List<TransactionDto>> findByAmountRange(@RequestParam Double min,
                                                                  @RequestParam Double max) {
        return ResponseEntity.ok(transactionService.findTransactionsInAmountRange(min, max));
    }
}
