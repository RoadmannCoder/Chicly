package com.chickly;

import com.chickly.DataAccessLayer.Entities.*;
import com.chickly.DataAccessLayer.EntitiesEmbeddedId.OrderProductId;
import com.chickly.DataAccessLayer.EntitiesEmbeddedId.ShoppingCartId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("chickly");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            // Create Address and Account
            Address address1 = new Address("123 Main St", "Cairo", "Springfield", "IL", "62701");
            Account account1 = new Account("john_doe", "password123");

            Address address2 = new Address("456 Elm St", "Giza", "Springfield", "IL", "62702");
            Account account2 = new Account("jane_smith", "password456");

            // Create Customers
            Customer customer1 = new Customer();
            customer1.setFirstName("John");
            customer1.setLastName("Doe");
            customer1.setCreditLimit(new BigDecimal("1000.00"));
            customer1.setDateOfBirth(new Date(85, 3, 12)); // Deprecated constructor
            customer1.setEmail("john.doe@example.com");
            customer1.setPhoneNumber("1234567890");
            customer1.setAddress(address1);
            customer1.setAccount(account1);

            Customer customer2 = new Customer();
            customer2.setFirstName("Jane");
            customer2.setLastName("Smith");
            customer2.setCreditLimit(new BigDecimal("1500.00"));
            customer2.setDateOfBirth(new Date(90, 6, 25)); // Deprecated constructor
            customer2.setEmail("jane.smith@example.com");
            customer2.setPhoneNumber("0987654321");
            customer2.setAddress(address2);
            customer2.setAccount(account2);

            em.persist(customer1);
            em.persist(customer2);

            // Create Products
            Product product1 = new Product("Summer T-Shirt", "Cool T-Shirt", "UNISEX", "NO");
            Product product2 = new Product("Winter Jacket", "Warm Jacket", "UNISEX", "NO");

            em.persist(product1);
            em.persist(product2);

            // Create SubProducts
            SubProduct subProduct1 = new SubProduct();
            subProduct1.setSize(SubProduct.Size.MEDIUM);
            subProduct1.setQuantity(100);
            subProduct1.setColor("Red");
            subProduct1.setPrice(new BigDecimal("19.99"));
            subProduct1.setProduct(product1);



            SubProduct subProduct2 = new SubProduct();
            subProduct2.setSize(SubProduct.Size.LARGE);
            subProduct2.setQuantity(50);
            subProduct2.setColor("Blue");
            subProduct2.setPrice(new BigDecimal("29.99"));
            subProduct2.setProduct(product2);

            em.persist(subProduct1);
            em.persist(subProduct2);

            Set<SubProduct> subProductSet1 = new HashSet<>();
            subProductSet1.add(subProduct1);
            product1.setSubProducts(subProductSet1);


            Set<SubProduct> subProductSet2 = new HashSet<>();
            subProductSet2.add(subProduct2);
            product2.setSubProducts(subProductSet2);
            // Create ShoppingCart entries
            ShoppingCart cartEntry1 = new ShoppingCart();
            cartEntry1.setId(new ShoppingCartId(customer1.getId(), subProduct1.getId()));
            cartEntry1.setCustomer(customer1);
            cartEntry1.setSubProduct(subProduct1);
            cartEntry1.setQuantity(2);

            ShoppingCart cartEntry2 = new ShoppingCart();
            cartEntry2.setId(new ShoppingCartId(customer1.getId(), subProduct2.getId()));
            cartEntry2.setCustomer(customer1);
            cartEntry2.setSubProduct(subProduct2);
            cartEntry2.setQuantity(1);

            em.persist(cartEntry1);
            em.persist(cartEntry2);



            transaction.commit();

            transaction.begin();
            Order orderEntry1 = new Order();
            orderEntry1.setStatus(Order.Status.PENDING);
            customer1.addOrder(orderEntry1);
            em.persist(orderEntry1);
            em.persist(customer1);
            OrderProductId orderProductId = new OrderProductId(orderEntry1.getId(),product1.getId());

            OrderItem orderItem1 = new OrderItem();
            orderItem1.setId(orderProductId);
            orderItem1.setQuantity(1);
            orderItem1.setProduct(product1);
            orderItem1.setOrder(orderEntry1);
            orderItem1.setPrice(subProduct1.getPrice());
            orderEntry1.getOrderItems().add(orderItem1);

            em.persist(customer1);
            em.persist(orderEntry1);
            em.persist(product1);

            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
