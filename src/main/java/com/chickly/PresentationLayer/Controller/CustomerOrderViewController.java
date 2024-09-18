package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.OrderService;
import com.chickly.DTO.OrderViewDTO;
import com.chickly.DataAccessLayer.Entities.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class CustomerOrderViewController extends HttpServlet {
    private  OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = (Customer) req.getSession(false).getAttribute("user");
        if(customer==null)
            resp.sendRedirect("/");
        orderService = new OrderService();
        List<OrderViewDTO> orderList = orderService.getAllOrdersOfSpecificCustomer(customer.getId().toString());
        req.setAttribute("orders", orderList);
        req.getRequestDispatcher("orderHistory.jsp").forward(req, resp);

    }
}
