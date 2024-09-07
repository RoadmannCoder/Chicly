package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.SubProductService;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/filterProducts")
public class CategoryFilterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("category");
        List<SubProduct> filteredProducts;

        SubProductService subProductService = new SubProductService();

        if ("all".equals(categoryId)) {
            filteredProducts = subProductService.getAllSubProducts();
        } else {
            filteredProducts = subProductService.findSubProductsByCategory(Integer.parseInt(categoryId));
        }

        request.setAttribute("subProducts", filteredProducts);
        request.getRequestDispatcher("/chicly").forward(request, response);
    }
}
