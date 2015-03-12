package com.goodfood.dao.impl;

import com.goodfood.dao.CategoryDao;
import com.goodfood.model.Category;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Yaroslav on 03.02.2015.
 */
@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addCategory(Category category) {
       getCurrentSession().save(category);
    }

    /**
     * Get all Category
     */
    @Override
    public List<Category> allCategory() {
        HashSet<Category> setCategory = new HashSet<>(getCurrentSession().createCriteria(Category.class).list());
        return new ArrayList<Category>(setCategory);
    }

    /**
     * Return category by name
     *
     * @param name
     */
    @Override
    public Category getCategoryByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(Category.class);
        criteria.add(Restrictions.eq("name", name));
        return (Category) criteria.uniqueResult();
    }


}
