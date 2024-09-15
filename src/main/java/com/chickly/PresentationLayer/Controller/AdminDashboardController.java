package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CustomerService;
import com.chickly.BussinesLayer.ProductService;
import com.chickly.BussinesLayer.SubCategoryService;
import com.chickly.BussinesLayer.SubProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;


import java.io.IOException;

@WebServlet("/AdminDashBoardController")
public class AdminDashboardController extends HttpServlet {
    private ProductService productService;
    private CustomerService customerService;
    private SubCategoryService subCategoryService;
    private SubProductService subProductService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productService = new ProductService();
        subProductService = new SubProductService();
        customerService = new CustomerService();


        Long totalProducts = productService.getTotalProducts();
        Long totalSubProducts = subProductService.getTotalSubProducts();
        Long totalCustomers = customerService.getTotalCustomers();

        req.setAttribute("totalProducts", totalProducts);
        req.setAttribute("totalSubProducts", totalSubProducts);
        req.setAttribute("totalCustomers", totalCustomers);

        // Forward to JSP page
        RequestDispatcher dispatcher = req.getRequestDispatcher("adminDashboard.jsp");
        dispatcher.forward(req, resp);
    }

//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doGet(req, resp);
//    }
}
