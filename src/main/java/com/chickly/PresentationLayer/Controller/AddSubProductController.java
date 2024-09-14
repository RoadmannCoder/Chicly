package com.chickly.PresentationLayer.Controller;
import com.chickly.BussinesLayer.ProductService;
import com.chickly.BussinesLayer.SubProductService;
import com.chickly.DTO.ProductViewDTO;
import com.chickly.DTO.SubProductDTO;
import com.chickly.Enums.Color;
import com.chickly.Enums.Size;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;


@WebServlet(name = "addsubProduct",urlPatterns = "/addsubProduct")
@MultipartConfig
public class AddSubProductController extends HttpServlet {
    private ProductService productService;
    private SubProductService subProductService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productService = new ProductService();
        List<ProductViewDTO> productDTOList = productService.getAllProdcutsForView();
        req.setAttribute("mainProducts", productDTOList);
        req.setAttribute("colors", Color.values());
        req.setAttribute("sizes", Size.values());

        req.getRequestDispatcher("/addSubProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        subProductService = new SubProductService();
        SubProductDTO subProduct = subProductService.createSubProductDTO(request);

        subProductService.addSubProduct(subProduct,subProduct.getProductName());
        response.sendRedirect("productView");

    }
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//
//        out.println("<html><body>");
//        out.println("<h2>Debugging Information:</h2>");
//        out.println("<p>Content Type: " + request.getContentType() + "</p>");
//
//        out.println("<h3>Request Parameters:</h3>");
//        Enumeration<String> parameterNames = request.getParameterNames();
//        while (parameterNames.hasMoreElements()) {
//            String paramName = parameterNames.nextElement();
//            String[] paramValues = request.getParameterValues(paramName);
//            out.println("<p>" + paramName + ": " + String.join(", ", paramValues) + "</p>");
//        }
//
//        out.println("<h3>Request Parts:</h3>");
//        for (Part part : request.getParts()) {
//            out.println("<p>Part Name: " + part.getName() + ", Size: " + part.getSize() + "</p>");
//        }
//
//        out.println("</body></html>");
//    }

}
