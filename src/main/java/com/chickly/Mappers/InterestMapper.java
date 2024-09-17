package com.chickly.Mappers;

import com.chickly.DTO.InterestDTO;
import com.chickly.DTO.SubCategoryDTO;
import com.chickly.DataAccessLayer.Entities.Interest;

public class InterestMapper {
    public static InterestDTO convertEntityToDTO(Interest interest) {
        if (interest == null) {
            return null;
        }
        InterestDTO dto = new InterestDTO();
        dto.setId(interest.getId());
        dto.setName(interest.getName());
        return dto;
    }
}
