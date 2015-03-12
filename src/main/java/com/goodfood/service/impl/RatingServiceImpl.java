package com.goodfood.service.impl;

import com.goodfood.dao.RatingDao;
import com.goodfood.model.Rating;
import com.goodfood.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yaroslav on 23.02.2015.
 */

@Service
@Transactional
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingDao ratingDao;

    @Override
    public void saveOrUpdate(Rating rating) {
        ratingDao.saveOrUpdate(rating);
    }

    @Override
    public List<Rating> getRatingByIdFood(String id) {
        return ratingDao.getRatingByIdFood(id);
    }

    @Override
    public Rating getRatingByIdUserFood(String idFood, int idUser) {
        return ratingDao.getRatingByIdUserFood(idFood, idUser);
    }
}
