package com.chickly.DataAccessLayer.Repository;


import com.chickly.DataAccessLayer.DBContext.JpaUtil;
import com.chickly.DataAccessLayer.Entities.Admin;
import com.chickly.DataAccessLayer.Entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;
import java.util.Optional;

public class CustomerRepository extends GenericCrudManager<Customer, Object> {

    public CustomerRepository() {
        super(Customer.class);
    }



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
    public boolean checkUsernameIfFound(String username){
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Customer> q = cb.createQuery(Customer.class);
        Root<Customer> cus = q.from(Customer.class);
        q.select(cus).where(cb.like(cus.get("account").get("userName"),username));
        List<Customer> result3 = this.entityManager.createQuery(q).getResultList();
        if(!result3.isEmpty()){
            return true;
        }
        return false;
    }
    public boolean checkPhoneNumberIfFound(String phoneNumber){
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Customer> q = cb.createQuery(Customer.class);
        Root<Customer> cus = q.from(Customer.class);
        q.select(cus).where(cb.like(cus.get("phoneNumber"),phoneNumber));
        List<Customer> result3 = this.entityManager.createQuery(q).getResultList();
        if(!result3.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * This method is used to check if the user is the entered email and password
     * are correct so that it checks the authentication of the user
     * @param username
     * @param password
     * @return true if the email and password are correct and false if one of them or both of them are incorrect
     */
    public boolean checkUsernameAndPasswordAreValid(String username,String password){
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Customer> q = cb.createQuery(Customer.class);
        Root<Customer> cus = q.from(Customer.class);
        q.select(cus)
                .where(cb.and(
                        cb.equal(cus.get("account").get("userName"), username),
                        cb.equal(cus.get("account").get("password"), password)
                ));
        try {
            this.entityManager.createQuery(q).getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
    public Customer findyByUser(String username){
        String jpql = "Select all from Customer all where all.account.userName = :username";
        Customer customer = null;
        try {
             customer = entityManager.createQuery(jpql, Customer.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No customer found for username: " + username);
        }
        return customer;
    }
    public Customer findUserByCriteria(String userName,String email,String Id){
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Customer> q = cb.createQuery(Customer.class);
        Root<Customer> cus = q.from(Customer.class);
        Predicate predicate = cb.conjunction();
        if (userName != null && !userName.isEmpty()) {
            predicate = cb.and(predicate, cb.like(cus.get("account").get("userName"), "%" + userName + "%"));
        }
        if (email != null && !email.isEmpty()) {
            predicate = cb.and(predicate, cb.like(cus.get("email"), "%" + email + "%"));
        }
        if (Id != null && !Id.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(cus.get("id"), Id));
        }
        q.select(cus).where(predicate);
        Customer customer=null;
        try {
            customer = this.entityManager.createQuery(q).getSingleResult();
            return customer;
        } catch (NoResultException e) {
            return customer;
        }

    }
}
