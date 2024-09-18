package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.ProductService;
import com.chickly.BussinesLayer.SubCategoryService;
import com.chickly.DTO.CategoryDTO;
import com.chickly.DTO.SubCategoryDTO;
import com.chickly.Enums.Color;
import com.chickly.Enums.Size;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "addProduct",urlPatterns = "/addProduct")
@MultipartConfig
public class AddProductController extends HttpServlet {
    private SubCategoryService subCategoryService ;
    private ProductService productService ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        subCategoryService = new SubCategoryService();
        List<SubCategoryDTO> subCategoriesDTOList = subCategoryService.getAllSubCategories();
        req.setAttribute("subCategories", subCategoriesDTOList);

        req.getRequestDispatcher("addProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productService = new ProductService();

        try {
            productService.createProduct(req);
            resp.sendRedirect("adminDashboard.jsp?successMessage=Product+added+successful");
        } catch (Exception e) {
            resp.sendRedirect("adminDashboard.jsp?errorMessage=An+error+occurred:+Please+try+again");
        }
    }
}
