package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CustomerService;
import com.chickly.BussinesLayer.OrderService;
import com.chickly.DTO.CustomerViewDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="customerDetails",urlPatterns = "/customerDetails")
public class CustomerDetailsController extends HttpServlet {

    private  CustomerService customerService ;
    private  OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        customerService = new CustomerService();
        String userName = request.getParameter("userName");

        if (userName == null || userName.trim().isEmpty()) {
            response.sendRedirect("customerProfiles.jsp");
            return;
        }

        CustomerViewDTO customer = customerService.getCustomerByUserName(userName);
        if (customer == null) {
            request.setAttribute("errorMessage", "Customer not found.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        request.setAttribute("customer", customer);

        // Fetch customer orders
//        List<OrderDTO> orders = orderService.getOrdersByCustomer(userName);
//        request.setAttribute("orders", orders);

        // Forward to JSP
        request.getRequestDispatcher("customerDetails.jsp").forward(request, response);
    }
}
