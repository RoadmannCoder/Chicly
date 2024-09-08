package com.chickly.BussinesLayer;

import com.chickly.DataAccessLayer.Entities.Account;
import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Repository.AdminRepository;
import com.chickly.DataAccessLayer.Repository.CustomerRepository;

import java.util.Optional;

public class AuthenticationService {

    private final CustomerRepository customerRepository = new CustomerRepository();
    private final AdminRepository adminRepository = new AdminRepository();

    public Customer isAuthenticatedCustomer(String userName, String password) {
        Customer customer = customerRepository.findyByUser(userName);

        return Optional.ofNullable(customer)
                .map(Customer::getAccount)
                .map(Account::getPassword)
                .filter(p -> p.equals(password))
                .map(p -> customer)  // Return the customer if the password matches
                .orElse(customer);

    }
    public boolean isAuthenticatedAdmin(String userName, String password) {
        return adminRepository.checkUserNameAndPasswordAreValid(userName, password);
    }



}
