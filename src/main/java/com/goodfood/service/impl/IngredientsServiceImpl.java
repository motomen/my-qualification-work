package com.goodfood.service.impl;

import com.goodfood.dao.IngredientDao;
import com.goodfood.model.Ingredient;
import com.goodfood.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yaroslav on 23.04.2015.
 */
@Service
@Transactional
public class IngredientsServiceImpl implements IngredientsService {

    @Autowired
    private IngredientDao ingredientDao;

    @Override
    public void addIngredient(Ingredient ingredient) {
        ingredientDao.addIngredient(ingredient);
    }

    @Override
    public Ingredient getIngredientByName(String name) {
        return ingredientDao.getIngredientByName(name);
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        ingredientDao.updateIngredient(ingredient);
    }

    @Override
    public List<Ingredient> getListIngredient() {
        return ingredientDao.getListIngredient();
    }

    @Override
    public void delete(String name) {
        ingredientDao.delete(name);
    }
}
