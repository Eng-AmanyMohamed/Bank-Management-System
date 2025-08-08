package com.Sprints.BankManagementSystem.DTO;

public class CustomerDto {
    private Long customer_id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;

    public CustomerDto() {
    }

    public CustomerDto(Long id, String name, String email, String password, String phone, String address) {
        this.customer_id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public void setId(Long id) {
        this.customer_id = id;
    }
    public void setPassword(String password) {
        this.password = password;
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

    public Long getId() {
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
}

