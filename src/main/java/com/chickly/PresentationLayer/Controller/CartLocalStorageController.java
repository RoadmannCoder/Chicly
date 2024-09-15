package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cartlocal")
public class CartLocalStorageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        CartService cartService = (CartService) req.getSession().getAttribute("cart");

        if (cartService == null) {
            cartService = new CartService();
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonCart = mapper.writeValueAsString(cartService);

        resp.getWriter().write("{\"cart\": " + jsonCart + "}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartService cartService = new CartService();

        ObjectMapper mapper = new ObjectMapper();
        cartService = mapper.readValue(req.getReader(), CartService.class);

        // Restore the cart in the session
        req.getSession().setAttribute("cart", cartService);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
