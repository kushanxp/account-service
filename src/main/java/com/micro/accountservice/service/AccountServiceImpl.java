package com.micro.accountservice.service;

import com.micro.accountservice.constants.AccountConstants;
import com.micro.accountservice.dto.AccountDto;
import com.micro.accountservice.dto.CustomerDto;
import com.micro.accountservice.entity.Account;
import com.micro.accountservice.entity.Customer;
import com.micro.accountservice.exception.CustomerAlreadyExistsException;
import com.micro.accountservice.exception.ResourceNotFoundException;
import com.micro.accountservice.mapper.AccountMapper;
import com.micro.accountservice.mapper.CustomerMapper;
import com.micro.accountservice.repository.AccountRepository;
import com.micro.accountservice.repository.CustomerRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private CustomerRespository customerRespository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        Optional<Customer> optionalCustomer = customerRespository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists for the give mobile number");
        }

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Kushan");
        Customer savedCustomer = customerRespository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRespository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber",mobileNumber)
        );

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountMapper.mapToAccountsDto(account, new AccountDto()));
        return customerDto;
    }

    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setCreatedAt(customer.getCreatedAt());
        newAccount.setCreatedBy(customer.getCreatedBy());
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        return newAccount;
    }
}
