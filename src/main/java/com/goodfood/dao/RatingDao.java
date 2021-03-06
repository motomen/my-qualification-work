package com.goodfood.dao;

import com.goodfood.model.Rating;

import java.util.List;

/**
 * Created by Yaroslav on 23.02.2015.
 */
public interface RatingDao {
    public void saveOrUpdate(Rating rating);
    public List<Rating> getRatingByIdFood(String id);
    public Rating getRatingByIdUserFood(String idFood, int idUser);
}
