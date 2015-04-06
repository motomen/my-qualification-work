package com.goodfood.controller;

import com.goodfood.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by Yaroslav on 31.01.2015.
 */
@Controller
public class IndexController {

    final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("foods", foodService.getFood(3));
        String BestFood = foodService.getStringBestFood(10);
        model.addAttribute("bestFood", BestFood);
        logger.info("index page");
        return "index";
    }

    @RequestMapping("about")
    public String about() {
        logger.info("about page");
        return "about";
    }
}
