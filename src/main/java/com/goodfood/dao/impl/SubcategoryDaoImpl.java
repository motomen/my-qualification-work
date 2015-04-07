package com.goodfood.dao.impl;

import com.goodfood.dao.SubcategoryDao;
import com.goodfood.model.Subcategory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Yaroslav on 04.02.2015.
 */

@Repository
public class SubcategoryDaoImpl implements SubcategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * add new Subcategory
     *
     * @param subcategory
     */
    @Override
    public void addSubcategory(Subcategory subcategory) {
        getCurrentSession().save(subcategory);
    }

    /**
     * Return Subcategory by name
     *
     * @param name
     */
    @Override
    public Subcategory getSubcategoryByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(Subcategory.class);
        criteria.add(Restrictions.eq("name", name));
        return (Subcategory) criteria
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .uniqueResult();
    }

    /**
     * Get all subcategory
     *
     * @return List subcategory
     */
    @Override
    public List<Subcategory> getAllSubcategory() {
        return getCurrentSession()
                .createCriteria(Subcategory.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public Subcategory getCategoryById(int id) {
        Criteria criteria = getCurrentSession().createCriteria(Subcategory.class);
        criteria.add(Restrictions.eq("idSubCategory", id));
        return (Subcategory) criteria
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .uniqueResult();
    }
}
