package com.chickly.DataAccessLayer.Repository.Product;

import com.chickly.DataAccessLayer.Entities.Product;
import com.chickly.DataAccessLayer.Repository.ProductRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {
    private ProductRepository productRepository;
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("h2Testing");
        productRepository = new ProductRepository(entityManagerFactory.createEntityManager());

    }

    @Test
    void createSubProductsTest(){
        Product product = new Product("asdsad", "asds", "male", "sad");
        productRepository.create(product);
        assertNotNull(product.getId());
        assertSame(productRepository.entityManager.find(Product.class, 1).getId(), product.getId());
    }


}
