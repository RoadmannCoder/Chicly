package com.chickly.DataAccessLayer.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public abstract class GenericCrudManager<T, U extends Object > {

    public final EntityManager entityManager;
    private final Class<T> objectClass;
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    protected GenericCrudManager(EntityManager entityManager,Class<T> objectClass){
        this.entityManager = entityManager;
        this.objectClass = objectClass;
    }
    public void create(T entityObject) throws RuntimeException {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            System.out.println("Inserting......");
            transaction.begin();
            entityManager.persist(entityObject);
            transaction.commit();
        }catch(RuntimeException ex){
            if(transaction.isActive()){
                transaction.rollback();
            }
            System.err.println("Transaction failed and rolled back: " + ex.getMessage());
            LOGGER.error("Rollback failure", ex);

        }
    }
    public T findBy(String fieldName,U genericValue){

        try {
            String jpql = "FROM " + objectClass.getSimpleName() + " record WHERE record." + fieldName + " = :genericValue";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("genericValue", genericValue);
            return (T) query.getSingleResult();
        }catch (Exception e){
            throw new RuntimeException("Failed to get the entity by field value", e);
        }
    }
    public List<T> findAll(){
        String jpql = "Select all From "+objectClass.getSimpleName()+" all";
        return entityManager.createQuery(jpql,objectClass).getResultList();
    }
    public T update(T entityObject){
        entityManager.getTransaction().begin();
        T updateEntity = entityManager.merge(entityObject);
        entityManager.getTransaction().commit();
        return updateEntity;
    }
    public void delete(T entityObject){
        entityManager.getTransaction().begin();
        if (!entityManager.contains(entityObject)) {
            entityObject = entityManager.merge(entityObject);
        }

        entityManager.remove(entityObject);
        entityManager.getTransaction().commit();
    }
    public T deleteById(int id) throws RuntimeException{
        try {

            entityManager.getTransaction().begin();

            T entity = entityManager.find(objectClass, id);
            if (entity != null) {
                entityManager.remove(entity);
            }

            entityManager.getTransaction().commit();
            return entity;
        }catch (Exception e){
            throw new RuntimeException("cannot delete this item");
        }
    }
    public void deleteBy(String fieldName, U genericValue){
        entityManager.getTransaction().begin();
        try {
            String jpql = "DELETE FROM " + objectClass.getSimpleName() + " record WHERE record." + fieldName + " = :genericValue";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("genericValue", genericValue);
            query.executeUpdate();
            System.out.println("Record Got Deleted");
            entityManager.getTransaction().commit();
        }catch (Exception e){
            throw new RuntimeException("Failed to delete the entity by field value", e);
        }
    }






}
