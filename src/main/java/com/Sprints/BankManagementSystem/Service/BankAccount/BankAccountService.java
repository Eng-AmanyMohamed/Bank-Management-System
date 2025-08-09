package com.Sprints.BankManagementSystem.Service.BankAccount;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.Sprints.BankManagementSystem.DTO.BankAccountDto;
import com.Sprints.BankManagementSystem.Mapper.AccountMapper;
import com.Sprints.BankManagementSystem.Model.BankAccount;
import com.Sprints.BankManagementSystem.Repository.BankAccountRepository;

import jakarta.transaction.Transactional;

@Service
public class BankAccountService implements IBankAccountService{
    private final BankAccountRepository bankAccountRepository;
    private final AccountMapper AccountMapper;

    public BankAccountService(BankAccountRepository bankAccountRepository, AccountMapper bankAccountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.AccountMapper = bankAccountMapper;
    }

    @Override
    public BankAccountDto createBankAccount(BankAccountDto bankAccountDto) {
        BankAccount account = AccountMapper.toEntity(bankAccountDto);
        BankAccount saved = bankAccountRepository.save(account);
        return AccountMapper.toDTO(saved);
    }

     @Override
    public BankAccountDto getAccountById(Long accountId) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with id " + accountId));
        return AccountMapper.toDTO(account);
    }

    @Override
    public List<BankAccountDto> getAccountsByMinBalance(Double minBalance) {
        if (minBalance == null) {
            return bankAccountRepository.findAll()
                    .stream()
                    .map(AccountMapper::toDTO)
                    .collect(Collectors.toList());
        }
        return bankAccountRepository.findByBalanceGreaterThan(minBalance)
                .stream()
                .map(AccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BankAccountDto deposit(Long accountId, Double amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with id " + accountId));

        Double newBalance = account.getBalance() + amount; // account.getBalance() is primitive double, auto-boxed
        int updated = bankAccountRepository.updateAccountBalance(accountId, newBalance);
        if (updated == 0) {
            throw new RuntimeException("Failed to update balance for account " + accountId);
        }

        BankAccount updatedAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found after update " + accountId));
        return AccountMapper.toDTO(updatedAccount);
    }

    @Override
    @Transactional
    public BankAccountDto withdraw(Long accountId, Double amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive");
        }

        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with id " + accountId));

        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        Double newBalance = account.getBalance() - amount;
        int updated = bankAccountRepository.updateAccountBalance(accountId, newBalance);
        if (updated == 0) {
            throw new RuntimeException("Failed to update balance for account " + accountId);
        }

        BankAccount updatedAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found after update " + accountId));
        return AccountMapper.toDTO(updatedAccount);
    }

    @Override
    @Transactional
    public void transfer(Long senderAccountId, Long receiverAccountId, Double amount) {
        if (senderAccountId.equals(receiverAccountId)) {
            throw new IllegalArgumentException("Sender and receiver must be different accounts");
        }
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        BankAccount sender = bankAccountRepository.findById(senderAccountId)
                .orElseThrow(() -> new RuntimeException("Sender account not found: " + senderAccountId));
        BankAccount receiver = bankAccountRepository.findById(receiverAccountId)
                .orElseThrow(() -> new RuntimeException("Receiver account not found: " + receiverAccountId));

        if (sender.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds in sender account");
        }

        Double newSenderBalance = sender.getBalance() - amount;
        Double newReceiverBalance = receiver.getBalance() + amount;

        int u1 = bankAccountRepository.updateAccountBalance(senderAccountId, newSenderBalance);
        int u2 = bankAccountRepository.updateAccountBalance(receiverAccountId, newReceiverBalance);

        if (u1 == 0 || u2 == 0) {
            throw new RuntimeException("Failed to complete transfer (db update returned 0 rows)");
        }
    }
}
