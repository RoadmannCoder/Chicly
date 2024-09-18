package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CategoryService;
import com.chickly.BussinesLayer.SubCategoryService;
import com.chickly.DTO.CategoryDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "addSubCategory",urlPatterns = "/addSubCategory")
@MultipartConfig
public class AddSubCategoryController extends HttpServlet {
    private SubCategoryService subCategoryService ;
    private CategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        categoryService = new CategoryService();
        List<CategoryDTO> categoryDTOList = categoryService.getAllCategories();
        req.setAttribute("categories", categoryDTOList);

        req.getRequestDispatcher("addSubCategory.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        subCategoryService = new SubCategoryService();

        String categoryId = req.getParameter("category");
        String subCategoryName = req.getParameter("subcategory");
        try {
            subCategoryService.createSubCategory(categoryId,subCategoryName);
            resp.sendRedirect("adminDashboard.jsp?successMessage=SubCategory+added+successful");
        } catch (Exception e) {
            resp.sendRedirect("adminDashboard.jsp?errorMessage=An+error+occurred:+Please+try+again");
        }

    }
}
