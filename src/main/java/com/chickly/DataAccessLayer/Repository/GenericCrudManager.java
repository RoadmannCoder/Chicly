package com.chickly.DataAccessLayer.Repository;

import com.chickly.DataAccessLayer.DBContext.EntityManagerUtil;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

public abstract class GenericCrudManager<T, U extends Object> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final Class<T> objectClass;
    protected EntityManager entityManager;

    protected GenericCrudManager(Class<T> objectClass) {
        entityManager = EntityManagerUtil.getEntityManager();
        this.objectClass = objectClass;
    }

    protected EntityManager getEntityManager() {
        return EntityManagerUtil.getEntityManager(); // Assuming EntityManagerUtil uses ThreadLocal to manage EM
    }

    public void create(T entityObject) throws RuntimeException {
        try {
            LOGGER.info("Inserting......");
            getEntityManager().persist(entityObject);
        } catch (RuntimeException ex) {
            LOGGER.error("Failed to insert entity: ", ex);
            throw ex;
        }
    }

    public T findBy(String fieldName, U genericValue) {
        try {
            String jpql = "FROM " + objectClass.getSimpleName() + " record WHERE record." + fieldName + " = :genericValue";
            Query query = getEntityManager().createQuery(jpql);
            query.setParameter("genericValue", genericValue);
            return (T) query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get the entity by field value", e);
        }
    }

    public Optional<List<T>> findAll() {

        String jpql = "SELECT all FROM " + objectClass.getSimpleName() + " all";
        return (Optional<List<T>>) Optional.ofNullable((T) entityManager.createQuery(jpql,objectClass).getResultList());
    }

    public T update(T entityObject) {
        try {
            return getEntityManager().merge(entityObject);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update entity", e);
        }
    }

    public void delete(T entityObject) {
        EntityManager em = getEntityManager();
        try {
            if (!em.contains(entityObject)) {
                entityObject = em.merge(entityObject);
            }
            em.remove(entityObject);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete entity", e);
        }
    }
    public void deleteAll(){
        EntityManager entityManager1 = getEntityManager();
        try{
            String jpql = "DELETE FROM " + objectClass.getSimpleName() ;
            Query query = getEntityManager().createQuery(jpql);
            query.executeUpdate();
            LOGGER.info("Deleted All Records successfully");
        }catch (Exception e) {
            throw new RuntimeException("Failed to delete the table", e);
        }

    }

    public T deleteById(int id) throws RuntimeException {
        try {
            T entity = getEntityManager().find(objectClass, id);
            if (entity != null) {
                getEntityManager().remove(entity);
            }
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete this item", e);
        }
    }

    public void deleteBy(String fieldName, U genericValue) {
        try {
            String jpql = "DELETE FROM " + objectClass.getSimpleName() + " record WHERE record." + fieldName + " = :genericValue";
            Query query = getEntityManager().createQuery(jpql);
            query.setParameter("genericValue", genericValue);
            query.executeUpdate();
            LOGGER.info("Record deleted successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete the entity by field value", e);
        }
    }
}
