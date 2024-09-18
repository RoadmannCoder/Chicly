package com.chickly.PresentationLayer.Controller;

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
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        subProductService = new SubProductService();

        try {
            subProductService.deleteSubProduct(req);
            resp.sendRedirect("productView?successMessage=Operation+is+successful");
        } catch (Exception e) {
            resp.sendRedirect("productView?errorMessage=An+error+occurred:+Please+try+again");
        }
//        resp.sendRedirect("productView");
    }
}
