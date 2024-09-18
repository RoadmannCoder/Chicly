package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.SubProductService;
import com.chickly.DTO.SubProductDTO;
import com.chickly.DTO.SubProductForAdminDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="updateSubProduct",urlPatterns = "/updateSubProduct")
@MultipartConfig
public class UpdateSubProductController extends HttpServlet {

    private SubProductService subProductService ;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        subProductService = new SubProductService();
        String subProductId = request.getParameter("subproduct_Id");
        SubProductForAdminDTO subProductDTO = subProductService.getSubProductById(subProductId);
        request.setAttribute("subproduct",subProductDTO);
        System.out.println(subProductDTO.getImageURL());
        request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        subProductService = new SubProductService();
        try {
            subProductService.updateSubProduct(request);
            response.sendRedirect("productView?successMessage=Operation+is+successful");
        } catch (Exception e) {
            response.sendRedirect("productView?errorMessage=An+error+occurred:+Please+try+again");
        }
//        response.sendRedirect("productView");
    }
}
