package com.chickly.DataAccessLayer.DBContext;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory MANAGER_FACTORY = buildEntityManagerFactory();
    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            return Persistence.createEntityManagerFactory("chicly");
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("Initial EntityManagerFactory creation failed." + ex);
        }
    }
    public static EntityManagerFactory getEntityManagerFactory() {
        return MANAGER_FACTORY;
    }
    public static void shutdownEntityManagerFactory() {
        if (MANAGER_FACTORY != null) {
            MANAGER_FACTORY.close();
        }
    }

}
