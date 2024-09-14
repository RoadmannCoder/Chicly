package com.chickly.PresentationLayer.Filters;
import com.chickly.BussinesLayer.CustomerService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/register")
public class UsernameValidationFilter implements Filter {

    private CustomerService customerService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        customerService = new CustomerService();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String checkType = request.getParameter("checkType");
        String un = request.getParameter("uName");

        if ("usernamecheck".equalsIgnoreCase(checkType) && !un.isEmpty()) {
            PrintWriter out = response.getWriter();
            String userName = request.getParameter("uName");
            if (customerService.usernameValidation(userName)) {
                out.print("This username already exists");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
