package com.chickly.PresentationLayer.Filters;
import com.chickly.BussinesLayer.CustomerService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

//@WebFilter("/register")
public class PhoneValidationFilter implements Filter {

    private CustomerService customerService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        customerService = new CustomerService();  // Initialize the service
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String checkType = request.getParameter("checkType");

        if ("phonecheck".equalsIgnoreCase(checkType)) {
            PrintWriter out = response.getWriter();
            String phoneNumber = request.getParameter("phoneNo");
            if (customerService.phoneNumberValidation(phoneNumber)) {
                out.print("This phone number already exists");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
