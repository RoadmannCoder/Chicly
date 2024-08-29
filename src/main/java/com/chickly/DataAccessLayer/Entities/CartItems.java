package com.chickly.DataAccessLayer.Entities;


import com.chickly.DataAccessLayer.EntitiesEmbeddedId.ShoppingCartId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shoppingCart")
@Setter
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
public class CartItems {
    @EmbeddedId
    private ShoppingCartId id;


    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @MapsId("subProductId")
    @JoinColumn(name = "subproduct_id", nullable = false)
    private SubProduct subProduct;

    @Column(name="quantity")
    private Integer quantity;



}
