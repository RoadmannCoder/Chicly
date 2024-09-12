package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CustomerService;
import com.chickly.BussinesLayer.OrderService;
import com.chickly.BussinesLayer.SubProductService;
import com.chickly.DTO.CustomerViewDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="updateProduct",urlPatterns = "/updateProduct")
public class UpdateProductController extends HttpServlet {

    private SubProductService subProductService ;
    private  OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        subProductService = new SubProductService();

        request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
    }
}
