package com.Sprints.BankManagementSystem.DTO;

import java.time.LocalDateTime;

public class BankAccountDto {

    private Long account_id;
    private int account_number;
    private String account_type;
    private double balance;
    private LocalDateTime created_at;
    private int customer_id;
    public BankAccountDto() {
    }

    public BankAccountDto(Long account_id, int account_number, String account_type, double balance, LocalDateTime created_at, int customer_id) {
        this.account_id = account_id;
        this.account_number = account_number;
        this.account_type = account_type;
        this.balance = balance;
        this.created_at = created_at;
        this.customer_id = customer_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public void setCustomer_id(int customer_name) {
        this.customer_id = customer_id;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
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
        return account_type;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public int getCustomer_id() {
        return customer_id;
    }
}
