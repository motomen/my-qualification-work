package com.goodfood.dao.impl;

import com.goodfood.dao.CommentsDao;
import com.goodfood.model.Comments;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yaroslav on 18.02.2015.
 */

@Repository
public class CommentDaoImpl implements CommentsDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * save comment in data base
     *
     * @param comments
     */
    @Override
    public void save(Comments comments) {
        getCurrentSession().save(comments);
    }

    /**
     * return all comments food
     *
     * @param id
     * @return list comments
     */
    @Override
    public List<Comments> getCommentByIdFood(String id) {
        List commentsList;
        String sql = "Select co.* from comment_to_food f, comments co where co.id_comment = f.fk_idcomment and f.fk_food = :id";
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("id", id);
        query.addEntity(Comments.class);
        commentsList = query
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        return commentsList;
    }

    /**
     * delete comments
     *
     * @param comments
     */
    @Override
    public void delete(Comments comments) {
        getCurrentSession().delete(comments);
    }

    /**
     * Get comment by id
     *
     * @param id
     * @return Comment
     */
    @Override
    public Comments getCommentById(int id) {
        Comments comment = (Comments) getCurrentSession().get(Comments.class, id);
        return comment;
    }

    /**
     * get ten comment by last date
     *
     * @return
     */
    @Override
    public List<Comments> getLastComment(int count) {
        Criteria criteria = getCurrentSession().createCriteria(Comments.class)
        .addOrder(Order.desc("dateComment")).setMaxResults(count);
        return criteria
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }


}
