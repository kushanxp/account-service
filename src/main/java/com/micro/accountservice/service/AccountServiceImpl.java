package com.micro.accountservice.service;

import com.micro.accountservice.dto.CustomerDto;
import com.micro.accountservice.entity.Customer;
import com.micro.accountservice.mapper.CustomerMapper;
import com.micro.accountservice.repository.AccountRepository;
import com.micro.accountservice.repository.CustomerRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private CustomerRespository customerRespository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Customer savedCustomer = customerRespository.save(customer);
    }
}
