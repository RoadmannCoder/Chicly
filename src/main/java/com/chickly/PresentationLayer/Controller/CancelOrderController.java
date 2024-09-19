package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.OrderService;
import com.chickly.Enums.Status;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.google.gson.JsonObject;


import java.io.IOException;

@WebServlet("/cancelOrderController")
public class CancelOrderController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String newStatus = request.getParameter("status");
        OrderService orderService = new OrderService();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JsonObject jsonResponse = new JsonObject();
        try {
            orderService.updateOrderStatus(Integer.parseInt(orderId), Status.valueOf(newStatus));
            jsonResponse.addProperty("success", true);
            jsonResponse.addProperty("message", "Order canceled successfully.");
        } catch (Exception e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Failed to cancel the order. Please try again.");
        }

        response.getWriter().write(jsonResponse.toString());
    }
}
