package com.sprsec.controller;

import com.sprsec.model.Food;
import com.sprsec.service.category.CategoryService;
import com.sprsec.service.category.SubcategoryService;
import com.sprsec.service.food.FoodService;
import com.sprsec.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

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

    @RequestMapping(value = "/addfood", method = RequestMethod.GET)
    public String addFood(ModelMap model) {
        model.addAttribute("food", new Food());
        model.addAttribute("listCategory", categoryService.allCategory());
        return "addfood";
    }

    // add main information about food
    @RequestMapping(value = "/addfood", method = RequestMethod.POST)
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
        food.getSubcategories().add(subcategoryService.getCategoryByName(subcategory));
        foodService.addFood(food);
        return "redirect:/";
    }

    @RequestMapping(value = "/showfood/{id}", method = RequestMethod.GET)
    public String showPageFood(@PathVariable("id") String idFood,
            ModelMap model) {
        Food food = foodService.getFoodById(idFood);
        model.addAttribute("food", food);
        return "showfood";
    }
}
