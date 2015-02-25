package com.sprsec.dao.rating;

import com.sprsec.model.Rating;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

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
    public Set<Rating> getRatingByIdFood(String id) {
        // you have get all rating by id and display on page
        String sql = "Select r.* from rating r where r.fk_food_tc = :id";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("id", id);
        // query.addEntity(Comments.class);
        return new HashSet<Rating>(query.list());
    }

    @Override
    public Rating getRatingByIdUserFood(String idFood, int idUser) {
        String sql = "Select r.* from rating r where r.fk_food_tc = :idFood and r.id_user = :idUser ";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("idFood", idFood);
        query.setParameter("idUser", idUser);
        return (Rating) query.uniqueResult();
    }
}
