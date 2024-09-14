package com.chickly.BussinesLayer;
import com.chickly.DTO.SubCategoryDTO;
import com.chickly.DataAccessLayer.Entities.Category;
import com.chickly.DataAccessLayer.Entities.SubCategory;
import com.chickly.DataAccessLayer.Repository.SubCategoryRepository;
import com.chickly.Mappers.SubCategoryMapper;
import java.util.List;
import java.util.Optional;

public class SubCategoryService {
    SubCategoryRepository subCategoryRepository;
    public SubCategoryService() {
        this.subCategoryRepository = new SubCategoryRepository();
    }

    public List<SubCategoryDTO> getAllSubCategories(){
        Optional<List<SubCategory>> subCategoryEntity = subCategoryRepository.findAll();
        List<SubCategoryDTO> subCategoryDTOList = SubCategoryMapper.fromEntityToSubCategoryDTO(subCategoryEntity);
        return subCategoryDTOList;
    }

    public void createSubCategory(String categoryId, String subCategoryName) {
        CategoryService categoryService = new CategoryService();
        Category category = categoryService.findCategoryById(categoryId);

        SubCategory subCategory = new SubCategory();
        subCategory.setCategory(category);
        subCategory.setName(subCategoryName);
        subCategoryRepository.create(subCategory);
    }
}
