package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CartService;
import com.chickly.DTO.SubProductDTO;
import com.chickly.DataAccessLayer.Entities.CartItems;
import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Repository.CartRepository;
import com.chickly.DataAccessLayer.Repository.CustomerRepository;
import com.chickly.DataAccessLayer.Util.ObjectMapperConfig;
import com.chickly.Mappers.SubProductMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        ObjectMapper mapper = ObjectMapperConfig.configureObjectMapper();
// Serialize or Deserialize objects using this mapper
         cartService = mapper.readValue(req.getReader(), CartService.class);
        req.getSession().setAttribute("cart", cartService);
        resp.setStatus(HttpServletResponse.SC_OK);

    }
}
