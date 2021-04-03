package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/shopping/", "/shopping"})
public class ShoppingController extends HttpServlet {

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MaterialDAO materialDAO = new MaterialDAOImpl();
        request.setAttribute("materials", materialDAO.findAll(false));

        MeasureDAO measureDAO = new MeasureDAOImpl();
        request.setAttribute("measures", measureDAO.findAll());

        request.getRequestDispatcher("/layouts/shopping/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("materialId").isEmpty() && !request.getParameter("stock").isEmpty()) {
            MaterialDAO materialDAO = new MaterialDAOImpl();

            materialDAO.updateStock("increase", Integer.parseInt(request.getParameter("materialId")),
                    Integer.parseInt(request.getParameter("stock")));

            response.sendRedirect(request.getContextPath() + "/shopping?success=add&materialId=" +
                    request.getParameter("materialId") + "&stock=" + request.getParameter("stock"));
        }
    }
}
