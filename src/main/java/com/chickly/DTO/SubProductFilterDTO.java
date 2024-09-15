package com.chickly.DTO;

import java.math.BigDecimal;

import com.chickly.DataAccessLayer.Entities.SubCategory;
import com.chickly.Enums.Color;
import com.chickly.Enums.Gender;
import com.chickly.Enums.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class SubProductFilterDTO {
    private String productName;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Size size;
    private Color color;
    private Gender gender;
    private Integer categoryId;
    private String categoryName;
    private Integer pageNumber;
    private Integer pageSize = 6;
    private Boolean isDeleted = Boolean.FALSE;
    private Boolean isNewArrival;
    private Boolean inStock = Boolean.TRUE;
    private String subCategoryName;
}