package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CartService;
import com.chickly.DTO.SubProductDTO;
import com.chickly.DataAccessLayer.Entities.CartItems;
import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Repository.CartRepository;
import com.chickly.DataAccessLayer.Repository.CustomerRepository;
import com.chickly.Mappers.SubProductMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/login")
@MultipartConfig
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = (Customer) req.getAttribute("user");
        if (customer != null) {
            CartService cartService = (CartService) req.getSession().getAttribute("cart") == null ? new CartService() : (CartService) req.getSession().getAttribute("cart");
            cartService = cartService.mergeFromDBToSession(customer,cartService);
            req.getSession().setAttribute("cart", cartService);
            req.getSession().setAttribute("user",customer);
            resp.sendRedirect("/");
        }else{
            doGet(req, resp);
        }
    }



}
