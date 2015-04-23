package com.goodfood.dao.impl;

import com.goodfood.dao.TypeIngredientDao;
import com.goodfood.model.TypeIngredients;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Yaroslav on 23.04.2015.
 */

@Repository
public class TypeIngredientDaoImpl implements TypeIngredientDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addTypeIngredient(TypeIngredients typeIngredients) {
        getCurrentSession().save(typeIngredients);
    }
}
