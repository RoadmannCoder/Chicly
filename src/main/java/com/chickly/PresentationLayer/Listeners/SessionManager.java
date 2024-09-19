package com.chickly.PresentationLayer.Listeners;

import com.chickly.BussinesLayer.CartService;
import com.chickly.DataAccessLayer.Entities.Customer;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.ArrayList;

public class SessionManager implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Customer customer = (Customer) se.getSession().getAttribute("user");
        CartService cartService = (CartService) se.getSession().getAttribute("cart");
        HttpSession session = se.getSession();
        if(cartService!=null&&customer!=null) {
            cartService.addToDB(new ArrayList<>(),customer,cartService);
        }

    }
}
