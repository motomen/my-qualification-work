package com.goodfood.dao.impl;

import com.goodfood.dao.CalcDao;
import com.goodfood.model.CalcFood;
import com.goodfood.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by Yaroslav on 05.03.2015.
 */

@Repository
public class CalcDAOImpl implements CalcDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * save or update list food in calc
     *
     * @param calcFood
     */
    @Override
    public void save(CalcFood calcFood) {
        getCurrentSession().saveOrUpdate(calcFood);
    }

    /**
     * Get all calcFood by id user sor by date
     *
     * @param user
     * @return list
     */
    @Override
    public List<CalcFood> getCalcByIdUser(User user, Date dateBefore, Date dateAfter) {
        Criteria criteria = getCurrentSession().createCriteria(CalcFood.class)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.between("addDate", dateBefore, dateAfter))
                .addOrder(Order.desc("addDate"));
        return new ArrayList<>(new TreeSet(criteria.list()));
    }

    /**
     * Delete calc Food from our database
     *
     * @param calcFood
     */
    @Override
    public void delete(CalcFood calcFood) {
        getCurrentSession().delete(calcFood);
    }

    @Override
    public CalcFood getCalcById(int id) {
        Criteria criteria = getCurrentSession().createCriteria(CalcFood.class)
                .add(Restrictions.eq("idCalc", id));
        return (CalcFood) criteria.uniqueResult();
    }


}
