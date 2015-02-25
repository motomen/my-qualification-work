package com.sprsec.controller;

import com.sprsec.model.Comments;
import com.sprsec.model.Food;
import com.sprsec.model.Rating;
import com.sprsec.model.Subcategory;
import com.sprsec.service.category.CategoryService;
import com.sprsec.service.category.SubcategoryService;
import com.sprsec.service.comments.CommentService;
import com.sprsec.service.food.FoodService;
import com.sprsec.service.rating.RatingService;
import com.sprsec.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Yaroslav on 01.02.2015.
 */

@Controller
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private RatingService ratingService;

    @RequestMapping(value = "/control/addfood", method = RequestMethod.GET)
    public String addFood(ModelMap model) {
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
        Food food = new Food();
        food.setIdFood(idFood);
        food.setName(name);
        food.setProtein(protein);
        food.setFats(fats);
        food.setCarbs(carbs);
        food.setKcal(kcal);
        food.setRating(0.0);
        food.setIngredients(ingredients);
        food.setPhoto(Util.fileToString(file)); //convert file to string Base64
        HashSet<Subcategory> hashSet = new HashSet<Subcategory>();
        hashSet.add(subcategoryService.getCategoryByName(subcategory));
        food.setSubcategories(hashSet);
        foodService.addFood(food);
//        ModelAndView modelAndView = new ModelAndView("/control/addfoodstep2");
//        modelAndView.addObject("idFood", food.getIdFood());
        return "/control/addfood";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/showfood/{id}", method = RequestMethod.GET)
    public String showPageFood(@PathVariable("id") String idFood,
                               ModelMap model) {

        Food food = foodService.getFoodById(idFood);
        List<Comments> commentsList = food.getCommentsList();
        model.addAttribute("id", idFood);
        model.addAttribute("comment", new Comments());
        model.addAttribute("listComment", commentsList);
        model.addAttribute("count", commentsList.size());
        model.addAttribute("food", food);
        ArrayList<Rating> ratingSet = new ArrayList<>(ratingService.getRatingByIdFood(idFood));
        double val=0.0;
        if (ratingSet.size() > 0) {
            for (Rating rSet : ratingSet) {
                val += rSet.getValue();
            }
            val /= ratingSet.size();
        }
        model.addAttribute("ratginFood", val);
        return "showfood";
    }

    @RequestMapping(value = "/control/foodtocategory", method = RequestMethod.GET)
    public String addFoodToCategory(ModelMap model) {
        model.addAttribute("subcategory", subcategoryService.getAllSubcategory());
        model.addAttribute("foods", foodService.getAllFood());
        return "control/foodtocategory";
    }

    @RequestMapping(value = "/control/foodtocategory", method = RequestMethod.POST)
    public String addFoodToCategory(@RequestParam("namecategory") String subcategory,
                                    @RequestParam("food") String food,
                                    ModelMap model) {
        Food food1 = foodService.getFoodByName(food);
        Set<Subcategory> subcategoryList = food1.getSubcategories();
        Subcategory subcategory1 = subcategoryService.getCategoryByName(subcategory);
        subcategoryList.add(subcategory1);
        food1.setSubcategories(subcategoryList);
        foodService.update(food1);
        return "redirect:/control/foodtocategory";
    }
}
