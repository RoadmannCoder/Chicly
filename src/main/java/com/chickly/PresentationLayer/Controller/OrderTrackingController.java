package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.OrderItemService;
import com.chickly.BussinesLayer.OrderService;
import com.chickly.DTO.OrderItemDTO;
import com.chickly.DTO.OrderViewDTO;
import com.chickly.DataAccessLayer.Entities.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@WebServlet("/OrderTrackingController")
public class OrderTrackingController extends HttpServlet {
    private OrderItemService orderItemService;
    private  OrderService orderService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        orderItemService = new OrderItemService();
        orderService = new OrderService();

        List<OrderItemDTO> orderItems = orderItemService.getAllOrderItemsByOrderId(orderId);
        Order order = orderService.getOrderById(orderId);

        LocalDate localDate = order.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate newDate = localDate.plusDays(2);
        request.setAttribute("date", newDate);
        request.setAttribute("order", order);
        request.setAttribute("orderItems", orderItems);

        request.getRequestDispatcher("/order-tracking.jsp").forward(request, response);

    }
}