package com.chickly.PresentationLayer.Validators;

import com.chickly.BussinesLayer.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "phoneValidator", urlPatterns = "/phoneValidator")
public class PhoneValidator extends HttpServlet {

    private CustomerService customerService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        String phoneNo = req.getParameter("phoneNo");
        customerService = new CustomerService();
        if (phoneNo == null || phoneNo.trim().isEmpty()) {
            out.print("Phone number is required");
            return;
        }
        if (customerService.phoneNumberValidation(phoneNo)) {
            out.print("This phone number is already registered");
        }
    }
}
