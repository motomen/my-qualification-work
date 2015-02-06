package com.sprsec.service.food;

import com.sprsec.dao.food.FoodDAO;
import com.sprsec.model.Food;
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
    public List<Food> getAllFood() {
        return foodDAO.getAllFood();
    }

    @Override
    public Food getFoodById(String id) {
        return foodDAO.getFoodById(id);
    }
}
