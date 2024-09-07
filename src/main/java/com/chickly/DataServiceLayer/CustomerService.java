package com.chickly.DataServiceLayer.ServiceImpl;

import com.chickly.DataAccessLayer.Repository.CustomerRepository;
import com.chickly.DataServiceLayer.ServiceInterface.CustomerService;
import jakarta.persistence.EntityManager;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl() {
        this.customerRepository = new CustomerRepository();
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        return customerRepository.checkUsernameAndPasswordAreValid(username, password);
    }
}
