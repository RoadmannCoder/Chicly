package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "adminlogincontroller",urlPatterns = "/adminlogincontroller")
@MultipartConfig
public class AdminLoginController extends HttpServlet {
    private AdminService adminService ;
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        adminService = new AdminService();
        if (adminService.isAdminAuthenticated(req)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("role", "admin");
            session.setAttribute("adminName", req.getParameter("username"));
            resp.sendRedirect(req.getContextPath() + "/AdminDashBoardController");
        } else {
            req.setAttribute("error", "Username or password is incorrect");
            req.getRequestDispatcher("adminLogin.jsp").forward(req, resp);
        }

    }


}