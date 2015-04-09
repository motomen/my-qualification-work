package com.goodfood.api;

import com.goodfood.model.Food;
import com.goodfood.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yaroslav on 20.03.2015.
 */

@RestController
@RequestMapping(value = "/api/food")
public class FoodRestController {

    final Logger logger = LoggerFactory.getLogger(FoodRestController.class);

    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "/get/{idFood}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<Food> getFoodById(@PathVariable("idFood") String idFood) {
        Food food = foodService.getFoodById(idFood);
        if (null == food || null == idFood || idFood.trim().length() == 0) {
            logger.info("Problem with getting food by id" + idFood);
            return new ResponseEntity<Food>(new Food(), HttpStatus.BAD_REQUEST);
        }
        logger.info("Get food by id" + idFood);
        return new ResponseEntity<Food>(food, HttpStatus.OK);
    }
}
