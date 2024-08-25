package com.chickly.DataAccessLayer.Repository;

import com.chickly.DataAccessLayer.DBContext.JpaUtil;
import com.chickly.DataAccessLayer.Entities.Customer;
import jakarta.persistence.EntityManager;

public class CustomerRepository extends GenericCrudManager<Customer> {

    protected CustomerRepository() {
        super(JpaUtil.getEntityManagerFactory().createEntityManager(), Customer.class);
    }
}
