package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.MaterialDAO;
import hu.latzkoo.cookbook.dao.MaterialDAOImpl;
import hu.latzkoo.cookbook.model.Pager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/materials")
public class MaterialListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MaterialDAO materialDAO = new MaterialDAOImpl();

        boolean outOfStock = request.getParameter("status") != null && request.getParameter("status").equals("outOfStock");

        int count = materialDAO.count(outOfStock, request.getParameter("q"));
        request.setAttribute("count", count);

        Pager pager = new Pager((request.getParameter("page") != null ?
                Integer.parseInt(request.getParameter("page")) : 0), count);
        request.setAttribute("pager", pager);
        request.setAttribute("materials", materialDAO.findAll(outOfStock, request.getParameter("q"), pager));

        request.getRequestDispatcher("/layouts/materials/list.jsp").forward(request, response);
    }
}
