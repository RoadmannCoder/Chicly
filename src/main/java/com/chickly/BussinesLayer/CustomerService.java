package com.chickly.BussinesLayer;

import com.chickly.DTO.CustomerRegistrationDTO;
import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Repository.CustomerRepository;
import com.chickly.Mappers.CustomerMapper;
import com.chickly.DTO.CustomerViewDTO;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

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
    public List<CustomerViewDTO> getAllCustomers(){
        Optional<List<Customer>> customerEntity = customerRepository.findAll();
        List<CustomerViewDTO> customers = CustomerMapper.fromEntityToCustomerViewDTO(customerEntity);
        return customers;
    }

    public CustomerViewDTO getCustomerByUserName(String userName) {
        Customer customer= customerRepository.findyByUser(userName);
        CustomerViewDTO customerViewDTO = CustomerViewDTO.fromCustomer(customer);
        return customerViewDTO;
    }
    public CustomerViewDTO getCustomerByUserNameEmailID(String userName, String email, String Id) {
        Customer customer = customerRepository.findUserByCriteria(userName, email, Id);
        if (customer == null) {
            return null;
        }

        CustomerViewDTO customerViewDTO = CustomerViewDTO.fromCustomer(customer);

        return customerViewDTO;
    }
}
