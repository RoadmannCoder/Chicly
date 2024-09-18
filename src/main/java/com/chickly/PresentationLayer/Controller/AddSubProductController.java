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
import java.io.IOException;
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

        try {
            subProductService.addSubProduct(subProduct, subProduct.getProductName());
            response.sendRedirect("productView?successMessage=Operation+is+successful");
        } catch (Exception e) {
            response.sendRedirect("productView?errorMessage=An+error+occurred:+Please+try+again");
        }
//        response.sendRedirect("productView");

    }

}
