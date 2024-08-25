package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name="description", length=50)
    private String description;

    @Column(name="name", length=50)
    private String name;

    @Column(name="gender", length=50)
    private String gender;

    @Column(name="isDeleted", length=50)
    private String isDeleted;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<SubProduct> subProducts;

    public Product() {
    }

    public void addSubProduct(SubProduct subProduct) {
        subProducts.add(subProduct);
        subProduct.setProduct(this);
    }

    public void removeSubProduct(SubProduct subProduct) {
        subProducts.remove(subProduct);
        subProduct.setProduct(null);
    }
}
