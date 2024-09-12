package com.chickly.Mappers;

import com.chickly.DTO.CategoryDTO;
import com.chickly.DTO.CustomerViewDTO;
import com.chickly.DataAccessLayer.Entities.Category;
import com.chickly.DataAccessLayer.Entities.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryMapper {
    public static CategoryDTO convertEntityToDTO(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());

        return dto;
    }
    public static List<CategoryDTO> fromEntityToCategoryDTO(Optional<List<Category>> categoriesOptional) {
        if (categoriesOptional.isEmpty()) {
            return null;
        }
        List<CategoryDTO> categoriesViewDTOList = new ArrayList<>();

        if (categoriesOptional.isPresent()) {
            List<Category> categoriesList = categoriesOptional.get();

            for (Category category : categoriesList) {
                CategoryDTO dto = CategoryMapper.convertEntityToDTO(category);
                categoriesViewDTOList.add(dto);
            }
        }

        return categoriesViewDTOList;
    }

}
