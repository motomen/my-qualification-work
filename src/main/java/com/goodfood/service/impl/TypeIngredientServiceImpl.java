package com.goodfood.service.impl;

import com.goodfood.dao.TypeIngredientDao;
import com.goodfood.model.TypeIngredients;
import com.goodfood.service.TypeIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yaroslav on 23.04.2015.
 */

@Service
@Transactional
public class TypeIngredientServiceImpl implements TypeIngredientService {

    @Autowired
    private TypeIngredientDao typeIngredientDao;

    @Override
    public void addTypeIngredient(TypeIngredients typeIngredients) {
        typeIngredientDao.addTypeIngredient(typeIngredients);
    }

    @Override
    public List<TypeIngredients> getListTypeIngredients() {
        return typeIngredientDao.getListTypeIngredients();
    }

    @Override
    public TypeIngredients getTypeIngredientByName(String name) {
        return typeIngredientDao.getTypeIngredientByName(name);
    }
}
