package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "subProduct")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class SubProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NonNull

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

    @OneToMany(mappedBy = "subProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ShoppingCartItems> shoppingCartItems;




}