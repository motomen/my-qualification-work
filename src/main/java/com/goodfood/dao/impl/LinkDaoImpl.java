package com.goodfood.dao.impl;

import com.goodfood.dao.LinkDao;
import com.goodfood.model.Link;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Yaroslav on 24.04.2015.
 */

@Repository
public class LinkDaoImpl implements LinkDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addLink(Link link) {
        getCurrentSession().save(link);
    }
}
