package com.chickly.DataAccessLayer.Repository;

import com.chickly.DataAccessLayer.Entities.CartItems;
import jakarta.persistence.EntityManager;

public class CartRepository extends GenericCrudManager<CartItems,Object> {

    protected CartRepository(EntityManager entityManager) {
        super(entityManager, CartItems.class);
    }

}
