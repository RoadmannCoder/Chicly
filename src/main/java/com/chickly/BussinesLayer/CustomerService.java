package com.chickly.BussinesLayer;

import com.chickly.DTO.CustomerRegistrationDTO;
import com.chickly.DataAccessLayer.Entities.Account;
import com.chickly.DataAccessLayer.Entities.Address;
import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Entities.Interest;
import com.chickly.DataAccessLayer.Repository.CustomerRepository;
import com.chickly.DataAccessLayer.Repository.InterestRepository;
import com.chickly.Mappers.CustomerMapper;
import com.chickly.DTO.CustomerViewDTO;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

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

    public Long getTotalCustomers() {
        CustomerRepository customerRepository1 = new CustomerRepository();
        return customerRepository1.countAllCustomers();
    }

    public void updateCustomer(HttpServletRequest req,Customer oldCustomer) {
        CustomerRepository customerRepository1 = new CustomerRepository();
        String firstName = req.getParameter("firstName");
        String lastName= req.getParameter("lastName");
        BigDecimal creditLimit = new BigDecimal(req.getParameter("creditLimit"));
        String email= req.getParameter("email");
        String phoneNumber= req.getParameter("phoneNumber");
        String job= req.getParameter("job");
        String street= req.getParameter("street");
        String city= req.getParameter("city");
        String zip= req.getParameter("zip");
        String description= req.getParameter("description");
        String userName = req.getParameter("userName");
        String[] selectedInterestIds = req.getParameterValues("interests");


        if(city==null || city.isEmpty()){
            city = oldCustomer.getAddress().getCity();
        }
        Account account = new Account();account.setUserName(userName);account.setPassword(oldCustomer.getAccount().getPassword());
        Address address = new Address();address.setCity(city);address.setZip(zip);address.setDescription(description);
        address.setStreet(street);
        Customer updatedCustomer = new Customer();
        updatedCustomer.setCreditLimit(creditLimit);updatedCustomer.setEmail(email);updatedCustomer.setJob(job);
        updatedCustomer.setDateOfBirth(oldCustomer.getDateOfBirth());updatedCustomer.setFirstName(firstName);
        updatedCustomer.setLastName(lastName);updatedCustomer.setPhoneNumber(phoneNumber);updatedCustomer.setAddress(address);
        updatedCustomer.setAccount(account);updatedCustomer.setId(oldCustomer.getId());
        Set<Interest> interests = new HashSet<>();
        if (selectedInterestIds != null) {
            for (String interestId : selectedInterestIds) {
                Interest interest = new InterestRepository().findBy("id",Integer.parseInt(interestId));  // Fetch from the database
                if (interest != null) {
                    interests.add(interest);
                }
            }
        }
        updatedCustomer.setInterests(interests);

        customerRepository1.updateCustomer(updatedCustomer);

    }

    public Customer getCustomerById(Integer id) {
        CustomerRepository customerRepository1 = new CustomerRepository();
        return customerRepository1.findBy("id",id);
    }
}
