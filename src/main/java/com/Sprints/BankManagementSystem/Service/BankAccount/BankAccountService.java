package com.Sprints.BankManagementSystem.Service.BankAccount;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.Sprints.BankManagementSystem.DTO.TransactionDto;

import com.Sprints.BankManagementSystem.DTO.TransferDto;
import com.Sprints.BankManagementSystem.Mapper.TransactionMapper;
import com.Sprints.BankManagementSystem.Model.Transaction;
import com.Sprints.BankManagementSystem.Service.Transaction.TransactionService;
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
    private final TransactionMapper TransactionMapper;
    private final TransactionService transactionService;

    public BankAccountService(BankAccountRepository bankAccountRepository, AccountMapper bankAccountMapper, TransactionService transactionService,TransactionMapper transactionMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.AccountMapper = bankAccountMapper;
        this.transactionService = transactionService;
        this.TransactionMapper = transactionMapper;
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
        account.setBalance(newBalance);
        bankAccountRepository.save(account);

        TransactionDto dto = new TransactionDto();
        dto.setCustomer_id(account.getCustomer().getCustomer_id());
        dto.setAmount(amount);
        dto.setType("deposit");
        dto.setTimestamp(LocalDateTime.now());
        transactionService.createTransaction(dto);

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

        TransactionDto dto = new TransactionDto();
        dto.setCustomer_id(account.getCustomer().getCustomer_id());
        dto.setAmount(amount);
        dto.setType("withdraw");
        dto.setTimestamp(LocalDateTime.now());
        transactionService.createTransaction(dto);

        BankAccount updatedAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found after update " + accountId));
        return AccountMapper.toDTO(updatedAccount);
    }


    @Override
    @Transactional
    public BankAccountDto transfer(TransferDto transferDto) {
        if (transferDto.getSenderAccountId().equals(transferDto.getReceiverAccountId())) {
            throw new IllegalArgumentException("Sender and receiver must be different accounts");
        }
        if (transferDto.getAmount() == null || transferDto.getAmount() <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        BankAccount sender = bankAccountRepository.findById(transferDto.getSenderAccountId())
                .orElseThrow(() -> new RuntimeException("Sender account not found: " + transferDto.getSenderAccountId()));
        BankAccount receiver = bankAccountRepository.findById(transferDto.getReceiverAccountId())
                .orElseThrow(() -> new RuntimeException("Receiver account not found: " + transferDto.getReceiverAccountId()));

        if (sender.getBalance() < transferDto.getAmount() ) {
            throw new IllegalArgumentException("Insufficient funds in sender account");
        }


        sender.setBalance(sender.getBalance() - transferDto.getAmount() );
        receiver.setBalance(receiver.getBalance() + transferDto.getAmount() );
        bankAccountRepository.save(sender);
        bankAccountRepository.save(receiver);




        TransactionDto dto = new TransactionDto();
        dto.setSenderAccountId(transferDto.getSenderAccountId());
        dto.setReceiverAccountId(transferDto.getReceiverAccountId());
        dto.setAmount(transferDto.getAmount());
        dto.setType("withdraw");
        dto.setTimestamp(LocalDateTime.now());
        transactionService.createTransaction(dto);

        BankAccount updatedAccountSender = bankAccountRepository.findById(sender.getAccount_id())
                .orElseThrow(() -> new RuntimeException("Account not found after update " + sender.getAccount_id()));

        updatedAccountSender.getSentTransactions().add(TransactionMapper.toEntity(dto));

        BankAccount updatedAccountReciever = bankAccountRepository.findById(receiver.getAccount_id())
                .orElseThrow(() -> new RuntimeException("Account not found after update " + receiver.getAccount_id()));

        updatedAccountReciever.getReceivedTransactions().add(TransactionMapper.toEntity(dto));




        return AccountMapper.toDTO(updatedAccountSender);
    }

    @Override
    public List<BankAccountDto> findAccountsInRange(Double min, Double max) {
        List<BankAccount> accounts = bankAccountRepository.findAccountsInRange(min, max);
        return accounts.stream()
                .map(AccountMapper::toDTO)
                .collect(Collectors.toList());
    }


}
