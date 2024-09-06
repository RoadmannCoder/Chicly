package com.chickly.DataAccessLayer.DBContext;

import jakarta.persistence.EntityManager;

public class EntityManagerUtil {

    private static final ThreadLocal<EntityManager> threadLocalEntityManager = new ThreadLocal<>();

    public static EntityManager getEntityManager() {
        EntityManager em = threadLocalEntityManager.get();
        if (em == null) {
            em = JpaUtil.getEntityManagerFactory().createEntityManager();
            threadLocalEntityManager.set(em);
        }
        return em;
    }

    public static void closeEntityManager() {
        EntityManager em = threadLocalEntityManager.get();
        if (em != null) {
            em.close();
            threadLocalEntityManager.remove();
        }
    }
}

