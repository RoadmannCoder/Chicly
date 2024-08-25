package com.chickly.DataAccessLayer.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;


public abstract class GenericCrudManager<T> {

    private final EntityManager entityManager;
    private final Class<T> objectClass;
    protected GenericCrudManager(EntityManager entityManager,Class<T> objectClass){
        this.entityManager = entityManager;
        this.objectClass = objectClass;
    }
    protected void create(T entityObject){
        System.out.println("Inserting......");
        entityManager.getTransaction().begin();
        entityManager.persist(entityObject);
        entityManager.getTransaction().commit();
    }
    protected T readBy(T genericValue){
        return entityManager.find(objectClass,genericValue);
    }
    protected List<T> readAll(){
        String jpql = "Select all From "+objectClass.getSimpleName()+" all";
        return entityManager.createQuery(jpql,objectClass).getResultList();
    }
    protected T update(T entityObject){
        entityManager.getTransaction().begin();
        T updateEntity = entityManager.merge(entityObject);
        entityManager.getTransaction().commit();
        return updateEntity;
    }
    protected void delete(T entityObject){
        entityManager.getTransaction().begin();
        if (!entityManager.contains(entityObject)) {
            entityObject = entityManager.merge(entityObject);
        }

        entityManager.remove(entityObject);
        entityManager.getTransaction().commit();
    }
    protected T deleteById(int id) throws RuntimeException{
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
    protected void deleteBy(String fieldName, T genericValue){
        entityManager.getTransaction().begin();
        try {
            String jpql = "DELETE FROM " + objectClass.getSimpleName() + " record WHERE record." + fieldName + " = :genericValue";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("genericValue", genericValue);
            query.executeUpdate();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            throw new RuntimeException("Failed to delete the entity by field value", e);
        }
    }






}
