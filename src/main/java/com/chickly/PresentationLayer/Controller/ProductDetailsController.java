package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CartService;
import com.chickly.BussinesLayer.SubProductService;
import com.chickly.DTO.SubProductDTO;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/product-details")
public class ProductDetailsController extends HttpServlet {
    SubProductService subProductService = new SubProductService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("product", subProductService.findSubProductByID(req));
        req.getRequestDispatcher("/product-details.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartService cartService = (CartService) req.getSession().getAttribute("cart");
        if(cartService == null) {
            cartService = new CartService();
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonData = sb.toString();

        Gson gson = new Gson();
        SubProductDTO product = gson.fromJson(jsonData, SubProductDTO.class);
        if(cartService.getTotalCartItems()==0){
            cartService.addCartItem(product, product.getQuantity());
            req.getSession().setAttribute("cart", cartService);
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print("{ \"status\": \"success\", \"cartItemCount\": " + cartService.getTotalCartItems() + " }");
            out.flush();
        }else {
            int Quantity = cartService.getQuantityOfSubProduct(product)==null?0:cartService.getQuantityOfSubProduct(product);
            if (product.getQuantity() + Quantity <= product.getStock()) {
                cartService.addCartItem(product, product.getQuantity() + Quantity);
                req.getSession().setAttribute("cart", cartService);
                resp.setContentType("application/json");
                PrintWriter out = resp.getWriter();
                out.print("{ \"status\": \"success\", \"cartItemCount\": " + cartService.getTotalCartItems() + " }");
                out.flush();
            } else {
                // Send failure response if quantity exceeds stock
                resp.setContentType("application/json");
                PrintWriter out = resp.getWriter();
                out.print("{ \"status\": \"fail\", \"message\": \"Quantity exceeds available stock.\" }");
                out.flush();
            }
        }

    }
}
