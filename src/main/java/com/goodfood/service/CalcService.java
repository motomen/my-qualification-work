package com.goodfood.service;

import com.goodfood.model.CalcFood;
import com.goodfood.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Yaroslav on 05.03.2015.
 */
public interface CalcService {
    /**
     * save or update list food in calc
     * @param calcFood
     */
    public void save(CalcFood calcFood);

    /**
     * Get all calories by id user sor by date
     * @param user
     * @return list
     */
    public Double getCaloriesByIdUser(User user, Date dateBefore, Date dateAfter);

    /**
     * Delete calc Food from our database
     * @param calcFood
     */
    public void delete(CalcFood calcFood);

    public CalcFood getCalcById(int id);

    /**
     * Get all CalcFood list what user eat
     * @param user
     * @param dateBefore
     * @param dateAfter
     * @return
     */
    public List<CalcFood> getListCalculateByIdUser(User user, Date dateBefore, Date dateAfter);
}
