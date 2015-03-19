package com.goodfood.controller;

import com.goodfood.auth.IAuthenticationFacade;
import com.goodfood.model.Rating;
import com.goodfood.model.User;
import com.goodfood.service.FoodService;
import com.goodfood.service.RatingService;
import com.goodfood.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    final Logger logger = LoggerFactory.getLogger(RatingController.class);

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
        int idRating = ratingService.getIdRatingByIdUserIdFood(id, user.getIdUser());
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setFood(foodService.getFoodById(id));
        if (idRating != -1) {
            rating.setIdRating(idRating);
        }
        rating.setValue(value);
        ratingService.saveOrUpdate(rating);
        double val = ratingService.getRatingByIdFood(id);
        logger.info("set rating = " + String.valueOf(rating.getValue()) + " for food id = (" + id + ") user = (" + userName + ")");
        return val;
    }
}
