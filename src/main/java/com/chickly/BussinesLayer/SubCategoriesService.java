package com.chickly.BussinesLayer;

import com.chickly.DataAccessLayer.Entities.SubCategory;
import com.chickly.DataAccessLayer.Repository.SubCategoryRepository;

import java.util.List;
import java.util.Optional;

public class SubCategoriesService {
    public final SubCategoryRepository subCategoryRepository = new SubCategoryRepository();


    public Optional<List<SubCategory>> getAllSubCategories(){
        return subCategoryRepository.findAllExceptAccessories();
    }
    public List<SubCategory> getAccessories(){
        return subCategoryRepository.findSubCategoriesByCategoryID(3);
    }

}
