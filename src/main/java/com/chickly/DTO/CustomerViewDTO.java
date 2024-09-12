package com.chickly.DTO;

import jakarta.servlet.http.HttpServletRequest;



public class CustomerRegistrationDTO {

    private String firstName;
    private String lastName;
    private String creditLimit;
    private String dateOfBirth;
    private String email;
    private String phoneNumber;
    private String job;
    private String street;
    private String city;
    private String zip;
    private String description;
    private String userName;
    private String password;

    public CustomerRegistrationDTO() {
    }


    public CustomerRegistrationDTO(String firstName, String lastName, String creditLimit, String dateOfBirth, String email, String phoneNumber, String job, String street, String city, String zip, String description, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.creditLimit = creditLimit;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.job = job;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.description = description;
        this.userName = userName;
        this.password = password;
    }



    public  CustomerRegistrationDTO(HttpServletRequest request) {
        CustomerRegistrationDTO customerDTO = new CustomerRegistrationDTO();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String creditLimit = request.getParameter("creditLimit");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String job = request.getParameter("job");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String zip = request.getParameter("zip");
        String description = request.getParameter("description");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
//        CustomerRegistrationDTO customerRegistrationDTO = new CustomerRegistrationDTO( firstName,  lastName,  creditLimit,  dateOfBirth,  email,  phoneNumber,  job,  street,  city,  zip,  description,  userName,  password);
//        return customerRegistrationDTO;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creditLimit = creditLimit;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.job = job;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.description = description;
        this.userName = userName;
        this.password = password;
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

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
