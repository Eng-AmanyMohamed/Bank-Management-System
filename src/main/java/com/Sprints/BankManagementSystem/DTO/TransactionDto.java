package com.Sprints.BankManagementSystem.DTO;

import java.time.LocalDateTime;

public class TransactionDto {
    private Long transaction_id;
    private String type;
    private Double amount;
    private LocalDateTime timestamp;
    private Long senderAccountId;
    private Long receiverAccountId;

    public TransactionDto() {
    }

    public TransactionDto(Long transaction_id, String type, Double amount, LocalDateTime timestamp, Long senderAccountId, Long receiverAccountId) {
        this.transaction_id = transaction_id;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
    }

    public void setSenderAccountId(Long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public void setReceiverAccountId(Long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTransaction_id() {
        return transaction_id;
    }

    public String getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Long getSenderAccountId() {
        return senderAccountId;
    }

    public Long getReceiverAccountId() {
        return receiverAccountId;
    }
}
