package com.goodfood.service;

import com.goodfood.model.Food;
import com.goodfood.model.Subcategory;

import java.util.List;
import java.util.Map;

/**
 * Created by Yaroslav on 01.02.2015.
 */
public interface FoodService {
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
     * get pair name food and count eat food
     * @param count elem map
     * @return
     */
    public Map<String, Integer> getMapBestFood(int count);

    public String getStringBestFood(int count);
}
