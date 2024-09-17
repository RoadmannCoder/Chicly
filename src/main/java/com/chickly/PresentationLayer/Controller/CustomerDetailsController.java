package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CustomerService;
import com.chickly.BussinesLayer.OrderService;
import com.chickly.DTO.CustomerViewDTO;
import com.chickly.DTO.OrderViewDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

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
        System.out.println("Customer ID*********************************"+customer.getId());

        orderService = new OrderService();
        List<OrderViewDTO> orderList = orderService.getAllOrdersOfSpecificCustomer(customer.getId());
        System.out.println("Order size*********************************"+orderList.size());
        request.setAttribute("customer", customer);
        request.setAttribute("orders", orderList);

        request.getRequestDispatcher("customerDetails.jsp").forward(request, response);
    }
}
