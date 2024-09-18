package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.*;
import com.chickly.DTO.CategoryDTO;
import com.chickly.DTO.SubCategoryDTO;
import com.chickly.DTO.SubProductDTO;
import com.chickly.DTO.SubProductForAdminDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "productView",urlPatterns = "/productView")
@MultipartConfig
public class ProductController extends HttpServlet {
    private SubProductService subProductService;
    private CategoryService categoryService;
    private SubCategoryService subCategoryService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        subProductService = new SubProductService();

        String successMessage = req.getParameter("successMessage");
        String errorMessage = req.getParameter("errorMessage");

        // Pass the messages to the JSP
        req.setAttribute("successMessage", successMessage);
        req.setAttribute("errorMessage", errorMessage);
        List<SubProductForAdminDTO> subProductList = subProductService.getAllSubProductForAdminDTOs(req);
        req.setAttribute("subProducts", subProductList);

        req.getRequestDispatcher("/view-products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Handle POST requests the same way as GET requests if needed
        doGet(req, resp);
    }

}
