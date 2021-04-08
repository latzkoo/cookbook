package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.MenuDAO;
import hu.latzkoo.cookbook.dao.MenuDAOImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/menus/show")
public class MenuShowController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MenuDAO menuDao = new MenuDAOImpl();
        request.setAttribute("menu", menuDao.findById(Integer.parseInt(request.getParameter("id"))));

        request.getRequestDispatcher("/layouts/menus/show.jsp").forward(request, response);
    }
}
