package com.sprsec.dao.category;

import com.sprsec.model.Category;

import java.util.List;

/**
 * Created by Yaroslav on 03.02.2015.
 */
public interface CategoryDao {
    /**
     * Add new Category
     * @param category
     */
    public void addCategory(Category category);

    /**
     * Get all Category
     */
    public List<Category> allCategory();

    /**
     * Return category by name
     * @param name
     */
    public Category getCategoryByName(String name);
}
