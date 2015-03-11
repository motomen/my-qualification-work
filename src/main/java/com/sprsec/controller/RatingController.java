package com.sprsec.controller;

import com.sprsec.auth.IAuthenticationFacade;
import com.sprsec.model.Rating;
import com.sprsec.model.User;
import com.sprsec.service.FoodService;
import com.sprsec.service.RatingService;
import com.sprsec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
    @ResponseBody
    public double rating(
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
        }
        rating.setValue(value);
        ratingService.saveOrUpdate(rating);
        List<Rating> ratingSet = ratingService.getRatingByIdFood(id);
        double val=0.0;
        for (Rating rSet: ratingSet) {
            val += rSet.getValue();
        }
        val /= ratingSet.size();
        return val;
    }
}
