package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.CategoryService;
import com.chickly.BussinesLayer.SubProductService;
import com.chickly.DTO.CategoryDTO;
import com.chickly.DTO.SubProductDTO;
import com.chickly.DTO.SubProductFilterDTO;

import com.chickly.Mappers.SubProductFilterMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.List;

@WebServlet("/filterProducts")
public class FilterProductsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubProductService subProductService = new SubProductService();
        CategoryService categoryService = new CategoryService();

        SubProductFilterDTO filterDTO = new SubProductFilterMapper().parseRequestToFitlerDTO(request);

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

}
