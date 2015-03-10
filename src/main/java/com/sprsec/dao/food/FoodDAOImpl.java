package com.sprsec.dao.food;

import com.sprsec.model.Food;
import com.sprsec.model.Role;
import com.sprsec.model.Subcategory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.criterion.Restrictions;
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
    public List<Food> getTenFood() {
        Set<Food> foodSet =
                new HashSet<Food>(
                        getCurrentSession().createQuery(
                                "select f FROM Food f order by f.rating").setMaxResults(10).list()
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
}
