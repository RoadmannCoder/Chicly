package com.chickly.DataAccessLayer.EntitiesEmbeddedId;

import lombok.*;



@Getter
@Setter
@NoArgsConstructor
public class OrderProductId {
    private Integer subProduct;
    private Integer order;
}
