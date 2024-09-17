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
        categoryService.createCategory(catergoryName);
//        req.setAttribute("errorMessage","Added successfully");
//        req.getRequestDispatcher("adminDashboard.jsp").forward(req,resp);
        resp.sendRedirect("/adminDashboard.jsp");

    }
}
