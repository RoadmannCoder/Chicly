package com.chickly.DataAccessLayer.Repository;

import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Entities.Order;
import jakarta.persistence.EntityManager;

import java.util.Date;

public class OrderRepository extends GenericCrudManager<Order ,Object>{
    public OrderRepository() {
        super( Order.class);
    }

    public void merge(Order order){
        getEntityManager().merge(order);
    }


}
