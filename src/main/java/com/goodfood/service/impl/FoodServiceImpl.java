package com.goodfood.service.impl;

import com.goodfood.dao.FoodDAO;
import com.goodfood.model.Food;
import com.goodfood.model.Subcategory;
import com.goodfood.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yaroslav on 01.02.2015.
 */
@Service
@Transactional
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodDAO foodDAO;

    @Override
    public void addFood(Food food) {
        foodDAO.addFood(food);
    }

    @Override
    public List<Food> getFood(int count) {
        return foodDAO.getFood(count);
    }

    @Override
    public Food getFoodById(String id) {
        return foodDAO.getFoodById(id);
    }

    @Override
    public List<Food> getAllFood() {
        return foodDAO.getAllFood();
    }

    @Override
    public Food getFoodByName(String name) {
        return foodDAO.getFoodByName(name);
    }

    @Override
    public void update(Food food) {
        foodDAO.update(food);
    }

    @Override
    public List<Food> getAllFoodBySubcategory(int page, int maxResults, Subcategory subcategory) {
        return foodDAO.getAllFoodBySubcategory(page, maxResults, subcategory);
    }

    @Override
    public List<Food> getAllFoodBySubcategory(Subcategory subcategory) {
        return foodDAO.getAllFoodBySubcategory(subcategory);
    }

    @Override
    public List<Food> getAllFoodWithoutSubcategory() {
        return foodDAO.getAllFoodWithoutSubcategory();
    }

    public List<String> getNameFoodForSearch(String name){
        return foodDAO.getNameFoodForSearch(name);
    }

    @Override
    public List<Food> getFoodForSearch(String name) {
        return foodDAO.getFoodForSearch(name);
    }

    /**
     * get pair name food and count eat food
     *
     * @param count elem map
     * @return
     */
    @Override
    public Map<String, Integer> getMapBestFood(int count) {
        Map<String, Integer> foodMap = new HashMap();
        List<Food> foodList = foodDAO.getBestFoodEats(count);
        for (Food food: foodList) {
            foodMap.put(food.getName(), food.getCalcFoodList().size());
        }
        return foodMap;
    }

    @Override
    public String getStringBestFood(int count) {
        List<Food> foodList = foodDAO.getBestFoodEats(count);
        String result = "";
        for (Food food: foodList) {
            result += "[\'" + food.getName() + "\', " + String.valueOf(food.getCalcFoodList().size()) + "], ";
        }
        result = result.substring(0, result.length()-2);
        return result;
    }
}
