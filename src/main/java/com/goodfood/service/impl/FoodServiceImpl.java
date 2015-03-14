package com.goodfood.service.impl;

import com.goodfood.dao.FoodDAO;
import com.goodfood.model.Food;
import com.goodfood.model.Subcategory;
import com.goodfood.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Food> getTenFood() {
        return foodDAO.getTenFood();
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
}
