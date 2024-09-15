package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CustomerService;
import com.chickly.DataAccessLayer.Entities.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "updatecustomer",urlPatterns = "/updatecustomer")
@MultipartConfig
public class UpdateCustomerController extends HttpServlet {
    private CustomerService customerService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        customerService = new CustomerService();
        Customer customer = (Customer)req.getSession().getAttribute("user");
        customerService.updateCustomer(req,customer);
        req.getSession().removeAttribute("user");
        Customer customerNew = customerService.getCustomerById(customer.getId());
        req.getSession().setAttribute("user",customerNew);
        resp.sendRedirect("/");
    }
}
