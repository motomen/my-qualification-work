package com.sprsec.service.food;

import com.sprsec.model.Food;

import java.util.List;

/**
 * Created by Yaroslav on 01.02.2015.
 */
public interface FoodService {
    public void addFood(Food food);
    public List<Food> getTenFood();
    public Food getFoodById(String id);
    public List<Food> getAllFood();
    public Food getFoodByName(String name);
    public void update(Food food);
}
