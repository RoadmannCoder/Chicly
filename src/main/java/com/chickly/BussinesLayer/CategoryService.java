package com.chickly.BussinesLayer;

import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

public class CategoryService {

    public static Optional<List<SubProduct>> getAllCategories (){
        CategoryRepository categoryRepository = new CategoryRepository();
        Optional<List<SubProduct>> categories = categoryRepository.findAll();
        return categories;
    }
}
