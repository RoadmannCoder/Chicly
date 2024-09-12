package com.chickly.Mappers;

import com.chickly.DTO.CategoryDTO;
import com.chickly.DTO.SubCategoryDTO;
import com.chickly.DataAccessLayer.Entities.Category;
import com.chickly.DataAccessLayer.Entities.SubCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubCategoryMapper {
    public static SubCategoryDTO convertEntityToDTO(SubCategory subCategory) {
        if (subCategory == null) {
            return null;
        }
        SubCategoryDTO dto = new SubCategoryDTO();
        dto.setId(subCategory.getId());
        dto.setName(subCategory.getName());
        dto.setId(subCategory.getCategory().getId());
        return dto;
    }
    public static List<SubCategoryDTO> fromEntityToSubCategoryDTO(Optional<List<SubCategory>> subCategoriesOptional) {
        if (subCategoriesOptional.isEmpty()) {
            return null;
        }
        List<SubCategoryDTO> subCategoriesViewDTOList = new ArrayList<>();

        if (subCategoriesOptional.isPresent()) {
            List<SubCategory> subCategoriesList = subCategoriesOptional.get();

            for (SubCategory subCategory : subCategoriesList) {
                SubCategoryDTO dto = SubCategoryMapper.convertEntityToDTO(subCategory);
                subCategoriesViewDTOList.add(dto);
            }
        }
        return subCategoriesViewDTOList;
    }

}
