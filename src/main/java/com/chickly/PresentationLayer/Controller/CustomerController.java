package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CustomerService;
import com.chickly.DTO.CustomerViewDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "customerView",urlPatterns = "/customerView")

public class CustomerController extends HttpServlet {
    private CustomerService customerService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String id = req.getParameter("Id");
        customerService = new CustomerService();
        List<CustomerViewDTO> customers;

        if (!areFiltersEmpty(userName, email, id)) {
            CustomerViewDTO customerViewDTO = customerService.getCustomerByUserNameEmailID(userName, email, id);
            customers = new ArrayList<>();
            if (customerViewDTO != null) {
                customers.add(customerViewDTO);
            }
        } else {
            customers = customerService.getAllCustomers();
        }

        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/customerProfiles.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Handle POST requests the same way as GET requests if needed
        doGet(req, resp);
    }
    private boolean areFiltersEmpty(String userName, String email, String Id){
        if ((userName == null || userName.trim().isEmpty())&&
                (email == null || email.trim().isEmpty())&&
                (Id == null || Id.trim().isEmpty())) {
            return true;
        }
        return false;
    }
}
