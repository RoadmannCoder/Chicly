package com.chickly.DataAccessLayer.Repository;

import com.chickly.DataAccessLayer.Entities.CartItems;
import com.mysql.cj.exceptions.CJPacketTooBigException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CartRepository extends GenericCrudManager<CartItems,Object> {

    public CartRepository(EntityManager entityManager) {
        super(entityManager, CartItems.class);
    }



}
