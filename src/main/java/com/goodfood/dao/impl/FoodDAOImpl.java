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

import java.util.*;

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
    public List<Food> getFood(int count) {
        return getCurrentSession().createQuery(
                "select f FROM Food f order by f.rating")
                .setMaxResults(count)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public Food getFoodById(String id) {
        Food food = (Food) getCurrentSession().get(Food.class, id);
        return food;
    }

    @Override
    public List<Food> getAllFood() {
        return getCurrentSession()
                .createCriteria(Food.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public Food getFoodByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(Food.class);
        criteria.add(Restrictions.eq("name", name));
        return (Food) criteria.uniqueResult();
    }

    @Override
    public void update(Food food) {
        getCurrentSession().update(food);
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
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
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
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public List<Food> getAllFoodWithoutSubcategory() {
        String sql = "SELECT f.* FROM food f, food_to_category sc " +
                "WHERE  f.id_food_tc <> NULL AND f.id_food_tc = sc.id_fk_food AND sc.id_fk_fcategory = NULL ";
        return getCurrentSession()
                .createSQLQuery(sql)
                .addEntity(Food.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public List<String> getNameFoodForSearch(String name) {
        String sql = "SELECT f.name FROM food f " +
                "WHERE  f.name LIKE '%" + name + "%' ";
        return getCurrentSession()
                .createSQLQuery(sql)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public List<Food> getFoodForSearch(String name) {
        Criteria cr = getCurrentSession().createCriteria(Food.class);
        // To get records having fistName starting with zara
        cr.add(Restrictions.like("name", "%"+name+"%")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return cr.list();
    }

/*
    @Override
    public List<Food> getFoodForSearch(String name) {
        String sql = "SELECT f.* FROM food f " +
                "WHERE  f.name LIKE '%" + name + "%' ";
        List<Food> foodList = getCurrentSession()
                .createSQLQuery(sql)
                .addEntity(Food.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        return foodList;
    }
*/

    /**
     * get some count list foods how eat is max
     *
     * @param count return list element
     * @return list food
     */
    @Override
    public List<Food> getBestFoodEats(int count) { // try work this code in index page
        return new ArrayList<>(new HashSet(getCurrentSession().createCriteria(Food.class)
                .addOrder(Order.desc("countCalculate"))
                .setMaxResults(count)
                .list()));
    }

    @Override
    public void delete(Food food) {
        getCurrentSession().delete(food);
    }
}
