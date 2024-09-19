package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CartService;
import com.chickly.BussinesLayer.CategoryService;
import com.chickly.BussinesLayer.SubProductService;
import com.chickly.DTO.CategoryDTO;
import com.chickly.DTO.SubProductDTO;
import com.chickly.DTO.SubProductFilterDTO;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Repository.SubProductRepository;
import com.chickly.Enums.Color;
import com.chickly.Enums.Size;
import com.google.gson.Gson;

import com.chickly.Mappers.SubProductFilterMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;

@WebServlet("/filterProducts")
public class FilterProductsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubProductService subProductService = new SubProductService();
        CategoryService categoryService = new CategoryService();

        SubProductFilterDTO filterDTO = new SubProductFilterMapper().parseRequestToFitlerDTO(request);

        List<SubProductDTO> subProductDTOs = subProductService.filterSubProducts(filterDTO);

        long totalSubProducts = subProductService.countFilteredSubProducts(filterDTO);

        List<CategoryDTO> categories = categoryService.getAllCategories();

        request.setAttribute("categories", categories);
        request.setAttribute("subProducts", subProductDTOs);
        request.setAttribute("currentPage", filterDTO.getPageNumber());
        request.setAttribute("totalSubProducts", totalSubProducts);
        request.setAttribute("pageSize", filterDTO.getPageSize());
        request.setAttribute("param", request.getParameterMap());

        request.getRequestDispatcher("/shop.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartService cartService = (CartService) req.getSession().getAttribute("cart");
        if(cartService == null) {
            cartService = new CartService();
        }
        BufferedReader reader = req.getReader();
        StringBuilder jsonData = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonData.append(line);
        }

        Gson gson = new Gson();
        SubProductDTO product = gson.fromJson(jsonData.toString(), SubProductDTO.class);
        int Quantity = cartService.getQuantityOfSubProduct(product);
        System.out.println(Quantity);
        if(Quantity+product.getQuantity()<=product.getStock()) {
            cartService.addCartItem(product);
            req.getSession().setAttribute("cart", cartService);
            req.setAttribute("validMessage","Product Added To Cart");
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print("{ \"message\": \"Product added to cart\", \"cartItemCount\": " + cartService.getTotalCartItems() + " }");
            out.flush();
        }else{
            req.getSession().setAttribute("cart", cartService);
            req.setAttribute("errorMessage","Product exceeds the limit");
            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print("{ \"message\": \"Product exceeds the limit\", \"cartItemCount\": " + cartService.getTotalCartItems() + " }");
            out.flush();
        }
    }

    private BigDecimal parseBigDecimal(String value) {
        try {
            return value != null && !value.isEmpty() ? new BigDecimal(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
    private Color parseColorEnum(String color) {
        try {
            return color != null && !color.isEmpty() ? Color.valueOf(color.toUpperCase()) : null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    private Size parseSizeEnum(String size) {
        try {
            return size != null && !size.isEmpty() ? Size.valueOf(size.toUpperCase()) : null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
