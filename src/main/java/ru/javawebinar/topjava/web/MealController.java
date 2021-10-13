package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

public class MealController {
    private MealService service;

    public MealController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll(int caloriesPerDay) {
        return service.getAll(caloriesPerDay);
    }

    public Meal get(int id) {
        return service.get(id);
    }

    public Meal create(Meal meal) {
        return service.create(meal);
    }

    public void update(Meal meal) {
        service.update(meal);
    }

    public void delete(int id) {
        service.delete(id);
    }
}
