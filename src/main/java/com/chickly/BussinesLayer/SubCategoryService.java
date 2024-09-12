package com.chickly.BussinesLayer;

import com.chickly.DTO.CategoryDTO;
import com.chickly.DataAccessLayer.Entities.Category;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Repository.CategoryRepository;
import com.chickly.Mappers.CategoryMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryService {

    public  List<CategoryDTO> getAllCategories (){
        CategoryRepository categoryRepository = new CategoryRepository();
        List<Category> categories = categoryRepository.findAll().get();
        return categories.stream().map(CategoryMapper::convertEntityToDTO).collect(Collectors.toList());
    }
}
