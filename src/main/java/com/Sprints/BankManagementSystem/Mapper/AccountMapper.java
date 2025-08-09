package com.Sprints.BankManagementSystem.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.Sprints.BankManagementSystem.DTO.BankAccountDto;
import com.Sprints.BankManagementSystem.Model.BankAccount;

@Component
public class AccountMapper {
    @Autowired
    private ModelMapper modelMapper;

    public BankAccountDto toDTO(BankAccount bankAccount) {
        return modelMapper.map(bankAccount, BankAccountDto.class);
    }

    public BankAccount toEntity(BankAccountDto bankAccountDto) {
        return modelMapper.map(bankAccountDto, BankAccount.class);
    }
}
