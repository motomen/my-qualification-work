package com.goodfood.api;

import com.goodfood.model.Category;
import com.goodfood.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Yaroslav on 11.05.2015.
 */

@RestController
@RequestMapping(value = "/api/category")
public class CategoryRestController {

    final Logger logger = LoggerFactory.getLogger(CategoryRestController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Category>> getAllCategory() {

        ArrayList<Category> categoryArrayList = (ArrayList<Category>) categoryService.allCategory();

        if (categoryArrayList == null) {
            logger.info("Problem with getting all category");
            return new ResponseEntity<ArrayList<Category>>(new ArrayList<Category>(), HttpStatus.BAD_REQUEST);
        }

        logger.info("Get all category");
        return new ResponseEntity<ArrayList<Category>> (categoryArrayList, HttpStatus.OK);
    }
}
