package com.Sprints.BankManagementSystem.Service.Customer;

import com.Sprints.BankManagementSystem.DTO.CustomerDto;

import java.util.List;

public interface ICustomerService {

    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto getCustomerById(Long id);
    List<CustomerDto> getAllCustomers();
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);
    void deleteCustomer(Long id);
    CustomerDto findCustomerByEmail(String email);
}
