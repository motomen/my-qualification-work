package com.goodfood.dao;

import com.goodfood.model.Food;
import com.goodfood.model.Subcategory;

import java.util.List;

/**
 * Created by Yaroslav on 01.02.2015.
 */
public interface FoodDAO {
    public void addFood(Food food);
    public List<Food> getFood(int count);
    public Food getFoodById(String id);
    public List<Food> getAllFood();
    public Food getFoodByName(String name);
    public void update(Food food);
    public List<Food> getAllFoodBySubcategory(int page, int maxResults, Subcategory subcategory);
    public List<Food> getAllFoodBySubcategory(Subcategory subcategory);
    public List<Food> getAllFoodWithoutSubcategory();
    public List<String> getNameFoodForSearch(String name);
    public List<Food> getFoodForSearch(String name);

    /**
     * get some count list foods how eat is max
     * @param count return list element
     * @return list food
     */
    public List<Food> getBestFoodEats(int count);
    public void delete(Food food);
}
