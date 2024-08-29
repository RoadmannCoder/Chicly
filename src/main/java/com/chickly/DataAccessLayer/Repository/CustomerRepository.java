package com.chickly.DataAccessLayer.Repository;


import com.chickly.DataAccessLayer.Entities.Customer;
import jakarta.persistence.EntityManager;

public class CustomerRepository extends GenericCrudManager<Customer, Object> {

    public CustomerRepository(EntityManager entityManager) {
        super(entityManager, Customer.class);
    }


}
