package com.chickly.PresentationLayer.Filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
//@WebFilter("/register")
public class RegistrationValidationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String firstName = httpRequest.getParameter("firstName");
        String lastName = httpRequest.getParameter("lastName");
        String creditLimit = httpRequest.getParameter("creditLimit");
        String dateOfBirth = httpRequest.getParameter("dateOfBirth");
        String email = httpRequest.getParameter("email");
        String phoneNumber = httpRequest.getParameter("phoneNumber");
        String job = httpRequest.getParameter("job");
        String street = httpRequest.getParameter("street");
        String city = httpRequest.getParameter("city");
        String zip = httpRequest.getParameter("zip");
        String description = httpRequest.getParameter("description");
        String userName = httpRequest.getParameter("userName");
        String password = httpRequest.getParameter("password");

        if (isAnyParameterEmpty(firstName, lastName, creditLimit, dateOfBirth, email, phoneNumber, job, street, city, zip, description, userName, password)) {
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean isAnyParameterEmpty(String... parameters) {
        for (String param : parameters) {
            if (param == null || param.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
        // Cleanup code if necessary
    }
}
