package com.goodfood.service.impl;

import com.goodfood.dao.CalcDao;
import com.goodfood.model.CalcFood;
import com.goodfood.model.User;
import com.goodfood.service.CalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Yaroslav on 05.03.2015.
 */

@Service
@Transactional
public class CalcServiceImpl implements CalcService {

    @Autowired
    private CalcDao calcDao;

    /**
     * save or update list food in calc
     *
     * @param calcFood
     */
    @Override
    public void save(CalcFood calcFood) {
        calcDao.save(calcFood);
    }

    /**
     * Get all calcFood by id user sor by date
     *
     * @param user
     * @return list
     */
    @Override
    public List<CalcFood> getCalcByIdUser(User user, Date dateBefor, Date dateAfter) {
        return calcDao.getCalcByIdUser(user, dateBefor, dateAfter);
    }

    /**
     * Delete calc Food from our database
     *
     * @param calcFood
     */
    @Override
    public void delete(CalcFood calcFood) {
        calcDao.delete(calcFood);
    }

    @Override
    public CalcFood getCalcById(int id) {
        return calcDao.getCalcById(id);
    }
}
