package com.Sprints.BankManagementSystem.Service.Transaction;

import com.Sprints.BankManagementSystem.DTO.TransactionDto;
import com.Sprints.BankManagementSystem.Mapper.TransactionMapper;
import com.Sprints.BankManagementSystem.Model.BankAccount;
import com.Sprints.BankManagementSystem.Model.Customer;
import com.Sprints.BankManagementSystem.Model.Transaction;
import com.Sprints.BankManagementSystem.Repository.BankAccountRepository;
import com.Sprints.BankManagementSystem.Repository.CustomerRepository;
import com.Sprints.BankManagementSystem.Repository.TransactionRepository;
import com.Sprints.BankManagementSystem.exception.DataNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              BankAccountRepository bankAccountRepository,
                              CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public TransactionDto createTransaction(TransactionDto dto) {
        Transaction transaction = TransactionMapper.toEntity(dto);

        if (dto.getSenderAccountId() != null) {
            BankAccount sender = bankAccountRepository.findById(dto.getSenderAccountId())
                    .orElseThrow(() -> new DataNotFoundException("Sender account not found"));
            transaction.setSender(sender);
        }

        if (dto.getReceiverAccountId() != null) {
            BankAccount receiver = bankAccountRepository.findById(dto.getReceiverAccountId())
                    .orElseThrow(() -> new DataNotFoundException("Receiver account not found"));
            transaction.setReceiver(receiver);
        }

        if (dto.getCustomer_id() != null) {
            Customer customer = customerRepository.findById(dto.getCustomer_id())
                    .orElseThrow(() -> new DataNotFoundException("Customer not found"));
            transaction.setCustomer(customer);
        }

        transaction.setTimestamp(LocalDateTime.now());
        Transaction saved = transactionRepository.save(transaction);
        return TransactionMapper.toDTO(saved);
    }

    @Override
    public TransactionDto getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Transaction not found with id " + id));
        return TransactionMapper.toDTO(transaction);
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(TransactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TransactionDto updateTransaction(Long id, TransactionDto dto) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Transaction not found with id " + id));

        transaction.setType(dto.getType());
        transaction.setAmount(dto.getAmount());
        transaction.setTimestamp(dto.getTimestamp());

        if (dto.getSenderAccountId() != null) {
            transaction.setSender(bankAccountRepository.findById(dto.getSenderAccountId())
                    .orElseThrow(() -> new DataNotFoundException("Sender account not found")));
        }
        if (dto.getReceiverAccountId() != null) {
            transaction.setReceiver(bankAccountRepository.findById(dto.getReceiverAccountId())
                    .orElseThrow(() -> new DataNotFoundException("Receiver account not found")));
        }
        if (dto.getCustomer_id() != null) {
            transaction.setCustomer(customerRepository.findById(dto.getCustomer_id())
                    .orElseThrow(() -> new DataNotFoundException("Customer not found")));
        }

        Transaction updated = transactionRepository.save(transaction);
        return TransactionMapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new DataNotFoundException("Transaction not found with id " + id);
        }
        transactionRepository.deleteById(id);
    }

    @Override
    public List<TransactionDto> findByType(String type) {
        return transactionRepository.findByType(type)
                .stream().map(TransactionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> findByAmountGreaterThan(Double amount) {
        return transactionRepository.findByAmountGreaterThan(amount)
                .stream().map(TransactionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> findByTimestampBetween(LocalDateTime start, LocalDateTime end) {
        return transactionRepository.findByTimestampBetween(start, end)
                .stream().map(TransactionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> findTransactionsInAmountRange(Double min, Double max) {
        return transactionRepository.findTransactionsInAmountRange(min, max)
                .stream().map(TransactionMapper::toDTO).collect(Collectors.toList());
    }
}
