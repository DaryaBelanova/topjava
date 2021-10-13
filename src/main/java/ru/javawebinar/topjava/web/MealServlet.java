package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepositoryImplInMemory;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private MealController controller;

    public MealServlet() {
        this.controller = new MealController(new MealServiceImpl(new MealRepositoryImplInMemory()));
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");

        String action = req.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete": {
                int id = Integer.parseInt(req.getParameter("id"));
                controller.delete(id);
                resp.sendRedirect("meals");
                break;
            }
            case "create":
            case "update":
                final Meal meal = action.equals("create") ? new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        controller.get(Integer.parseInt(req.getParameter("id")));
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("/mealForm.jsp").forward(req, resp);
                break;
            case "all":
            default:
                req.setAttribute("meals", controller.getAll(MealsUtil.CALORIES_RATE_PER_DAY));
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
                break;
        }

//        req.setAttribute("meals", MealsUtil.filteredByStreams(MealsUtil.meals, MealsUtil.CALORIES_RATE_PER_DAY));
//        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");

        Meal meal = new Meal(
                LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories"))
        );

        meal.setId(id.isEmpty() ? null : Integer.valueOf(id));

        if (meal.isNew()) {
            controller.create(meal);
        }
        else {
            controller.update(meal);
        }
        resp.sendRedirect("meals");
    }
}
