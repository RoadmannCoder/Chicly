package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CategoryService;
import com.chickly.BussinesLayer.SubProductService;
import com.chickly.DTO.SubProductDTO;
import com.chickly.DTO.SubProductFilterDTO;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Repository.SubProductRepository;
import com.chickly.Enums.Color;
import com.chickly.Enums.Size;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@WebServlet("/filterProducts")
public class FilterProductsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubProductService subProductService = new SubProductService();



        SubProductFilterDTO filterDTO = new SubProductFilterDTO();
        filterDTO.setProductName(request.getParameter("productName"));
        filterDTO.setMinPrice(parseBigDecimal(request.getParameter("minPrice")));
        filterDTO.setMaxPrice(parseBigDecimal(request.getParameter("maxPrice")));
        filterDTO.setSize(parseSizeEnum(request.getParameter("size")));
        filterDTO.setColor(parseColorEnum(request.getParameter("color")));
        filterDTO.setPageNumber(parseInteger(request.getParameter("page"), 1));
        filterDTO.setPageSize(6); // Set your preferred page size

        // Get the filtered products as DTOs
        List<SubProductDTO> subProductDTOs = subProductService.filterSubProducts(filterDTO);
        long totalSubProducts = subProductService.countFilteredSubProducts(filterDTO);

        // Set attributes to pass to JSP
        request.setAttribute("subProducts", subProductDTOs);
        request.setAttribute("currentPage", filterDTO.getPageNumber());
        request.setAttribute("totalSubProducts", totalSubProducts);
        request.setAttribute("pageSize", filterDTO.getPageSize());
        request.setAttribute("param", request.getParameterMap());

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    private BigDecimal parseBigDecimal(String value) {
        try {
            return value != null && !value.isEmpty() ? new BigDecimal(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Color parseColorEnum(String color) {
        try {
            return color != null && !color.isEmpty() ? Color.valueOf(color.toUpperCase()) : null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    private Size parseSizeEnum(String size) {
        try {
            return size != null && !size.isEmpty() ? Size.valueOf(size.toUpperCase()) : null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private Integer parseInteger(String value, int defaultValue) {
        try {
            return value != null && !value.isEmpty() ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
