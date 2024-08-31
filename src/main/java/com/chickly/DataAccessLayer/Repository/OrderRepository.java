package com.chickly.DataAccessLayer.Repository;

import com.chickly.DataAccessLayer.Entities.Order;
import jakarta.persistence.EntityManager;

public class OrderRepository extends GenericCrudManager<Order ,Object>{
    public OrderRepository(EntityManager entityManager) {
        super(entityManager, Order.class);
    }
}
