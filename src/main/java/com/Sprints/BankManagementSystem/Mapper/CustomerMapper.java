package com.Sprints.BankManagementSystem.Mapper;
import com.Sprints.BankManagementSystem.DTO.CustomerDto;
import com.Sprints.BankManagementSystem.Model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

    @Component
    public class CustomerMapper {
        @Autowired
        private ModelMapper modelMapper;


        public CustomerDto toDTO(Customer customer) {
            return modelMapper.map(customer, CustomerDto.class);
        }

        public Customer toEntity(CustomerDto customerDto) {
            return modelMapper.map(customerDto, Customer.class);
        }
    }

