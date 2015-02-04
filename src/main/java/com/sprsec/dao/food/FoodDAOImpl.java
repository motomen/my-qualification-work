package com.sprsec.dao.food;

import com.sprsec.model.Food;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Yaroslav on 01.02.2015.
 */
@Repository
public class FoodDAOImpl implements FoodDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addFood(Food food) {
        getCurrentSession().save(food);
    }

    @Override
    public List<Food> getAllFood() {
        Set<Food> foodSet =
                new HashSet<Food>(
                        getCurrentSession().createQuery(
                                "SELECT f FROM food f order by f.rating").setMaxResults(10).list()
                        );
        return new ArrayList<Food>(foodSet);
    }
}
