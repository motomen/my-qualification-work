package com.goodfood.controller;

import com.goodfood.model.Ingredient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Yaroslav on 23.04.2015.
 */

@Controller
@RequestMapping(value = "/control")
public class IngredientController {

    @RequestMapping(value = "/addingredient", method = RequestMethod.GET)
    public String showAddIngredient(ModelMap modelMap){
        modelMap.addAttribute("ingredient", new Ingredient());
        return "addingredient";
    }

    @RequestMapping(value = "/addingredient", method = RequestMethod.POST)
    public String addIngredient(@ModelAttribute("ingredient") Ingredient ingredient) {
        return "redirect:/control/addingredient";
    }
}
