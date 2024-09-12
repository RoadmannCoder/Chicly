package com.chickly.PresentationLayer.Filters;

import com.chickly.BussinesLayer.SubCategoryService;
import com.chickly.DTO.SubCategoryDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = "/productView")
public class SubCategoriesViewFilter implements Filter {
    private SubCategoryService subCategoryService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        List<SubCategoryDTO> subCategoriesDTOList = (List<SubCategoryDTO>) httpRequest.getSession().getAttribute("subcategories");
        if (subCategoriesDTOList == null) {
            System.out.println("Inside SubCategory Filter");
            subCategoryService = new SubCategoryService();
            subCategoriesDTOList = subCategoryService.getAllSubCategories();
            httpRequest.getSession().setAttribute("subcategories", subCategoriesDTOList);
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
