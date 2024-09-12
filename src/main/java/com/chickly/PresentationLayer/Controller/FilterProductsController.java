package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CategoryService;
import com.chickly.BussinesLayer.SubProductService;
import com.chickly.DTO.CategoryDTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/filterProducts")
public class FilterProductsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubProductService subProductService = new SubProductService();
        CategoryService categoryService = new CategoryService();

        SubProductFilterDTO filterDTO = new SubProductFilterDTO();
        filterDTO.setProductName(request.getParameter("productName"));
        filterDTO.setMinPrice(parseBigDecimal(request.getParameter("minPrice")));
        filterDTO.setMaxPrice(parseBigDecimal(request.getParameter("maxPrice")));
        filterDTO.setSize(parseSizeEnum(request.getParameter("size")));
        filterDTO.setColor(parseColorEnum(request.getParameter("color")));
        filterDTO.setPageNumber(parseInteger(request.getParameter("page"), 1));
        filterDTO.setCategoryId(parseInteger(request.getParameter("category"),null));
        filterDTO.setPageSize(6);

        List<SubProductDTO> subProductDTOs = subProductService.filterSubProducts(filterDTO);
        long totalSubProducts = subProductService.countFilteredSubProducts(filterDTO);

        List<CategoryDTO> categories = categoryService.getAllCategories();

        request.setAttribute("categories", categories);
        request.setAttribute("subProducts", subProductDTOs);
        request.setAttribute("currentPage", filterDTO.getPageNumber());
        request.setAttribute("totalSubProducts", totalSubProducts);
        request.setAttribute("pageSize", filterDTO.getPageSize());
        request.setAttribute("param", request.getParameterMap());

        request.getRequestDispatcher("/shop.jsp").forward(request, response);
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

    private Integer parseInteger(String value, Integer defaultValue) {
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
