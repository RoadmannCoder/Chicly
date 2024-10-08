package com.chickly.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.metamodel.model.domain.IdentifiableDomainType;
import lombok.*;

import java.io.Serializable;
import java.lang.invoke.StringConcatFactory;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubProductDTO implements Serializable {

    private int id;
    private String productName;
    private BigDecimal price;
    private String imageURL;
    private String color;
    private String size;
    private String description;
    private int stock;
    private int quantity;
    private String subCategoryName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubProductDTO that = (SubProductDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    private Boolean isNewArrival;
    private Boolean isDeleted ;
}