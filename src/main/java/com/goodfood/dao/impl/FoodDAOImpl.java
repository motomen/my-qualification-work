package com.goodfood.dao.impl;

import com.goodfood.dao.FoodDAO;
import com.goodfood.model.Food;
import com.goodfood.model.Subcategory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Yaroslav on 01.02.2015.
 */
@Repository
@Transactional
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
    public List<Food> getFood(int count) {
        Set<Food> foodSet =
                new HashSet<Food>(
                        getCurrentSession().createQuery(
                                "select f FROM Food f order by f.rating").setMaxResults(count).list()
                        );
        return new ArrayList<Food>(foodSet);
    }

    @Override
    public Food getFoodById(String id) {
        Food food = (Food) getCurrentSession().get(Food.class, id);
        return food;
    }

    @Override
    public List<Food> getAllFood() {
        return new ArrayList<Food>(new HashSet <Food> (getCurrentSession().createCriteria(Food.class).list()));
    }

    @Override
    public Food getFoodByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(Food.class);
        criteria.add(Restrictions.eq("name", name));
        return (Food) criteria.uniqueResult();
    }

    @Override
    public void update(Food food) {
        getCurrentSession().merge(food);
    }

    @Override
    public List<Food> getAllFoodBySubcategory(int page, int maxResults, Subcategory subcategory) {
        page = page * maxResults - maxResults;
        String sql = "SELECT f.* FROM food f, sub_category sc, food_to_category fc WHERE f.id_food_tc = fc.id_fk_food " +
                "AND sc.id_sub_category = fc.id_fk_fcategory AND sc.id_sub_category = :id ORDER BY f.rating DESC ";
        return getCurrentSession()
                .createSQLQuery(sql)
                .addEntity(Food.class)
                .setParameter("id", subcategory.getIdSubCategory())
                .setFirstResult(page)
                .setMaxResults(maxResults)
                .list();
    }

    @Override
    public List<Food> getAllFoodBySubcategory(Subcategory subcategory) {
        String sql = "SELECT f.* FROM food f, sub_category sc, food_to_category fc WHERE f.id_food_tc = fc.id_fk_food " +
                "AND sc.id_sub_category = fc.id_fk_fcategory AND sc.id_sub_category = :id ORDER BY f.rating DESC ";
        return getCurrentSession()
                .createSQLQuery(sql)
                .addEntity(Food.class)
                .setParameter("id", subcategory.getIdSubCategory())
                .list();
    }

    @Override
    public List<Food> getAllFoodWithoutSubcategory() {
        String sql = "SELECT f.* FROM food f, food_to_category sc " +
                "WHERE  f.id_food_tc <> NULL AND f.id_food_tc = sc.id_fk_food AND sc.id_fk_fcategory = NULL ";
        return getCurrentSession()
                .createSQLQuery(sql)
                .addEntity(Food.class)
                .list();
    }

    @Override
    public List<String> getNameFoodForSearch(String name) {
        String sql = "SELECT f.name FROM food f " +
                "WHERE  f.name LIKE '%"+name+"%' ";
        return getCurrentSession()
                .createSQLQuery(sql)
                .list();
    }

    @Override
    public List<Food> getFoodForSearch(String name) {
        String sql = "SELECT f.* FROM food f " +
                "WHERE  f.name LIKE '%"+name+"%' ";
        List<Food> foodList = getCurrentSession()
                .createSQLQuery(sql)
                .addEntity(Food.class)
                .list();
        return new ArrayList<>(new HashSet(foodList));
    }

    /**
     * get some count list foods how eat is max
     *
     * @param count return list element
     * @return list food
     */
    @Override
    public List<Food> getBestFoodEats(int count) { // try work this code in index page
        return getCurrentSession().createCriteria(Food.class)
                .setMaxResults(count)
                .list();
    }
}
