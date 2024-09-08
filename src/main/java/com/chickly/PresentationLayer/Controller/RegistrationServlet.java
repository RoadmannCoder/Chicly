package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CustomerService;
import com.chickly.DTO.CustomerRegistrationDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/register")
public class RegistrationServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("customer") != null) {
            response.sendRedirect("/index.jsp");
            return;
        }
        else {
            customerService = new CustomerService();
            CustomerRegistrationDTO customerDTO = new CustomerRegistrationDTO(request);
            try {
                customerService.createCustomer(customerDTO);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("login.jsp");
        }

    }
}
