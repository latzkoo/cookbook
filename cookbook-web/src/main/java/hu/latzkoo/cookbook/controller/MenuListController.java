package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.MenuDAO;
import hu.latzkoo.cookbook.dao.MenuDAOImpl;
import hu.latzkoo.cookbook.model.Pager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/menus", "/menus/"})
public class MenuListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MenuDAO menuDAO = new MenuDAOImpl();

        int count = menuDAO.count(request.getParameter("q"));
        request.setAttribute("count", count);

        Pager pager = new Pager((request.getParameter("page") != null ?
                Integer.parseInt(request.getParameter("page")) : 0), count);
        request.setAttribute("pager", pager);
        request.setAttribute("menus", menuDAO.findAll(request.getParameter("q"), pager));

        request.getRequestDispatcher("/layouts/menus/list.jsp").forward(request, response);
    }
}
