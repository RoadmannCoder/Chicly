package com.chickly.DTO;

import com.chickly.DataAccessLayer.Entities.SubProduct;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

public class OrderItemDTO {

    private String quantity;
    private String price;
    private String productName;

    public OrderItemDTO() {
    }

    public OrderItemDTO(String quantity, String price, String productName) {
        this.quantity = quantity;
        this.price = price;
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
