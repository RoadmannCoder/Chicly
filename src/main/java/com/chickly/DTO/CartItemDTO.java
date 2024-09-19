package com.chickly.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItemDTO {

    @JsonProperty("subProduct")
    private SubProductDTO subProduct;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonCreator
    public CartItemDTO(@JsonProperty("subProduct") SubProductDTO subProduct, @JsonProperty("quantity") Integer quantity) {
        this.subProduct = subProduct;
        this.quantity = quantity;
    }

    // Getters
    public SubProductDTO getSubProduct() {
        return subProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    // Setters
    public void setSubProduct(SubProductDTO subProduct) {
        this.subProduct = subProduct;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

