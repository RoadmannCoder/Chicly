package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "subCategory")
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "subCategory")
    private Set<Product> products;



    public void addProduct(Product product) {
        products.add(product);
        product.setSubCategory(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setSubCategory(null);
    }



}
