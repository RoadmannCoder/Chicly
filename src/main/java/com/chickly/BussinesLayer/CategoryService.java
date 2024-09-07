package com.chickly.BussinesLayer;

import com.chickly.DataAccessLayer.Entities.Category;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Repository.CategoryRepository;
import com.chickly.DataAccessLayer.Repository.SubProductRepository;

import java.util.List;

public class CategoryService {

    public static List<Category> getAllCategories (){
        CategoryRepository categoryRepository = new CategoryRepository();
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
}
