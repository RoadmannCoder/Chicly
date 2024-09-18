package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "addCategory",urlPatterns = "/addCategory")
@MultipartConfig
public class AddCategoryController extends HttpServlet {

    private CategoryService categoryService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("/addCategory.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        categoryService = new CategoryService();
        String catergoryName = req.getParameter("categoryname");
        try {
            categoryService.createCategory(catergoryName);
            resp.sendRedirect("adminDashboard.jsp?successMessage=Category+added+successful");
        } catch (Exception e) {
            resp.sendRedirect("adminDashboard.jsp?errorMessage=An+error+occurred:+Please+try+again");
        }

    }
}
