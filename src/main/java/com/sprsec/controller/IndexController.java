package com.sprsec.controller;

import com.sprsec.service.CommentService;
import com.sprsec.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Yaroslav on 31.01.2015.
 */
@Controller
public class IndexController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {

        return "index";
    }

    @RequestMapping("about")
    public String about() {
        return "about";
    }
}
