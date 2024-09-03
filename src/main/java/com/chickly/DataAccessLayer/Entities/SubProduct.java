package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Size size;

    @NonNull
    private Integer stock;
    @NonNull
    @NotNull
    private String color;
    @NonNull
    @NotNull
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


}