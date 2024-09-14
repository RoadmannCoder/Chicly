package com.chickly.PresentationLayer.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet(name = "adminlogout",urlPatterns = "/adminlogout")
@MultipartConfig
public class AdminLogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // Get session, if exists
        if (session != null) {
            // Remove only the specific "role" attribute
            session.removeAttribute("role");
            session.removeAttribute("admineName");
        }
        resp.sendRedirect("adminLogin.jsp"); // Redirect to login page after logout
    }
}
