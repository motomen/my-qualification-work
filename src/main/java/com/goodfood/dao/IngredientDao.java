package com.goodfood.dao;

import com.goodfood.model.Ingredient;

import java.util.List;

/**
 * Created by Yaroslav on 23.04.2015.
 */

public interface IngredientDao {
    public void addIngredient(Ingredient ingredient);
    public Ingredient getIngredientByName(String name);
    public void updateIngredient(Ingredient ingredient);
    public List<Ingredient> getListIngredient();
    public void delete(String name);
}
