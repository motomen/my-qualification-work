package com.goodfood.dao.impl;

import com.goodfood.dao.RatingDao;
import com.goodfood.model.Rating;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yaroslav on 23.02.2015.
 */

@Repository
public class RatingDaoImpl implements RatingDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveOrUpdate(Rating rating) {
        getCurrentSession().saveOrUpdate(rating);
    }

    @Override
    public List<Rating> getRatingByIdFood(String id) {
        // you have get all rating by id and display on page
        String sql = "Select r.* from rating r where r.fk_food_tc = :id";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("id", id);
        query.addEntity(Rating.class);
        return query.list();
    }

    @Override
    public Rating getRatingByIdUserFood(String idFood, int idUser) {
        String sql = "Select r.* from rating r where r.fk_food_tc = :idFood and r.id_user = :idUser ";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("idFood", idFood);
        query.setParameter("idUser", idUser);
        query.addEntity(Rating.class);
        return (Rating) query.uniqueResult();
    }
}
