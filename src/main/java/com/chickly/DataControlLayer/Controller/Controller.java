package com.chickly.DataControlLayer.Controller;

import com.chickly.DataControlLayer.ViewResolve.ViewResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Controller {
    ViewResolver resolve(final HttpServletRequest request, final HttpServletResponse response);
}
