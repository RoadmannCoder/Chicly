package com.chickly.DataAccessLayer.Repository.SubCategory;

import com.chickly.DataAccessLayer.Entities.Category;
import com.chickly.DataAccessLayer.Entities.SubCategory;
import com.chickly.DataAccessLayer.Repository.CategoryRepository;
import com.chickly.DataAccessLayer.Repository.SubCategoryRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SubCategoryRepositoryTest {
    private static SubCategoryRepository subCategoryRepository;
    private static CategoryRepository categoryRepository;
//    private static EntityManagerFactory entityManagerFactory;
    private static Category topCategory;
    private static Category bottomCategory;
    private static Category xCategory;

    @BeforeAll
    public static void setUp() {
//        entityManagerFactory = Persistence.createEntityManagerFactory("h2Testing");
        subCategoryRepository = new SubCategoryRepository();
        categoryRepository = new CategoryRepository();
        topCategory = new Category("Top");
        bottomCategory = new Category("Bottom");
        xCategory = new Category("xCategory");
        categoryRepository.create(topCategory);
        categoryRepository.create(bottomCategory);
        categoryRepository.create(xCategory);

    }
    @Test
    @Order(1)
    void testInsertSubCategoryToDB(){

        SubCategory subCategory = new SubCategory(topCategory,"T-Shirt");
        subCategoryRepository.create(subCategory);
        assertNotNull(subCategory.getId());
        assertSame(subCategoryRepository.findBy("id",subCategory.getId()).getId(),subCategory.getId());
        assertSame(subCategoryRepository.findBy("name",subCategory.getName()).getName(),subCategory.getName());

    }

    @Test
    @Order(2)
    void testInsertEmptySubCategoryToDB(){
        SubCategory subCategory = new SubCategory();
        assertThrows(RuntimeException.class,()-> subCategoryRepository.create(subCategory));
    }

    @Test
    @Order(3)
    void testInsertSameSubCategoryName(){

        SubCategory subCategory1 = new SubCategory(bottomCategory,"Shorts");
        SubCategory subCategory2 = new SubCategory(bottomCategory,"Shorts");
        subCategoryRepository.create(subCategory1);
        assertThrows(RuntimeException.class,()-> subCategoryRepository.create(subCategory2));

    }

    @Test
    @Order(4)
    void testfindSubCategoriesByCategoryID(){

        SubCategory subCategory1 = new SubCategory(xCategory,"Crop Tops");subCategoryRepository.create(subCategory1);
        SubCategory subCategory2 = new SubCategory(xCategory,"Shirts");subCategoryRepository.create(subCategory2);
        SubCategory subCategory3 = new SubCategory(xCategory,"Blouses");subCategoryRepository.create(subCategory3);
        SubCategory subCategory4 = new SubCategory(xCategory,"Sweaters");subCategoryRepository.create(subCategory4);
        List<SubCategory> subCategories = new ArrayList<>();
        subCategories.add(subCategory1);subCategories.add(subCategory2);
        subCategories.add(subCategory3);subCategories.add(subCategory4);
        assertEquals(subCategoryRepository.findSubCategoriesByCategoryID(xCategory),subCategories);

    }

    @Test
    @Order(5)
    void testUpdateSubCategoryToDB(){
        SubCategory subCategory = subCategoryRepository.findBy("id",1);
        SubCategory subCategory1 = new SubCategory(subCategory.getCategory(),subCategory.getName());
        subCategory.setName("Hoddies");
        subCategoryRepository.update(subCategory);
        assertNotSame(subCategoryRepository.findBy("id",subCategory.getId()).getName(),subCategory1.getName());
        assertSame(subCategoryRepository.findBy("id",subCategory.getId()).getName(),subCategory.getName());
    }

    @Test
    @Order(6)
    void testDeleteSubCategoryFromDB(){
        subCategoryRepository.deleteById(1);
        assertThrows(RuntimeException.class,()->subCategoryRepository.findBy("id",1));

    }

//    @AfterAll
//    public static void tearDownClass(){
//        subCategoryRepository.entityManager.close();
//        entityManagerFactory.close();
//    }


}
