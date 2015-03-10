package com.sprsec.controller;

import com.sprsec.model.Category;
import com.sprsec.model.Food;
import com.sprsec.model.Subcategory;
import com.sprsec.service.category.CategoryService;
import com.sprsec.service.category.SubcategoryService;
import com.sprsec.service.food.FoodService;
import com.sprsec.util.Page;
import com.sprsec.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Yaroslav on 03.02.2015.
 */
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "/control/addcategory", method = RequestMethod.GET)
    public String addCategory(ModelMap model) {
        model.addAttribute("category", new Category());
        return "/control/addcategory";
    }

    @RequestMapping(value = "/control/addcategory", method = RequestMethod.POST)
    public String addNewCategory(@RequestParam("file") MultipartFile file,
                                 @RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 ModelMap model) {
        Category category = new Category();
        category.setPhoto(Util.fileToString(file)); // convert file in String Base64
        category.setDescription(description);
        category.setName(name);
        categoryService.addCategory(category);
        return "redirect:/control/addcategory";
    }

    @RequestMapping(value = "/control/addsubcategory", method = RequestMethod.GET)
    public String addSubcategory(ModelMap model) {
        model.addAttribute("listCategory", categoryService.allCategory());
        model.addAttribute("subcategory", new Subcategory());
        return "/control/addsubcategory";
    }

    @RequestMapping(value = "/control/addsubcategory", method = RequestMethod.POST)
    public String addNewSubcategory(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("namecategory") String nameCategory,
            ModelMap model) {
        Subcategory subcategory = new Subcategory();
        subcategory.setName(name);
        subcategory.setImg(Util.fileToString(file));
        subcategory.setCategory(categoryService.getCategoryByName(nameCategory));
        subcategoryService.addSubcategory(subcategory);
        return "redirect:/control/addsubcategory";
    }

    @RequestMapping(value = "/getsubcategory/{namecategory}", method = RequestMethod.GET)
    @ResponseBody
    public List<Subcategory> allListSubcategory(@PathVariable("namecategory") String name) {
        Category category = categoryService.getCategoryByName(Util.Iso88591ToUtf8(name));
        List<Subcategory> list = category.getSubCategories();
        return list;
    }

    @RequestMapping(value = "/foods", method = RequestMethod.GET)
    public String initializeFoods(ModelMap model) {
        model.addAttribute("listCategory", categoryService.allCategory());
        model.addAttribute("listSubcategory", subcategoryService.getAllSubcategory());
        return "/foods";
    }

    @RequestMapping(value = "/foodsubcategory/{idsubcategory}", method = RequestMethod.GET)
    public String getListFood(
            @PathVariable("idsubcategory") int id,
            @RequestParam(value = "p",required = false ,defaultValue = "1") int page,
            @RequestParam(value = "results",defaultValue = "5",required = false) Integer recPerPage,
            ModelMap model) {
        Subcategory subcategory = subcategoryService.getCategoryById(id);
        int size = foodService.getAllFoodBySubcategory(subcategory).size();
        int maxPages = (size % recPerPage == 0)? (size / recPerPage) : (size / recPerPage)+1;
        page = (page > maxPages)?   maxPages : page;
        List<Food> foodList = foodService.getAllFoodBySubcategory(page, recPerPage, subcategory);
        int current = page;
        int begin = Math.max(1, current - recPerPage);
        int end = maxPages;
        Page<Food> foodPage = new Page<>(foodList, begin, current, size, maxPages, recPerPage, end);
        model.addAttribute("page", foodPage);
        model.addAttribute("idSubcategory", id);
        return "/foodsubcategory";
    }
}
