package com.sprsec.dao.rating;

import com.sprsec.model.Rating;

import java.util.List;
import java.util.Set;

/**
 * Created by Yaroslav on 23.02.2015.
 */
public interface RatingDao {
    public void saveOrUpdate(Rating rating);
    public List<Rating> getRatingByIdFood(String id);
    public Rating getRatingByIdUserFood(String idFood, int idUser);
}
