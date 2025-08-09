package com.Sprints.BankManagementSystem.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long transaction_id;
    private String type;     //deposit, withdrawal,transfer
    private Double amount;
    private LocalDateTime timestamp;


    @ManyToOne
    @JoinColumn(name = "sender_account_id")
    private BankAccount sender;

    @ManyToOne
    @JoinColumn(name = "receiver_account_id")
    private BankAccount receiver;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Transaction() {
    }


    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public void setSender(BankAccount sender) {
        this.sender = sender;
    }

    public void setReceiver(BankAccount receiver) {
        this.receiver = receiver;
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

    public BankAccount getSender() {
        return sender;
    }

    public BankAccount getReceiver() {
        return receiver;
    }

    public Customer getCustomer() {
        return customer;
    }
}
