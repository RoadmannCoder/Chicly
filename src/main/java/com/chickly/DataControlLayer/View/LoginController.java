package com.chickly.DataControlLayer.View;


import com.chickly.DataControlLayer.Controller.Controller;
import com.chickly.DataControlLayer.ViewResolve.ViewResolver;
import com.chickly.DataServiceLayer.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginController implements Controller {
    private static final String LOGIN_JSP = "/login.jsp";

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ViewResolver resolver = new ViewResolver();

        if (email == null || password == null) {
            resolver.forward(LOGIN_JSP);
            return resolver;
        }

        CustomerService customerService = new CustomerService();

        boolean isValidUser = customerService.authenticateUser(email, password);

        if (isValidUser) {
            resolver.forward("/FrontController?controller=home"); // Redirect to home page
        } else {
            request.setAttribute("errorMessage", "Invalid email or password");
            resolver.forward(LOGIN_JSP);
        }

        return resolver;
    }
}
