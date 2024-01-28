package com.micro.accountservice.service;

import com.micro.accountservice.dto.CustomerDto;

public interface AccountService {

    /**
     *
     * @param customerDto {@link CustomerDto}
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
