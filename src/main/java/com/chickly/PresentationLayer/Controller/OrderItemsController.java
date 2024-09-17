package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.OrderItemService;
import com.chickly.DTO.OrderItemDTO;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/OrderItemsController")
public class OrderItemsController extends HttpServlet {
    OrderItemService orderItemService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        orderItemService = new OrderItemService();
        List<OrderItemDTO> orderItems = orderItemService.getAllOrderItemsByOrderId(orderId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        out.print(gson.toJson(orderItems));
        out.flush();
    }
}
