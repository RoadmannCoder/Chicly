package com.chickly.DataAccessLayer.Repository;


import com.chickly.DataAccessLayer.DBContext.JpaUtil;
import com.chickly.DataAccessLayer.Entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class CustomerRepository extends GenericCrudManager<Customer, Object> {

    public CustomerRepository(EntityManager entityManager) {
        super(entityManager, Customer.class);
    }

    /**
     * This method is used to check if the email is already in the database
     *
     * @param email
     * @return true if the email is found and return false if it's not found
     */
    public boolean checkEmailIfFound(String email){
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Customer> q = cb.createQuery(Customer.class);
        Root<Customer> cus = q.from(Customer.class);
        q.select(cus).where(cb.like(cus.get("email"),email));
        List<Customer> result3 = this.entityManager.createQuery(q).getResultList();
        if(!result3.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * This method is used to check if the user is the entered email and password
     * are correct so that it checks the authentication of the user
     * @param email
     * @param password
     * @return true if the email and password are correct and false if one of them or both of them are incorrect
     */
    public boolean checkEmailAndPasswordAreValid(String email,String password){
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Customer> q = cb.createQuery(Customer.class);
        Root<Customer> cus = q.from(Customer.class);
        q.select(cus)
                .where(cb.and(
                        cb.equal(cus.get("email"), email),
                        cb.equal(cus.get("account").get("password"), password)
                ));
        try {
            Customer result = this.entityManager.createQuery(q).getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
