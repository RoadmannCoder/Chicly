package com.chickly.DTO;

import com.chickly.DataAccessLayer.Entities.Customer;
import jakarta.servlet.http.HttpServletRequest;


public class CustomerViewDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String street;
    private String city;
    private String zip;
    private String description;
    private String userName;

    public CustomerViewDTO() {
    }


    public CustomerViewDTO(String Id,String firstName, String lastName,String email, String phoneNumber, String street, String city, String zip, String description, String userName) {
        this.id = Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.description = description;
        this.userName = userName;
    }



    public CustomerViewDTO(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String zip = request.getParameter("zip");
        String description = request.getParameter("description");
        String userName = request.getParameter("userName");
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.description = description;
        this.userName = userName;
    }
    public static CustomerViewDTO fromCustomer(Customer customer) {
        return new CustomerViewDTO(
                customer.getId().toString(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getAddress().getStreet(),
                customer.getAddress().getCity(),
                customer.getAddress().getZip(),
                customer.getAddress().getDescription(),
                customer.getAccount().getUserName()
        );

    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
