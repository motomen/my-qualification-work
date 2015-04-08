package com.goodfood.service;

import com.goodfood.model.Rating;


/**
 * Created by Yaroslav on 23.02.2015.
 */
public interface RatingService {
    public void saveOrUpdate(Rating rating);
    public Double getRatingByIdFood(String id);
    public Double getRatingByIdUserIdFood(String idFood, int idUser);
    public int getIdRatingByIdUserIdFood(String idFood, int idUser);
}
