package com.sprsec.dao.food;

import com.sprsec.model.Food;

import java.util.List;

/**
 * Created by Yaroslav on 01.02.2015.
 */
public interface FoodDAO {
    public void addFood(Food food);
    public List<Food> getTenFood();
    public Food getFoodById(String id);
    public List<Food> getAllFood();
    public Food getFoodByName(String name);
    public void update(Food food);
}
