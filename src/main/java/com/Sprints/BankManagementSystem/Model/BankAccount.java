package com.Sprints.BankManagementSystem.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long account_id;
    @Column(nullable = false, unique = true)
    private int account_number;
    @Column(name = "account_type",nullable = false)
    private String accountType;
    private double balance;
    @CreationTimestamp
    @Column( updatable = false)
    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "sender",cascade = CascadeType.ALL)
    private List<Transaction> sentTransactions;
    
    @OneToMany(mappedBy = "receiver",cascade = CascadeType.ALL)
    private List<Transaction> receivedTransactions;


    public BankAccount() {
    }

    public void setSentTransactions(List<Transaction> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }

    public void setReceivedTransactions(List<Transaction> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public void setAccount_type(String account_type) {
        this.accountType = account_type;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public int getAccount_number() {
        return account_number;
    }

    public String getAccount_type() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Transaction> getSentTransactions() {
        return sentTransactions;
    }

    public List<Transaction> getReceivedTransactions() {
        return receivedTransactions;
    }
}
