package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

public class MealServiceImpl implements MealService {
    private final MealRepository repository;

    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }
    @Override
    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public void update(Meal meal) {
        repository.save(meal);
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return repository.getAll();
    }

    @Override
    public List<MealTo> getAll(int caloriesPerDay) {
        return MealsUtil.filteredByStreams(repository.getAll(), caloriesPerDay);
    }
}
