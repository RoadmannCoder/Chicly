package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.OrderService;
import com.chickly.Enums.Status;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/OrderStatusController")
public class OrderStatusController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String newStatus = request.getParameter("status");

        // Assuming you have an OrderService to handle business logic
        OrderService orderService = new OrderService();

        try {
            // Update the order status in the database
            orderService.updateOrderStatus(Integer.parseInt(orderId), Status.valueOf(newStatus));
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
