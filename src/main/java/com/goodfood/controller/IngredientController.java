package com.goodfood.controller;

import com.goodfood.model.Ingredient;
import com.goodfood.model.Link;
import com.goodfood.model.TypeIngredients;
import com.goodfood.service.IngredientsService;
import com.goodfood.service.LinkService;
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
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private LinkService linkService;

    @RequestMapping(value = "/addingredient", method = RequestMethod.GET)
    public String showAddIngredient(ModelMap modelMap) {
        logger.info("show add ingredient");
        modelMap.addAttribute("ingredient", new Ingredient());
        modelMap.addAttribute("listTypeIngredinets", typeIngredientService.getListTypeIngredients());
        return "/control/addingredient";
    }

    @RequestMapping(value = "/addingredient", method = RequestMethod.POST)
    public String addIngredient(@ModelAttribute("ingredient") Ingredient ingredient,
                                @RequestParam("file") MultipartFile file,
                                @RequestParam("typeingredient") String typeIngredient
            , ModelMap modelMap) {
        logger.info("add ingredient " + ingredient.getNameIngredient());
        TypeIngredients typeIngredients = typeIngredientService.getTypeIngredientByName(typeIngredient);
        List<TypeIngredients> typeIngredientsList = new ArrayList<>();
        typeIngredientsList.add(typeIngredients);
        ingredient.setTypeIngredientsList(typeIngredientsList);
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
        modelMap.addAttribute("link", new Link());
        String name = "/control/linktoingredient";
        ingredientsService.addIngredient(ingredient);
        return name; // this error
    }

    @RequestMapping(value = "/addtypeingredient", method = RequestMethod.GET)
    public String showAddTypeIngredient(ModelMap modelMap) {
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

    @RequestMapping(value = "/linktoingredient", method = RequestMethod.GET)
    public String showAddLinkToIngredient(ModelMap modelMap) {
        logger.info("show add link to ingredient");
        modelMap.addAttribute("link", new Link());
        return "/control/linktoingredient";
    }

    @RequestMapping(value = "/linktoingredient", method = RequestMethod.POST)
    public String addLinkToIngredient(@ModelAttribute("link") Link link,
                                      @RequestParam("ingredient_name") String name) {
        linkService.addLink(link);
        Ingredient ingredient = ingredientsService.getIngredientByName(name);
        List<Link> linkArrayList = ingredient.getLinkList();
        linkArrayList.add(link);
        ingredient.setLinkList(linkArrayList);
        ingredientsService.updateIngredient(ingredient);
        return "redirect:/control/linktoingredient";
    }
}
