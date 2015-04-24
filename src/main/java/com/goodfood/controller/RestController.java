package com.goodfood.controller;

import com.goodfood.model.Ingredient;
import com.goodfood.service.IngredientsService;
import com.goodfood.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yaroslav on 24.04.2015.
 */

@Controller
public class RestController {

    final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    public IngredientsService ingredientsService;

    @RequestMapping(value = "/get/information/{name}", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView getInformationIngredient(@PathVariable("name") String name) {
        logger.info("get rest information about ingredient");
        name = Util.Iso88591ToUtf8(name);
        name = Util.replaceSpecialSymbolOn0(name);
        Ingredient ingredient = ingredientsService.getIngredientByName(name.toLowerCase());
        ModelAndView modelAndView = new ModelAndView("/frames/ingredientmodal");
        if (ingredient != null) {
            modelAndView.addObject("ingredient", ingredient);
        } else {
            modelAndView = new ModelAndView("/frames/notfound");
        }
        return modelAndView;
    }
}
