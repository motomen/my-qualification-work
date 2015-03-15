package com.goodfood.controller;

import com.goodfood.auth.IAuthenticationFacade;
import com.goodfood.model.CalcFood;
import com.goodfood.model.Food;
import com.goodfood.model.User;
import com.goodfood.service.CalcService;
import com.goodfood.service.FoodService;
import com.goodfood.service.UserService;
import com.goodfood.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by Yaroslav on 05.03.2015.
 */

@Controller
public class CalcController {

    final Logger logger = LoggerFactory.getLogger(CalcController.class);

    @Autowired
    private CalcService calcService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private IAuthenticationFacade authentication;

    @Autowired
    private UserService userService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/calc/save/{id}/{calcval}", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveCalc(@PathVariable("calcval") double calcval,
                            @PathVariable("id") String id) {
        if (calcval <= 0.0) {
            return false;
        }
        CalcFood calcFood = new CalcFood();
        calcFood.setValue(calcval);
        calcFood.setAddDate(new Date());
        Food food = foodService.getFoodById(id);
        calcFood.setFood(food);
        String userName = authentication.getUserName();
        User user = userService.getUser(userName);
        calcFood.setUser(user);
        calcService.save(calcFood);
        logger.info("save calculate food user = " + userName + " food = " + food.getName());
        return true;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/calc", method = RequestMethod.GET)
    public String showcalc(ModelMap modelMap) {
        String userName = authentication.getUserName();
        logger.info("User (" + userName + ") show own calculate food");
        User user = userService.getUser(userName);
        List<CalcFood> calcFoodList = calcService.getCalcByIdUser(user, Util.getDate(1), Util.getDate(0));
        Double calories = 0.0;
        if (calcFoodList != null) {
            for (CalcFood calcs : calcFoodList) {
                calories += calcs.getFood().getKcal() * calcs.getValue() / 100.0;
            }
        } else {
            logger.error("calculate food list is empty");
        }
        modelMap.addAttribute("calories", calories);
        modelMap.addAttribute("calc", calcFoodList);
        return "/mycalc";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/calc/delete/{id}", method = RequestMethod.GET)
    public String deleteCalc(@PathVariable("id") int id) {
        String userName = authentication.getUserName();
        User user = userService.getUser(userName);
        CalcFood calcFood = calcService.getCalcById(id);
        logger.info("user = (" + userName + ") delete calculate food with id = " + String.valueOf(id));
        if (calcFood.getUser().getIdUser() == user.getIdUser()) {
            calcService.delete(calcFood);
        }
        return "redirect:/calc";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/foodtable/{begin}/{end}", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView getFoodTable(@PathVariable("begin") long begin,
                                     @PathVariable("end") long end) {
        Date beginDate = new Date(begin);
        Date endDate = new Date(end);
        String userName = authentication.getUserName();
        User user = userService.getUser(userName);
        ModelAndView modelAndView = new ModelAndView("/frames/foodtable");
        List<CalcFood> calcFoods = calcService.getCalcByIdUser(user, beginDate, endDate);
        Double calories = 0.0;
        if (calcFoods != null) {
            for (CalcFood calcs : calcFoods) {
                calories += calcs.getFood().getKcal() * calcs.getValue() / 100.0;
            }
        } else {
            logger.error("calculate food is empty for user = (" + userName + ") in date beetwen" + beginDate.toString() + " and " + endDate.toString());
        }
        modelAndView.addObject("calories", calories);
        modelAndView.addObject("calc", calcFoods);
        return modelAndView;
    }
}