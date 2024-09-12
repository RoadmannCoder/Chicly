package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.*;
import com.chickly.DTO.CategoryDTO;
import com.chickly.DTO.CustomerViewDTO;
import com.chickly.DTO.SubCategoryDTO;
import com.chickly.DTO.SubProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "productView",urlPatterns = "/productView")
public class ProductController extends HttpServlet {
    private SubProductService subProductService;
    private ProductService productService;
    private CategoryService categoryService;
    private SubCategoryService subCategoryService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        categoryService=new CategoryService();
        subCategoryService=new SubCategoryService();
        subProductService = new SubProductService();
        List<CategoryDTO> categoryDTOList=categoryService.getAllCategories();
        req.setAttribute("categories", categoryDTOList);
        List<SubCategoryDTO> subCategoryDTOList=subCategoryService.getAllSubCategories();
        req.setAttribute("subcategories", subCategoryDTOList);
        List<SubProductDTO> subProductDTOList = subProductService.getAllSubProductDTOs();
        req.setAttribute("subProducts", subProductDTOList);

        req.getRequestDispatcher("/view-products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Handle POST requests the same way as GET requests if needed
        doGet(req, resp);
    }

}
