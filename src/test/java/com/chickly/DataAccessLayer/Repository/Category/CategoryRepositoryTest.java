package com.chickly.DataAccessLayer.Repository.Category;
import com.chickly.DataAccessLayer.Entities.Category;
import com.chickly.DataAccessLayer.Repository.CategoryRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryRepositoryTest {
    private static CategoryRepository categoryRepository;
//    private static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    public static void setUp() {
//        entityManagerFactory = Persistence.createEntityManagerFactory("h2Testing");
        categoryRepository = new CategoryRepository();
    }

    // Test create
    @Test
    @Order(1)
    void testInsertCategoryToDB(){
        Category category = new Category("Top");
        categoryRepository.create(category);
        assertNotNull(category.getId());
        assertSame(categoryRepository.findBy("id",category.getId()).getId(),category.getId());
        assertSame(categoryRepository.findBy("name",category.getName()).getName(),category.getName());
    }
    @Test
    @Order(2)
    void testInsertEmptyCategoryToDB(){
        Category category = new Category();
        assertThrows(RuntimeException.class,()-> categoryRepository.create(category));
    }

    @Test
    @Order(3)
    void testInsertSameCategoryName(){
        Category category1 = new Category("X");
        categoryRepository.create(category1);
        assertThrows(RuntimeException.class,()-> categoryRepository.create(new Category("X")));

    }
    @Test
    @Order(4)
    void testUpdateCategoryToDB(){
        Category category = categoryRepository.findBy("id",1);
        Category category1 = new Category(category.getName());
        category.setName("Bottom");
        categoryRepository.update(category);
        assertNotSame(categoryRepository.findBy("id",category.getId()).getName(),category1.getName());
        assertSame(categoryRepository.findBy("id",category.getId()).getName(),category.getName());
    }

    // Test delete
    @Test
    @Order(5)
    void testDeleteCategoryFromDB(){
        categoryRepository.deleteById(1);
        assertThrows(RuntimeException.class,()->categoryRepository.findBy("id",1));

    }

//
//    @AfterAll
//    public static void tearDownClass(){
//        categoryRepository.entityManager.close();
//        entityManagerFactory.close();
//    }

}
