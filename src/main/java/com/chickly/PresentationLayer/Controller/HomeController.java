package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CategoryService;
import com.chickly.BussinesLayer.SubProductService;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/chicly")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<SubProduct> allSubProducts = new SubProductService().getAllSubProducts();
        List<SubProduct> requestSubProducts =(List<SubProduct>) request.getAttribute("subProducts");

        if (requestSubProducts == null){
            request.setAttribute("subProducts", allSubProducts);
        }
        request.setAttribute("categories", CategoryService.getAllCategories());
       request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
