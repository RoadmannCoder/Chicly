package com.chickly.PresentationLayer.Controller;

import com.chickly.BussinesLayer.AuthenticationService;
import com.chickly.DataAccessLayer.Entities.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    AuthenticationService authenticationService = new AuthenticationService();
    HomeController controller = new HomeController();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = (Customer) req.getAttribute("user");
        if(customer != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", customer);
            resp.sendRedirect("/chicly/");
        }else{
            doGet(req,resp);
        }


    }
}
