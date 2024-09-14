package com.chickly.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryDTO {
    private Integer id;
    private Integer categoryID;
    private String name;
}
