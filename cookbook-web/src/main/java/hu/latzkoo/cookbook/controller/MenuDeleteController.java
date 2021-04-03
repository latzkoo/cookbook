package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.MenuDAO;
import hu.latzkoo.cookbook.dao.MenuDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/menus/delete"})
public class MenuDeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MenuDAO menuDAO = new MenuDAOImpl();
        menuDAO.delete(Integer.parseInt(request.getParameter("id")));

        response.sendRedirect(request.getContextPath() + "/menus?success=delete");
    }
}
