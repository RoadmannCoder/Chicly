package com.chickly.DataAccessLayer.Repository.Product;

import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Entities.Product;
import com.chickly.DataAccessLayer.Repository.ProductRepository;
import com.chickly.Enums.Gender;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {
    private static ProductRepository productRepository;
//    private static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    public static void setUp() {
//        entityManagerFactory = Persistence.createEntityManagerFactory("h2Testing");
        productRepository = new ProductRepository();

    }

    @Test
    void testInsertProductToDB(){
        Product product = new Product("Socks","Long Socks", Gender.valueOf("UNISEX"),"1");
        productRepository.create(product);
        assertNotNull(product.getId());
        assertSame(productRepository.findBy("id",product.getId()).getId(),product.getId());

    }

    @Test
    void testUpdateProductToDB(){
        Product product = productRepository.findBy("id",1);
        Product product1 = new Product(product.getDescription(),product.getName(),product.getGender(),product.getIsDeleted());
        product.setName("Ghandy");
        productRepository.update(product);
        assertNotSame(productRepository.findBy("id",product.getId()).getName(),product1.getName());
        assertSame(productRepository.findBy("id",product.getId()).getName(),product.getName());
    }
    @Test
    void testDeleteProductFromDB(){
        productRepository.deleteById(1);
        assertThrows(RuntimeException.class,()->productRepository.findBy("id",1));
    }

//    @AfterAll
//    public static void tearDownClass(){
//        productRepository.entityManager.close();
//        entityManagerFactory.close();
//    }

}
