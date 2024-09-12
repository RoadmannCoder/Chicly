package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.SubProductService;
import com.chickly.DTO.SubProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/product-details")
public class ProductDetailsController extends HttpServlet {
    SubProductService subProductService = new SubProductService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("product", subProductService.findSubProductByID(req));
        req.getRequestDispatcher("/product-details.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
