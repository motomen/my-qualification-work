package com.sprsec.controller;

import com.sprsec.model.Category;
import com.sprsec.model.Subcategory;
import com.sprsec.service.category.CategoryService;
import com.sprsec.service.category.SubcategoryService;
import com.sprsec.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by Yaroslav on 03.02.2015.
 */
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubcategoryService subcategoryService;

    @RequestMapping(value = "/addcategory", method = RequestMethod.GET)
    public String addCategory(ModelMap model) {
        model.addAttribute("category", new Category());
        return "/addcategory";
    }

    @RequestMapping(value = "/addcategory", method = RequestMethod.POST)
    public String addNewCategory(@RequestParam("file") MultipartFile file,
                                 @RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 ModelMap model) {
        Category category = new Category();
        Blob blob;
        category.setPhoto(Util.fileToString(file)); // convert file in String Base64
        category.setDescription(description);
        category.setName(name);
        categoryService.addCategory(category);
        return "redirect:/";
    }

    @RequestMapping(value = "/addsubcategory", method = RequestMethod.GET)
    public String addSubcategory(ModelMap model){
        model.addAttribute("listCategory", categoryService.allCategory());
        model.addAttribute("subcategory", new Subcategory());
        return "/addsubcategory";
    }

    @RequestMapping(value = "/addsubcategory", method = RequestMethod.POST)
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
        return "redirect:/";
    }

    @RequestMapping(value = "/getsubcategory", method = RequestMethod.GET)
    public String getListSubcategory(){

        String result;
    }
}
