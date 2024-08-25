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

        Account account = new Account("admin","asdas");
        Account account2 = new Account("user","123213");
        Account account3 = new Account("user1","123213");
        Admin admin = new Admin(account);
        Address address = new Address("asd", "asdas", "asd", "asd");
        Customer customer = new Customer("a", "mandor", new BigDecimal(213) ,Date.valueOf(LocalDate.now()),
        "asdhgmail.com", "01205405",address,account2 );
        Customer customer2 = new Customer("ahmed", "mandor", new BigDecimal(213) ,Date.valueOf(LocalDate.now()), "asdhgmail", "01205405",address,account3 );




        entityManager.persist(admin);
        entityManager.persist(customer);
        entityManager.persist(customer2);
        entityManager.getTransaction().commit();



    }
}
