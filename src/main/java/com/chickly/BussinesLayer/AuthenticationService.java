package com.chickly.BussinesLayer;

import com.chickly.DataAccessLayer.Entities.Account;
import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Repository.AdminRepository;
import com.chickly.DataAccessLayer.Repository.CustomerRepository;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

public class AuthenticationService {

    private final CustomerRepository customerRepository = new CustomerRepository();
    private final AdminRepository adminRepository = new AdminRepository();

    public Customer isAuthenticatedCustomer(String userName, String password) {
        Customer customer = customerRepository.findyByUser(userName);
        if(customer==null)
            return null;
        System.out.println(userName+"   "+password);
        return password.equals(customer.getAccount().getPassword())?customer:null;
    }
    public boolean isAuthenticatedAdmin(String userName, String password) {
        return adminRepository.checkUserNameAndPasswordAreValid(userName, password);
    }
    private String hashFunction(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(hash);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }



}
