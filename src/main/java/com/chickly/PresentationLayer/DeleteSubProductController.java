package com.chickly.PresentationLayer;

import com.chickly.BussinesLayer.SubProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name="deleteSubProduct",urlPatterns = "/deleteSubProduct")
@MultipartConfig
public class DeleteSubProductController extends HttpServlet {
    private SubProductService subProductService ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        subProductService = new SubProductService();
        subProductService.deleteSubProduct(req);
        resp.sendRedirect("productView");
    }
}
