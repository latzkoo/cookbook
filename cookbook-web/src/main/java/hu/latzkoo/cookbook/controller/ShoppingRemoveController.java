package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.MaterialDAO;
import hu.latzkoo.cookbook.dao.MaterialDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/shopping/remove/", "/shopping/remove"})
public class ShoppingRemoveController extends HttpServlet {

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MaterialDAO materialDAO = new MaterialDAOImpl();

        if (!request.getParameter("materialId").isEmpty() && !request.getParameter("stock").isEmpty()) {
            materialDAO.updateStock("decrease", Integer.parseInt(request.getParameter("materialId")),
                    Integer.parseInt(request.getParameter("stock")));

            response.sendRedirect(request.getContextPath() + "/shopping?success=remove");
        }
    }

}
