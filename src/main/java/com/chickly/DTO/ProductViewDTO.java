package com.chickly.DTO;

import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Entities.Product;
import com.chickly.DataAccessLayer.Entities.SubCategory;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.NonNull;

public class ProductViewDTO {
    private Integer id;
    private String name;

    public ProductViewDTO() {
    }

    public ProductViewDTO(Integer id, String name) {
        this.id = id;
        this.name = name;

    }
    public ProductViewDTO(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        this.id = Integer.parseInt(id);
        this.name = name;


    }
    public static ProductViewDTO fromProduct(Product product) {
        return new ProductViewDTO(
                product.getId(),
                product.getName()
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
