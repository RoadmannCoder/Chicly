package com.chickly.DataAccessLayer.Repository.Category;

import com.chickly.DataAccessLayer.Entities.Account;
import com.chickly.DataAccessLayer.Entities.Address;
import com.chickly.DataAccessLayer.Entities.Category;
import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Repository.CategoryRepository;
import com.chickly.DataAccessLayer.Repository.CustomerRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class CategoryRepositoryTest {
    private static CategoryRepository categoryRepository;
    private static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    public static void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("h2Testing");
        categoryRepository = new CategoryRepository(entityManagerFactory.createEntityManager());
    }

    // Test create
    @Test
    void testInsertCustomerToDB(){
        Category category = new Category("Top");
        categoryRepository.create(category);
        assertNotNull(category.getId());
        assertSame(categoryRepository.findBy("id",category.getId()).getId(),category.getId());
    }
    // Test findBy

    // Test findAll

    // Test update

    // Test delete

    // Test deleteByID
}
