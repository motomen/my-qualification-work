package com.goodfood.service.impl;

import com.goodfood.dao.CommentsDao;
import com.goodfood.model.Comments;
import com.goodfood.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yaroslav on 18.02.2015.
 */

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentsDao commentDao;
    /**
     * save comment in data base
     *
     * @param comments
     */
    @Override
    public void save(Comments comments) {
        commentDao.save(comments);
    }

    /**
     * return all comments food
     *
     * @param id
     * @return list comments
     */
    @Override
    public List<Comments> getCommentByIdFood(String id) {
        return commentDao.getCommentByIdFood(id);
    }

    /**
     * delete comments
     *
     * @param comments
     */
    @Override
    public void delete(Comments comments) {
        commentDao.delete(comments);
    }

    /**
     * Get comment by id
     *
     * @param id
     * @return Comment
     */
    @Override
    public Comments getCommentById(int id) {
        return commentDao.getCommentById(id);
    }

    /**
     * get ten comment by last date
     *
     * @return
     */
    @Override
    public List<Comments> getLastComment(int count) {
        return commentDao.getLastComment(count);
    }


}
