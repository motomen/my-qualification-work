package com.sprsec.controller;

import com.sprsec.model.Food;
import com.sprsec.service.food.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Yaroslav on 31.01.2015.
 */
@Controller
public class IndexController {

    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        List<Food> foodList = foodService.getTenFood();
        model.addAttribute("foodList", foodList);
        return "index";
    }

    @RequestMapping("about")
    public String about() {
        return "about";
    }
}
