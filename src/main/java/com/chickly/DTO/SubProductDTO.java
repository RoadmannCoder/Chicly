package com.chickly.DTO;

import lombok.*;

import java.lang.invoke.StringConcatFactory;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubProductDTO {
    private int id;
    private String productName;
    private BigDecimal price;
    private String imageURL;
    private String color;
    private String size;
    private String description;
    private int stock;
    private Boolean isNewArrival;
    private Boolean isDeleted ;
}