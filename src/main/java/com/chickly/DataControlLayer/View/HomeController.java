package com.chickly.DataControlLayer.View;

import com.chickly.DataControlLayer.Controller.Controller;
import com.chickly.DataControlLayer.ViewResolve.ViewResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeController implements Controller {
    private static final String HOME_JSP = "/index.jsp";

    @Override
    public ViewResolver resolve(HttpServletRequest request, HttpServletResponse response) {
        ViewResolver resolver = new ViewResolver();
        resolver.forward(HOME_JSP); // Stay on login page with error

        return  resolver;
    }
}
