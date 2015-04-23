package com.goodfood.dao;

import com.goodfood.model.TypeIngredients;

import java.util.List;

/**
 * Created by Yaroslav on 23.04.2015.
 */
public interface TypeIngredientDao {
    public void addTypeIngredient(TypeIngredients typeIngredients);
    public List<TypeIngredients> getListTypeIngredients();
    public TypeIngredients getTypeIngredientByName(String name);
}
