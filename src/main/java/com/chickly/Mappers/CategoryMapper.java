package com.chickly.Mappers;

import com.chickly.DTO.CategoryDTO;
import com.chickly.DataAccessLayer.Entities.Category;

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
}
