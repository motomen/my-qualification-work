package com.sprsec.service.category;

import com.sprsec.model.Category;
import com.sprsec.model.Subcategory;

import java.util.List;

/**
 * Created by Yaroslav on 04.02.2015.
 */
public interface SubcategoryService {
    public void addSubcategory(Subcategory subcategory);
    public Subcategory getCategoryByName(String name);
    /**
     * Get all subcategory
     * @return List subcategory
     */
    public List<Subcategory> getAllSubcategory();

    public Subcategory getCategoryById(int id);
}
