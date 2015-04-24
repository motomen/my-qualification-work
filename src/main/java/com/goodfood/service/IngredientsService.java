package com.goodfood.service;

import com.goodfood.model.Ingredient;

/**
 * Created by Yaroslav on 23.04.2015.
 */
public interface IngredientsService {
    public void addIngredient(Ingredient ingredient);
    public Ingredient getIngredientByName(String name);
    public void updateIngredient(Ingredient ingredient);
}
