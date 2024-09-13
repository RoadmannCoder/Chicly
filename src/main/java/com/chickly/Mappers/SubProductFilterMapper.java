package com.chickly.Mappers;

import com.chickly.DTO.SubProductDTO;
import com.chickly.DTO.SubProductFilterDTO;
import com.chickly.Enums.Color;
import com.chickly.Enums.Size;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

public class SubProductFilterMapper {
    public SubProductFilterDTO parseRequestToFitlerDTO(HttpServletRequest request) {
        SubProductFilterDTO filterDTO = new SubProductFilterDTO();

        filterDTO.setProductName(request.getParameter("productName"));
        filterDTO.setMinPrice(parseBigDecimal(request.getParameter("minPrice")));
        filterDTO.setMaxPrice(parseBigDecimal(request.getParameter("maxPrice")));
        filterDTO.setSize(parseSizeEnum(request.getParameter("size")));
        filterDTO.setColor(parseColorEnum(request.getParameter("color")));
        filterDTO.setPageNumber(parseInteger(request.getParameter("page"), 1));
        filterDTO.setCategoryId(parseInteger(request.getParameter("category"),null));

        return filterDTO;
    }

    private BigDecimal parseBigDecimal(String value) {
        try {
            return value != null && !value.isEmpty() ? new BigDecimal(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
    private Color parseColorEnum(String color) {
        try {
            return color != null && !color.isEmpty() ? Color.valueOf(color.toUpperCase()) : null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    private Size parseSizeEnum(String size) {
        try {
            return size != null && !size.isEmpty() ? Size.valueOf(size.toUpperCase()) : null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private Integer parseInteger(String value, Integer defaultValue) {
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


}
