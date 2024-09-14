package com.chickly.Mappers;
import com.chickly.DTO.CustomerRegistrationDTO;
import com.chickly.DTO.CustomerViewDTO;
import com.chickly.DataAccessLayer.Entities.Account;
import com.chickly.DataAccessLayer.Entities.Address;
import com.chickly.DataAccessLayer.Entities.Customer;
import jakarta.persistence.Access;
import jakarta.servlet.ServletException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CustomerMapper {

    public static CustomerRegistrationDTO fromEntityToCustomerRegistrationDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerRegistrationDTO dto = new CustomerRegistrationDTO();
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        String crediLimitString = customer.getCreditLimit().toString();
        dto.setCreditLimit(crediLimitString);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOfBirthString = dateFormat.format(customer.getDateOfBirth());
        dto.setDateOfBirth(dateOfBirthString);
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setJob(customer.getJob());
        dto.setCity(customer.getAddress().getCity());
        dto.setStreet(customer.getAddress().getStreet());
        dto.setZip(customer.getAddress().getZip());
        dto.setDescription(customer.getAddress().getDescription());
        dto.setPassword(customer.getAccount().getPassword());
        dto.setUserName(customer.getAccount().getUserName());
        return dto;
    }

    public static Customer fromCustomerRegistrationDTOToEntity(CustomerRegistrationDTO dto) throws ParseException {
        if (dto == null) {
            return null;
        }
        Customer customer = new Customer();

        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        /////////////////////////////////////////////////////////////////////
        customer.setCreditLimit(new BigDecimal(dto.getCreditLimit()));

        String dateOfBirthStr = dto.getDateOfBirth();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Matches HTML5 date input format
        Date dateOfBirth = dateFormat.parse(dateOfBirthStr);
        customer.setDateOfBirth(dateOfBirth);
        /////////////////////////////////////////////////////////////////////
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setJob(dto.getJob());
        customer.setAccount(new Account(dto.getUserName(),dto.getPassword()));
        customer.setAddress(new Address(dto.getStreet(),dto.getCity(),dto.getZip(),dto.getDescription()));

        return customer;
    }
    public static List<CustomerViewDTO> fromEntityToCustomerViewDTO(Optional<List<Customer>> customersOptional) {
        if (customersOptional.isEmpty()) {
            return null;
        }
        List<CustomerViewDTO> customerViewDTOList = new ArrayList<>();

        if (customersOptional.isPresent()) {
            List<Customer> customersList = customersOptional.get();

            for (Customer customer : customersList) {
                CustomerViewDTO dto = CustomerViewDTO.fromCustomer(customer);
                customerViewDTOList.add(dto);
            }
        }

        return customerViewDTOList;
    }

}
