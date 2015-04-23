package com.goodfood.controller;

import com.goodfood.model.Ingredient;
import com.goodfood.model.TypeIngredients;
import com.goodfood.service.IngredientsService;
import com.goodfood.service.TypeIngredientService;
import com.goodfood.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * Created by Yaroslav on 23.04.2015.
 */

@Controller
@RequestMapping(value = "/control")
public class IngredientController {

    final Logger logger = LoggerFactory.getLogger(IngredientController.class);

    @Autowired
    public IngredientsService ingredientsService;

    @Autowired
    public TypeIngredientService typeIngredientService;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value = "/addingredient", method = RequestMethod.GET)
    public String showAddIngredient(ModelMap modelMap){
        logger.info("show add ingredient");
        modelMap.addAttribute("ingredient", new Ingredient());
        return "/control/addingredient";
    }

    @RequestMapping(value = "/addingredient", method = RequestMethod.POST)
    public String addIngredient(@ModelAttribute("ingredient") Ingredient ingredient,
                                @RequestParam("file") MultipartFile file) {
        logger.info("add ingredient " + ingredient.getNameIngredient());
        if (file.getSize() != 0) {
            ingredient.setPhoto(Util.fileToString(file)); //convert file to string Base64
        } else {
            File resource = new File(servletContext.getRealPath("/") + "/resources/img/ingredients.jpg");
            if (resource.exists()) {
                try {
                    ingredient.setPhoto(Util.fileToString(resource));
                } catch (Exception e) {
                    logger.error("error convert photo for food");
                    e.printStackTrace();
                }
            }
        }
        ingredientsService.addIngredient(ingredient);
        return "/control/addingredient";
    }

    @RequestMapping(value = "/addtypeingredient", method = RequestMethod.GET)
    public String showAddTypeIngredient(ModelMap modelMap){
        logger.info("show add ingredient");
        modelMap.addAttribute("typeingredient", new TypeIngredients());
        return "/control/addtypeingredient";
    }

    @RequestMapping(value = "/addtypeingredient", method = RequestMethod.POST)
    public String addTypeIngredient(@ModelAttribute("typeingredient") TypeIngredients ingredient,
                                @RequestParam("file") MultipartFile file) {
        logger.info("add type ingredient " + ingredient.getName());
        if (file.getSize() != 0) {
            ingredient.setPhoto(Util.fileToString(file)); //convert file to string Base64
        } else {
            File resource = new File(servletContext.getRealPath("/") + "/resources/img/ingredients.jpg");
            if (resource.exists()) {
                try {
                    ingredient.setPhoto(Util.fileToString(resource));
                } catch (Exception e) {
                    logger.error("error convert photo for food");
                    e.printStackTrace();
                }
            }
        }
        typeIngredientService.addTypeIngredient(ingredient);
        return "redirect:/control/addtypeingredient";
    }
}
