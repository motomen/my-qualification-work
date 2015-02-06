package com.sprsec.dao.category;

import com.sprsec.model.Category;
import com.sprsec.model.Subcategory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        Criteria criteria = getCurrentSession().createCriteria(Category.class);
        criteria.add(Restrictions.eq("name", name));
        return (Subcategory) criteria.uniqueResult();
    }
}
