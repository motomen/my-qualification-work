package com.goodfood.dao.impl;

import com.goodfood.dao.IngredientDao;
import com.goodfood.model.Ingredient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Yaroslav on 23.04.2015.
 */

@Repository
public class IngredientDaoImpl implements IngredientDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        getCurrentSession().save(ingredient);
    }
}
