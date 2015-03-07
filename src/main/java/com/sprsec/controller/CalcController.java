package com.sprsec.controller;

import com.sprsec.auth.IAuthenticationFacade;
import com.sprsec.model.CalcFood;
import com.sprsec.model.Food;
import com.sprsec.model.User;
import com.sprsec.service.food.CalcService;
import com.sprsec.service.food.FoodService;
import com.sprsec.service.user.UserService;
import com.sprsec.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yaroslav on 05.03.2015.
 */

@Controller
public class CalcController {

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
        return true;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/calc", method = RequestMethod.GET)
    public String showcalc(ModelMap modelMap) {
        String userName = authentication.getUserName();
        User user = userService.getUser(userName);
        List<CalcFood> calcFoodList = calcService.getCalcByIdUser(user, Util.getDate(1), Util.getDate(0));
        Double calories = 0.0;
        for (CalcFood calcs: calcFoodList) {
            calories += calcs.getFood().getKcal() * calcs.getValue() / 100.0;
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
        for (CalcFood calcs: calcFoods) {
            calories += calcs.getFood().getKcal() * calcs.getValue() / 100.0;
        }
        modelAndView.addObject("calories", calories);
        modelAndView.addObject("calc", calcFoods);
        return modelAndView;
    }
}
