package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String description;

    @NonNull
    private String name;

    @NonNull
    private String gender;

    @NonNull
    private String isDeleted;


    @ManyToOne
    @JoinColumn(name = "subCategory_id")
    private SubCategory subCategory;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<SubProduct> subProducts;


    public void addSubProduct(SubProduct subProduct) {
        subProducts.add(subProduct);
        subProduct.setProduct(this);
    }

    public void removeSubProduct(SubProduct subProduct) {
        subProducts.remove(subProduct);
        subProduct.setProduct(null);
    }
}
