package com.sprsec.service.impl;

import com.sprsec.dao.SubcategoryDao;
import com.sprsec.model.Subcategory;
import com.sprsec.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * Get all subcategory
     *
     * @return List subcategory
     */
    @Override
    public List<Subcategory> getAllSubcategory() {
        return subcategoryDao.getAllSubcategory();
    }

    @Override
    public Subcategory getCategoryById(int id) {
        return subcategoryDao.getCategoryById(id);
    }
}
