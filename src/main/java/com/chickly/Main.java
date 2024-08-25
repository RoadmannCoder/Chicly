package com.chickly;

import com.chickly.DataAccessLayer.Entities.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("chicly");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
    }
}
