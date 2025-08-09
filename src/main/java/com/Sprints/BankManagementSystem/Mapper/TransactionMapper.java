package com.Sprints.BankManagementSystem.Mapper;

import com.Sprints.BankManagementSystem.DTO.TransactionDto;
import com.Sprints.BankManagementSystem.Model.Transaction;

public class TransactionMapper {

    public static TransactionDto toDTO(Transaction transaction) {
        return new TransactionDto(
                transaction.getType(),
                transaction.getAmount(),
                transaction.getTimestamp(),
                transaction.getSender() != null ? transaction.getSender().getAccount_id() : null,
                transaction.getReceiver() != null ? transaction.getReceiver().getAccount_id() : null,
                transaction.getCustomer() != null ? transaction.getCustomer().getCustomer_id() : null
        );
    }

    public static Transaction toEntity(TransactionDto dto) {
        Transaction transaction = new Transaction();
        transaction.setType(dto.getType());
        transaction.setAmount(dto.getAmount());
        transaction.setTimestamp(dto.getTimestamp());
        return transaction;
    }
}
