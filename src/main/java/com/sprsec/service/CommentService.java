package com.sprsec.service;

import com.sprsec.model.Comments;

import java.util.List;

/**
 * Created by Yaroslav on 18.02.2015.
 */
public interface CommentService {
    /**
     * save comment in data base
     * @param comments
     */
    public void save(Comments comments);

    /**
     * return all comments food
     * @param id
     * @return list comments
     */
    public List<Comments> getCommentByIdFood(String id);

    /**
     * delete comments
     * @param comments
     */
    public void delete(Comments comments);

    /**
     * Get comment by id
     * @param id
     * @return Comment
     */
    public Comments getCommentById(int id);

    /**
     * get ten comment by last date
     * @return
     */
    public List<Comments> getLastTenComment();
}
