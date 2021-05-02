package hu.latzkoo.cookbook.controller;

import hu.latzkoo.cookbook.dao.*;
import hu.latzkoo.cookbook.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@WebServlet(urlPatterns = {"/menus/add", "/menus/update"})
@MultipartConfig(fileSizeThreshold = 1048576 * 5, maxFileSize = 1048576 * 5, maxRequestSize = 1048576 * 25)
public class MenuSaveController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Validation validation = new Validation(request, new String[]{"name", "duration", "numberOfPersons", "recipeId"});

        if (validation.getErrors().size() > 0) {
            request.getSession().setAttribute("errors", validation.getErrors());
            String page = request.getParameter("id") != null ? "edit?id=" + request.getParameter("id") + "&error" : "create?error";
            response.sendRedirect(request.getContextPath() + "/menus/" + page);
        }
        else {
            request.getSession().removeAttribute("errors");

            MenuDAO menuDAO = new MenuDAOImpl();
            Menu menu = new Menu();
            String event = "add";

            if (request.getParameter("id") != null) {
                menu.setId(Integer.parseInt(request.getParameter("id")));
                event = "modify";
            }

            // Fields
            menu.setName(request.getParameter("name"));
            menu.setDuration(Integer.parseInt(request.getParameter("duration")));
            menu.setNumberOfPersons(Integer.parseInt(request.getParameter("numberOfPersons")));
            menu.setCreatedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now()));

            menu = menuDAO.save(menu);

            String[] recipeIds = request.getParameterValues("recipeId");

            if (menu.getId() > 0 && request.getParameterValues("recipeId").length > 0) {
                menuDAO.deleteRecipes(menu.getId());
                menuDAO.insertRecipes(menu.getId(),
                        Arrays.stream(recipeIds).mapToInt(Integer::parseInt).toArray());
            }

            response.sendRedirect(request.getContextPath() + "/menus?success=" + event);
        }
    }

}
