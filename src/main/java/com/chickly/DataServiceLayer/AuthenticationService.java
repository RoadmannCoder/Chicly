package com.chickly.DataServiceLayer;

import com.chickly.DataAccessLayer.Repository.AdminRepository;
import com.chickly.DataAccessLayer.Repository.CustomerRepository;

public class AuthenticationService {

    private CustomerRepository customerRepository;
    private AdminRepository adminRepository;

    public boolean isAuthenticatedCustomer(String userName, String password) {
        return customerRepository.checkUserNameAndPasswordAreValid(userName, password);
    }
    public boolean isAuthenticatedAdmin(String userName, String password) {
        return adminRepository.checkUserNameAndPasswordAreValid(userName, password);
    }

}
