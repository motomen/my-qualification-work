package com.sprsec.service.comments;

import com.sprsec.dao.comment.CommentsDao;
import com.sprsec.model.Comments;
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


}
