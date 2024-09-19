package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CartService;
import com.chickly.DTO.SubProductDTO;
import com.chickly.DataAccessLayer.Entities.CartItems;
import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Repository.CustomerRepository;
import com.chickly.Mappers.SubProductMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = (Customer) req.getSession(false).getAttribute("user");
        CartService cartService = (CartService) req.getSession().getAttribute("cart");
        HttpSession session = req.getSession(false);
        if(cartService!=null) {
            cartService.addToDB(new ArrayList<>(),customer,cartService);
        }
        if(customer !=null){

        }

        if (session != null) {
            session.removeAttribute("user");
            session.invalidate();
        }


        resp.sendRedirect("logout.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);


    }

}
