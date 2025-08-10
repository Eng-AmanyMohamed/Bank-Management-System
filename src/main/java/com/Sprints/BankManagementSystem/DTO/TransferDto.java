package com.Sprints.BankManagementSystem.DTO;

import jakarta.validation.constraints.*;

public class TransferDto {
        private Long senderAccountId;

        private Long receiverAccountId;
        @Min(1)
        private Double amount;


        public Long getSenderAccountId() {
            return senderAccountId;
        }
        public void setSenderAccountId(Long senderAccountId) {
            this.senderAccountId = senderAccountId;
        }
        public Long getReceiverAccountId() {
            return receiverAccountId;
        }
        public void setReceiverAccountId(Long receiverAccountId) {
            this.receiverAccountId = receiverAccountId;
        }
        public Double getAmount() {
            return amount;
        }
        public void setAmount(Double amount) {
            this.amount = amount;
        }
    }


