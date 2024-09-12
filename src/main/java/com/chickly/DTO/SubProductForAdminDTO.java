package com.chickly.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SubProductDTO {
    private String productName;
    private BigDecimal price;
    private String imageURL;
    private String color;
    private String size;
    private int stock;
}