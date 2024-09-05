package com.chickly.DataControlLayer;

import com.chickly.DataControlLayer.Controller.Controller;
import com.chickly.DataControlLayer.Controller.ControllerFactory;
import com.chickly.DataControlLayer.ViewResolve.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FrontController extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        String controllerName = request.getParameter("controller");

        ControllerFactory factory = new ControllerFactory();
        Controller controller = factory.getController(controllerName);

        if (controller != null) {
            ViewResolver resolver = controller.resolve(request, response);
            dispatch(request, response, resolver);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Controller not found");
        }
    }

    private void dispatch(final HttpServletRequest request, final HttpServletResponse response,
                          final ViewResolver resolver) throws ServletException, IOException {

        String view = resolver.getView();
        switch (resolver.getResolveAction()) {
            case FORWARD:
                getServletContext().getRequestDispatcher(view).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(view);
                break;
            default:
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unknown resolve action");
                break;
        }
    }
}
