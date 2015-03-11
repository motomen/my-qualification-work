package com.sprsec.service;

import com.sprsec.model.CalcFood;
import com.sprsec.model.User;

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
     * Get all calcFood by id user sor by date
     * @param user
     * @return list
     */
    public List<CalcFood> getCalcByIdUser(User user, Date dateBefor, Date dateAfter);

    /**
     * Delete calc Food from our database
     * @param calcFood
     */
    public void delete(CalcFood calcFood);

    public CalcFood getCalcById(int id);
}
