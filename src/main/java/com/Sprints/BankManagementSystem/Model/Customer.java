package com.Sprints.BankManagementSystem.Model;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long customer_id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(unique = true)
    private String phone;
    private String address;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<BankAccount>accounts;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Transaction>transactions;

    public Customer() {
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }
    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
