package com.chickly.PresentationLayer.Validators;

import com.chickly.BussinesLayer.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "emailValidator", urlPatterns = "/emailValidator")
public class EmailValidator extends HttpServlet {
    private CustomerService customerService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");
        customerService = new CustomerService();

        if (email == null || email.trim().isEmpty()) {
            out.print("Email is required");
            return;
        }
        if (customerService.emailValidation(email)) {
            out.print("This email is already registered");
        }
    }
}
