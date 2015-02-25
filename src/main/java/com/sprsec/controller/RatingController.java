package com.sprsec.controller;

import com.sprsec.auth.IAuthenticationFacade;
import com.sprsec.model.Rating;
import com.sprsec.model.User;
import com.sprsec.service.food.FoodService;
import com.sprsec.service.rating.RatingService;
import com.sprsec.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Yaroslav on 22.02.2015.
 */

@Controller
public class RatingController {


    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

    @Autowired
    private IAuthenticationFacade authentication;

    @Autowired
    private FoodService foodService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/rating/{value}/{idFood}", method = RequestMethod.GET)
    public ModelAndView rating(
            @PathVariable("value") double value,
            @PathVariable("idFood") String id,
            ModelMap model) {
        String userName = authentication.getUserName();
        User user = userService.getUser(userName);
        Rating rating = new Rating();
        try {
            rating = ratingService.getRatingByIdUserFood(id, user.getIdUser());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rating == null)
        {
            rating = new Rating();
            rating.setUser(user);
            rating.setFood(foodService.getFoodById(id));
            rating.setValue(value);
        }
        ratingService.saveOrUpdate(rating);
        Set<Rating> ratingSet = ratingService.getRatingByIdFood(id);
        double val=0.0;
        for (Rating rSet: ratingSet) {
            val += rSet.getValue();
        }
        val /= ratingSet.size();
        model.addAttribute("ratginFood", val);
        // -- you have end it
        return new ModelAndView("showfood", model);
    }
}
