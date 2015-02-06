package com.sprsec.dao.category;

import com.sprsec.model.Category;
import com.sprsec.model.Subcategory;

/**
 * Created by Yaroslav on 04.02.2015.
 */
public interface SubcategoryDao {
    /**
     * add new Subcategory
     * @param subcategory
     */
    public void addSubcategory(Subcategory subcategory);

    /**
     * Return Subcategory by name
     * @param name
     */
    public Subcategory getSubcategoryByName(String name);
}
