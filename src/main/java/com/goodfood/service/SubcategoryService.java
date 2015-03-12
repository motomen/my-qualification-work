package com.goodfood.service;

import com.goodfood.model.Subcategory;

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
