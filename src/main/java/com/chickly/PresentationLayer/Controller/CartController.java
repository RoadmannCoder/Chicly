package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CartService;
import com.chickly.DTO.SubProductDTO;
import com.chickly.DataAccessLayer.Entities.CartItems;
import com.chickly.DataAccessLayer.Repository.CartRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/cart")
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String Error= req.getParameter("error")==null?"":req.getParameter("error");
//        int subProductID = Integer.parseInt(req.getParameter("id"));
//        int stock = Integer.parseInt(req.getParameter("stock"));
        String errorMessage = req.getParameter("error");
        String stock = req.getParameter("stock");

        if (errorMessage != null && !errorMessage.isEmpty()) {
            req.setAttribute("errorMessage", errorMessage+" = "+stock);
            System.out.println(errorMessage);
        }

        setCartInSession(req);
        req.getRequestDispatcher("shop-cart.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Read JSON input
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        // Use Gson to parse the incoming JSON
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json.toString(), JsonObject.class);
        int productId = jsonObject.get("productId").getAsInt();

        // Retrieve the cart from the session
        CartService cart = (CartService) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new CartService();
            req.getSession().setAttribute("cart", cart);
            req.getSession().setAttribute("cartPrevious",cart);
        }

        // Create SubProductDTO to remove from cart
        SubProductDTO subProductDTO = new SubProductDTO();
        subProductDTO.setId(productId);


        // Remove the product from the cart
        boolean success = cart.removeCartItem(subProductDTO);

        // Prepare the response JSON using Gson
        JsonObject responseData = new JsonObject();
        responseData.addProperty("success", success);
        responseData.addProperty("totalQuantity", cart.getTotalQuantity());
        responseData.addProperty("totalPrice", cart.getTotalPrice());
        responseData.addProperty("cartItemCount",cart.getTotalCartItems());

        // Set the response type to JSON and send the response
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(gson.toJson(responseData));
    }

    private void setCartInSession(HttpServletRequest req){
        if(req.getSession().getAttribute("user")!=null){
            CartRepository cartRepository = new CartRepository();
            List<CartItems> cartItems = cartRepository.findAll().get();

        }else {
            CartService cartService = (CartService) req.getSession().getAttribute("cart");
            if (cartService == null) {
                cartService = new CartService();
                req.getSession().setAttribute("cart", cartService);
                req.getSession().setAttribute("cartPrevious", cartService);
            }
        }

    }
}
