package com.chickly.Mappers;

import com.chickly.DTO.SubProductDTO;
import com.chickly.DataAccessLayer.Entities.SubProduct;

public class SubProductMapper {

    public static SubProductDTO convertEntityToDTO(SubProduct subProduct) {
        if (subProduct == null) {
            return null;
        }

        SubProductDTO dto = new SubProductDTO();
        dto.setProductName(subProduct.getProduct().getName());
        dto.setPrice(subProduct.getPrice());
        dto.setImageURL(subProduct.getImageURL());
        dto.setColor(subProduct.getColor().name());
        dto.setSize(subProduct.getSize().name());
        dto.setStock(subProduct.getStock());

        return dto;
    }
}
