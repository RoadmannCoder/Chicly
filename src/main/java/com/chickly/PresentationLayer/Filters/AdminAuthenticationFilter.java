package com.chickly.PresentationLayer.Filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/adminDashboard.jsp","/addCategory", "/addProduct", "/addSubCategory", "/addsubProduct"
        , "/customerView", "/customerDetails", "/productView", "/updateSubProduct"
        , "/addCategory.jsp", "/addProduct.jsp", "/addSubCategory.jsp", "/addSubProduct.jsp"
        , "/customerDetails.jsp", "/customerProfiles", "/updateProduct", "/view-products"})  // Apply filter to these URLs
public class AdminAuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        HttpSession session = httpRequest.getSession(false);
        String role = (session != null) ? (String) session.getAttribute("role") : null;
        if (role == null || !role.equals("admin")) {
            httpResponse.sendRedirect("adminLogin.jsp");
        }else{
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
