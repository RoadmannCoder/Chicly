package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CartService;
import com.chickly.BussinesLayer.OrderProcessError;
import com.chickly.BussinesLayer.OrderService;
import com.chickly.DTO.SubProductDTO;
import com.chickly.DataAccessLayer.DBContext.EntityManagerUtil;
import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Entities.Order;
import com.chickly.DataAccessLayer.Entities.OrderItem;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Repository.CustomerRepository;
import com.chickly.DataAccessLayer.Repository.OrderRepository;
import com.chickly.DataAccessLayer.Repository.SubProductRepository;
import com.chickly.Enums.Status;
import com.chickly.Mappers.SubProductMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CartService cartService = (CartService) req.getSession().getAttribute("cart")!=null?(CartService) req.getSession().getAttribute("cart"):null;

        req.getRequestDispatcher("checkout.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = (Customer) req.getSession().getAttribute("user");
        CartService cartService = (CartService) req.getSession().getAttribute("cart");
        OrderService orderService = new OrderService();
        Optional<OrderProcessError> optionalOrderProcessError = Optional.ofNullable(orderService.createOrder(cartService, customer));
        optionalOrderProcessError
                .map(OrderProcessError::getOrder) // Get the order, if orderProcessError is present
                .ifPresentOrElse(
                        order -> {
                            LocalDate localDate = order.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            LocalDate newDate = localDate.plusDays(2);
                            req.setAttribute("order", order);
                            req.setAttribute("date", newDate);
                            req.getSession().removeAttribute("cart");
                            try {
                                req.getRequestDispatcher("order-tracking.jsp").forward(req, resp);
                            } catch (ServletException | IOException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        () -> {

                            optionalOrderProcessError
                                    .map(OrderProcessError::getSubProductDTO) // Map to SubProductDTO if Order was null
                                    .ifPresentOrElse(
                                            subProductDTO -> {
                                                // If SubProductDTO is present, send a specific error message
                                                try {
                                                    SubProductRepository subProductRepository = new SubProductRepository();
                                                    SubProduct subProduct = subProductRepository.findBy("id",optionalOrderProcessError.get().getSubProductDTO().getId());
                                                    resp.sendRedirect("/cart?action=1&error=" + optionalOrderProcessError.get().getSubProductDTO().getProductName() + "&id=" + optionalOrderProcessError.get().getSubProductDTO().getId() + "&stock=" + subProduct.getStock());                                                } catch (IOException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            },
                                            () -> {
                                                // Handle the case where both Order and SubProductDTO are null
                                                try {
                                                    resp.sendRedirect("/cart?action=2&error= Your Credit Limit So Low To Proceed Your Order Recharge and Comeback Again");
                                                } catch (IOException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                    );
                        }
                );




    }
}
