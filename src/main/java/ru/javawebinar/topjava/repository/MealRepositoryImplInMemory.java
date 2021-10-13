package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealRepositoryImplInMemory implements MealRepository{
    private AtomicInteger counter = new AtomicInteger(0);

    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();

    {
        for (Meal meal : MealsUtil.meals) {
            save(meal);
        }
    }


    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        Meal newMeal = repository.put(meal.getId(), meal);
        return newMeal;
    }

    @Override
    public void delete(int id) {
        if (repository.containsKey(id)) {
            repository.remove(id);
        }
    }

    @Override
    public Meal get(int id) {
        return repository.getOrDefault(id, new Meal(null, null, 0));
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(repository.values());
    }
}
