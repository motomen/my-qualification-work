package com.goodfood.service.impl;

import com.goodfood.dao.CalcDao;
import com.goodfood.model.CalcFood;
import com.goodfood.model.User;
import com.goodfood.service.CalcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    final Logger logger = LoggerFactory.getLogger(CalcServiceImpl.class);
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
    public Double getCaloriesByIdUser(User user, Date dateBefore, Date dateAfter) {
        List<CalcFood> calcFoodList = calcDao.getCalcByIdUser(user, dateBefore, dateAfter);
        Double calories = 0.0;
        if (calcFoodList != null) {
            for (CalcFood calcs : calcFoodList) {
                if (calcs.getFood() != null) {
                    calories += calcs.getFood().getKcal() * calcs.getValue() / 100.0;
                }
            }
        } else {
            logger.error("calculate food list is empty");
        }
        return calories;
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

    /**
     * Get all CalcFood list what user eat
     *
     * @param user
     * @param dateBefore
     * @param dateAfter
     * @return
     */
    @Override
    public List<CalcFood> getListCalculateByIdUser(User user, Date dateBefore, Date dateAfter) {
        return calcDao.getCalcByIdUser(user, dateBefore, dateAfter);
    }
}
