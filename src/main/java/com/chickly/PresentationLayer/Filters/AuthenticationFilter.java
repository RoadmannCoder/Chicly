package com.chickly.PresentationLayer.Filters;


import com.chickly.BussinesLayer.AuthenticationService;
import com.chickly.DataAccessLayer.Entities.Customer;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = "/login")
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        if("POST".equalsIgnoreCase(httpRequest.getMethod())){
            AuthenticationService authenticationService = new AuthenticationService();
            Customer customer = authenticationService.isAuthenticatedCustomer(servletRequest.getParameter("username"),servletRequest.getParameter("password"));
            Optional.ofNullable(customer).ifPresentOrElse(
                    obj -> {
                        servletRequest.setAttribute("user", customer);
                        try {
                            filterChain.doFilter(servletRequest, servletResponse);
                        } catch (IOException | ServletException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    () -> {
                        servletRequest.setAttribute("error", "Invalid Username Or Password");
                        try {
                            servletRequest.getRequestDispatcher("login").forward(servletRequest, servletResponse);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );

        }
        else
            filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
