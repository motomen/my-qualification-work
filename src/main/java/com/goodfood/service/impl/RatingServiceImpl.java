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
    public Double getRatingByIdFood(String id) {
        List<Rating> ratingSet = ratingDao.getRatingByIdFood(id);
        double val=0.0;
        if (ratingSet.size() > 0) {
            for (Rating rSet : ratingSet) {
                val += rSet.getValue();
            }
            val /= ratingSet.size();
        }
        return val;
    }

    @Override
    public Double getRatingByIdUserIdFood(String idFood, int idUser) {
        Rating rating = ratingDao.getRatingByIdUserFood(idFood, idUser);
        if (rating != null) {
            return rating.getValue();
        } else {
            return 0.0;
        }
    }

    @Override
    public int getIdRatingByIdUserIdFood(String idFood, int idUser) {
        Rating rating = ratingDao.getRatingByIdUserFood(idFood, idUser);
        if (rating != null) {
            return rating.getIdRating();
        } else {
            return -1;
        }
    }


}
