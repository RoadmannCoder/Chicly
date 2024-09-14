package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import com.chickly.Enums.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String description;

    @NonNull
    @NotEmpty
    private String name;

    @Enumerated(EnumType.STRING)
    @NonNull
    @NotNull
    private Gender gender;

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
