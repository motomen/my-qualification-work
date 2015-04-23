package com.goodfood.dao.impl;

import com.goodfood.dao.TypeIngredientDao;
import com.goodfood.model.TypeIngredients;
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

    @Override
    public List<TypeIngredients> getListTypeIngredients() {
        return getCurrentSession()
                .createCriteria(TypeIngredients.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public TypeIngredients getTypeIngredientByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(TypeIngredients.class);
        criteria.add(Restrictions.eq("name", name));
        return (TypeIngredients) criteria.uniqueResult();
    }
}
