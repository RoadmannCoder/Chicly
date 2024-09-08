package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CategoryService;
import com.chickly.BussinesLayer.SubProductService;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/chicly")
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Optional<List<SubProduct>> allSubProducts = new SubProductService().getAllSubProducts();
        List<SubProduct> requestSubProducts =(List<SubProduct>) request.getAttribute("subProducts");

        if (requestSubProducts == null){
            request.setAttribute("subProducts", allSubProducts.get());
        }
        request.setAttribute("categories", CategoryService.getAllCategories().get());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
