package com.chickly;

import com.chickly.DataAccessLayer.Entities.Account;
import com.chickly.DataAccessLayer.Entities.Address;
import com.chickly.DataAccessLayer.Entities.Admin;
import com.chickly.DataAccessLayer.Entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("chickly");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Account account = new Account("jjj","asdas");
        Account account2 = new Account("jjjjasd","123213");
        Account account3 = new Account("jjsjj","123213");
        Admin admin = new Admin(account);
        Address address = new Address("aasdsd", "asdas", "asd", "asd");

        Customer customer2 = new Customer("ahmasded", "mandor", new BigDecimal(213) ,Date.valueOf(LocalDate.now()), "asasddddgmail", "01205405",address,account3 );



        entityManager.persist(admin);
        entityManager.persist(customer2);


        Customer customer = new Customer("asdada", "mandssor", new BigDecimal(213) ,Date.valueOf(LocalDate.now()),
                "asdasaddgmail.com", "01205405",address,account2 );



        entityManager.persist(customer);
        entityManager.getTransaction().commit();



    }
}
