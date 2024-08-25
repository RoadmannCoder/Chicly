package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "subProduct")
@Getter
@Setter
@ToString
public class SubProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Size size;

    @NonNull
    private int quantity;
    @NonNull
    private String color;
    @NonNull
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public enum Size {
        SMALL,
        MEDIUM,
        LARGE,
        XLARGE
    }

    public SubProduct() {
    }

    public SubProduct( Product product, Size size, int quantity, String color, BigDecimal price) {
        this.product = product;
        this.size = size;
        this.quantity = quantity;
        this.color = color;
        this.price = price;
    }
}