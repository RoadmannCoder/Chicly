package com.chickly.DataServiceLayer.ServiceImpl;

import com.chickly.DataAccessLayer.Repository.CustomerRepository;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }

    public boolean authenticateUser(String email, String password) {
        return customerRepository.checkEmailAndPasswordAreValid(email, password);
    }
}
