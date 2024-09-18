package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CartService;
import com.chickly.DTO.SubProductDTO;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Repository.SubProductRepository;
import com.chickly.Mappers.SubProductMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cartupdate")
public class CartUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubProductRepository subProductRepository = new SubProductRepository();
        CartService cartService = (CartService) req.getSession().getAttribute("cart");

//        if (cartService != null && subProductDTO != null) {
//            cartService.updateProductQuantity(subProductDTO, quantity);
//        }
//        // Update the cart totals
//        int totalQuantity = cartService.getTotalQuantity();
//        double totalPrice = cartService.getTotalPrice().doubleValue();
//
//        // Create JSON response
//        resp.setContentType("application/json");
//        PrintWriter out = resp.getWriter();
//        out.write("{\"success\": true, \"totalQuantity\": " + totalQuantity + ", \"totalPrice\": " + totalPrice + "}");
//        out.flush();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Parse JSON data from the request body
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = mapper.readValue(req.getReader(), Map.class);

        // Retrieve productId and quantity from the JSON data
        String productId = (String) jsonData.get("productId");
        int quantity = (int) jsonData.get("quantity");
        SubProduct subProduct = subProductRepository.findBy("id",productId);
        SubProductDTO subProductDTO = SubProductMapper.convertEntityToDTO(subProduct);
        // Your business logic to update the cart based on productId and quantity
        cartService.updateProductQuantity(subProductDTO,quantity);

        // Prepare the response data
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("success", true);
        responseData.put("totalQuantity", cartService.getTotalQuantity());
        responseData.put("totalPrice", cartService.getTotalPrice());

        // Send JSON response
        PrintWriter out = resp.getWriter();
        mapper.writeValue(out, responseData);
        out.flush();
    }
}
