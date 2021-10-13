package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealService {
    Meal create(Meal meal);

    void delete(int id);

    void update(Meal meal);

    Meal get(int id);

    List<Meal> getAll();

    List<MealTo> getAll(int caloriesPerDay);
}
