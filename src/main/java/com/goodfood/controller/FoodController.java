package com.goodfood.controller;

import com.goodfood.auth.IAuthenticationFacade;
import com.goodfood.model.*;
import com.goodfood.service.CategoryService;
import com.goodfood.service.SubcategoryService;
import com.goodfood.service.FoodService;
import com.goodfood.service.RatingService;
import com.goodfood.service.UserService;
import com.goodfood.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Yaroslav on 01.02.2015.
 */

@Controller
public class FoodController {

    final Logger logger = LoggerFactory.getLogger(FoodController.class);

    @Autowired
    private FoodService foodService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private IAuthenticationFacade authentication;

    @Autowired
    private UserService userService;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value = "/control/addfood", method = RequestMethod.GET)
    public String addFood(ModelMap model) {
        logger.info("show add food page");
        model.addAttribute("food", new Food());
        model.addAttribute("listCategory", categoryService.allCategory());
        return "control/addfood";
    }

    // add main information about food
    @RequestMapping(value = "/control/addfood", method = RequestMethod.POST)
    public String addNewFood(
            @RequestParam("file") MultipartFile file,
            @RequestParam("idFood") String idFood,
            @RequestParam("name") String name,
            @RequestParam("protein") Double protein,
            @RequestParam("fats") Double fats,
            @RequestParam("carbs") Double carbs,
            @RequestParam("kcal") Double kcal,
            @RequestParam("ingredients") String ingredients,
            @RequestParam("subcategory") String subcategory,
            ModelMap model) {
        logger.info("add new food");
        Food food = new Food();
        food.setIdFood(idFood);
        food.setName(name);
        food.setProtein(protein);
        food.setFats(fats);
        food.setCarbs(carbs);
        food.setKcal(kcal);
        food.setRating(1.0);
        food.setIngredients(ingredients);
        HashSet<Subcategory> hashSet = new HashSet<Subcategory>();
        hashSet.add(subcategoryService.getCategoryByName(subcategory));
        food.setSubcategories(hashSet);
        if (file.getSize() != 0) {
            food.setPhoto(Util.fileToString(file)); //convert file to string Base64
        } else {
            File resource = new File(servletContext.getRealPath("/") + "/resources/img/food.jpg");
            if (resource.exists()) {
                try {
                    food.setPhoto(Util.fileToString(resource));
                } catch (Exception e) {
                    logger.error("error convert photo for food");
                    e.printStackTrace();
                }
            }
        }
        foodService.addFood(food);
//        ModelAndView modelAndView = new ModelAndView("/control/addfoodstep2");
//        modelAndView.addObject("idFood", food.getIdFood());
        return "redirect:/control/addfood";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/showfood/{id}", method = RequestMethod.GET)
    public String showPageFood(@PathVariable("id") String idFood,
                               ModelMap model) {
        String userName = authentication.getUserName();
        User user = userService.getUser(userName);
        Food food = foodService.getFoodById(idFood);
        List<Comments> commentsList = food.getCommentsList();
        if (user != null) {
            Rating rating = ratingService.getRatingByIdUserFood(idFood, user.getIdUser());
            if (rating != null) {
                model.addAttribute("ratingUser", rating.getValue());
            } else {
                model.addAttribute("ratingUser", 0);
            }
        }
        model.addAttribute("id", idFood);
        model.addAttribute("comment", new Comments());
        model.addAttribute("listComment", commentsList);
        model.addAttribute("count", commentsList.size());
        model.addAttribute("food", food);
        model.addAttribute("calcFood", new CalcFood());
        List<Rating> ratingSet = ratingService.getRatingByIdFood(idFood);
        double val = 0.0;
        if (ratingSet.size() > 0) {
            for (Rating rSet : ratingSet) {
                val += rSet.getValue();
            }
            val /= ratingSet.size();
        }
        logger.info("show food information with id = (" + food.getIdFood() + ") for user = " + userName);
        model.addAttribute("ratginFood", val);
        return "showfood";
    }

    @RequestMapping(value = "/control/foodtocategory", method = RequestMethod.GET)
    public String addFoodToCategory(ModelMap model) {
        logger.info("show add food to category page");
        model.addAttribute("subcategory", subcategoryService.getAllSubcategory());
        return "control/foodtocategory";
    }

    @RequestMapping(value = "/control/foodtocategory", method = RequestMethod.POST)
    public String addFoodToCategory(@RequestParam("namecategory") String subcategory,
                                    @RequestParam("food") String food,
                                    ModelMap model) {
        logger.info("add food with id = (" + food + ") to subcategory with name = " + subcategory);
        Food food1 = foodService.getFoodById(food);
        Set<Subcategory> subcategoryList = food1.getSubcategories();
        Subcategory subcategory1 = subcategoryService.getCategoryByName(subcategory);
        subcategoryList.add(subcategory1);
        food1.setSubcategories(subcategoryList);
        foodService.update(food1);
        return "redirect:/control/foodtocategory";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public List<String> searchFood(
            @RequestParam("input") String input) {
        String nameUtf8 = Util.Iso88591ToUtf8(input);
        logger.info("search on site query = " + nameUtf8);
        List<String> nameList = foodService.getNameFoodForSearch(nameUtf8);
        return nameList;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchFoods(
            @RequestParam("name") String name,
            ModelMap modelMap) {
        List<Food> foodList = foodService.getFoodForSearch(name);
        if (foodList.size() == 1) {
            logger.info("show result for one food with query = " + name);
            return "redirect:/showfood/" + foodList.get(0).getIdFood();
        } else {
            logger.info("show result for more one food with query = " + name);
            modelMap.addAttribute("foodList", foodList);
            return "/showfoods";
        }
    }
}
