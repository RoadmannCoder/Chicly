package com.chickly.DataAccessLayer.Entities;


import com.chickly.DataAccessLayer.EntitiesEmbeddedId.CustomerSubProductId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shoppingCart")
@Setter
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
public class ShoppingCartItems {
    @EmbeddedId
    private CustomerSubProductId id;


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
