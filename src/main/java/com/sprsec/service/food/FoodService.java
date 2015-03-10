package com.sprsec.service.food;

import com.sprsec.model.Food;
import com.sprsec.model.Subcategory;

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
    public List<Food> getAllFoodBySubcategory(int page, int maxResults, Subcategory subcategory);
    public List<Food> getAllFoodBySubcategory(Subcategory subcategory);
}
