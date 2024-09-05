package com.chickly.DataServiceLayer.ServiceImpl;

import com.chickly.DataAccessLayer.Repository.CustomerRepository;
import com.chickly.DataServiceLayer.ServiceInterface.CustomerService;
import jakarta.persistence.EntityManager;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(EntityManager entityManager) {
        this.customerRepository = new CustomerRepository(entityManager);
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        return customerRepository.checkEmailAndPasswordAreValid(email, password);
    }
}
