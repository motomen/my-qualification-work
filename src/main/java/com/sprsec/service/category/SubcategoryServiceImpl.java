package com.sprsec.service.category;

import com.sprsec.dao.category.SubcategoryDao;
import com.sprsec.model.Category;
import com.sprsec.model.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yaroslav on 04.02.2015.
 */
@Service
@Transactional
public class SubcategoryServiceImpl implements SubcategoryService {
    @Autowired
    private SubcategoryDao subcategoryDao;

    @Override
    public void addSubcategory(Subcategory subcategory) {
        subcategoryDao.addSubcategory(subcategory);
    }

    @Override
    public Subcategory getCategoryByName(String name) {
        return subcategoryDao.getSubcategoryByName(name);
    }
}
