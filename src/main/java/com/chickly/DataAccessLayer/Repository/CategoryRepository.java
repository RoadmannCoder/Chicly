package com.chickly.DataAccessLayer.Repository;

import com.chickly.DataAccessLayer.Entities.Category;

import jakarta.persistence.EntityManager;

public class CategoryRepository extends GenericCrudManager<Category, Object> {
    public CategoryRepository() {
        super( Category.class);
    }

}
