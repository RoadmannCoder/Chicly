package com.chickly.BussinesLayer;

import com.chickly.DTO.CustomerRegistrationDTO;
import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Repository.CustomerRepository;
import com.chickly.Mappers.CustomerMapper;

import java.text.ParseException;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }


    public boolean authenticateUser(String username, String password) {
        return customerRepository.checkUsernameAndPasswordAreValid(username, password);
    }
    public boolean usernameValidation(String userName) {
        return customerRepository.checkUsernameIfFound(userName);
    }
    public boolean phoneNumberValidation(String phoneNumber) {
        return customerRepository.checkPhoneNumberIfFound(phoneNumber);
    }
    public boolean emailValidation(String email) {
        return customerRepository.checkEmailIfFound(email);
    }
    public void createCustomer(CustomerRegistrationDTO customerDTO) throws ParseException {
        Customer customer= CustomerMapper.fromCustomerRegistrationDTOToEntity(customerDTO);
        customerRepository.create(customer);
    }
}
