package com.chickly.DataAccessLayer.Entities;

import com.chickly.DataAccessLayer.EntitiesEmbeddedIds.SubProductId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "subProduct")
@Getter
@Setter
@ToString
public class SubProduct {

    @EmbeddedId
    private SubProductId id;

    @Enumerated(EnumType.STRING)
    private Size size;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "price", nullable = false)
    private float price;

    @MapsId("productId")
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

    public SubProduct(SubProductId id, Product product, Size size, int quantity, String color, float price) {
        this.id = id;
        this.product = product;
        this.size = size;
        this.quantity = quantity;
        this.color = color;
        this.price = price;
    }
}