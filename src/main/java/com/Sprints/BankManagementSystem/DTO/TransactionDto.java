package com.Sprints.BankManagementSystem.DTO;

import java.time.LocalDateTime;

public class TransactionDto {
    private String type;
    private Double amount;
    private LocalDateTime timestamp;
    private Long senderAccountId;
    private Long receiverAccountId;
    private Long customer_id;
    public TransactionDto() {
    }

    public TransactionDto(String type, Double amount, LocalDateTime timestamp, Long senderAccountId, Long receiverAccountId,Long customer_id) {

        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.customer_id = customer_id;
    }

    public void setCustomer_id(Long customer_id) {this.customer_id = customer_id;}

    public void setSenderAccountId(Long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public void setReceiverAccountId(Long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
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

    public Long getCustomer_id() {return customer_id;}
}
