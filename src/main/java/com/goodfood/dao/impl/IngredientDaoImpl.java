package com.goodfood.dao.impl;

import com.goodfood.dao.IngredientDao;
import com.goodfood.model.Ingredient;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public Ingredient getIngredientByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(Ingredient.class);
        criteria.add(Restrictions.eq("nameIngredient", name));
        return (Ingredient) criteria.uniqueResult();
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        getCurrentSession().update(ingredient);
    }

    @Override
    public List<Ingredient> getListIngredient() {
        return getCurrentSession()
                .createCriteria(Ingredient.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public void delete(String name) {
        getCurrentSession().delete(getIngredientByName(name));
    }
}
