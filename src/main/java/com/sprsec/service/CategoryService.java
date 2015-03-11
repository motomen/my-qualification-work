package com.sprsec.service;

import com.sprsec.model.Category;

import java.util.List;

/**
 * Created by Yaroslav on 03.02.2015.
 */
public interface CategoryService {
    public void addCategory(Category category);
    public List<Category> allCategory();
    public Category getCategoryByName(String name);
}
