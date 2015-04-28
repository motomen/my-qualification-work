package com.goodfood.api;

import com.goodfood.model.Ingredient;
import com.goodfood.service.IngredientsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yaroslav on 28.04.2015.
 */

@RestController
@RequestMapping(value = "/api/ingredients")
public class IngredientRestController {

    final Logger logger = LoggerFactory.getLogger(IngredientRestController.class);

    @Autowired
    private IngredientsService ingredientsService;

    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Ingredient> getIngredientByName(@PathVariable("name") String name) {
        Ingredient ingredient = ingredientsService.getIngredientByName(name);
        if (null == ingredient || null == name || name.trim().length() == 0) {
            logger.info("Problem with getting ingredient by name " + name);
            return new ResponseEntity<Ingredient>(new Ingredient(), HttpStatus.BAD_REQUEST);
        }
        logger.info("Get food by name" + name);
        return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
    }
}
