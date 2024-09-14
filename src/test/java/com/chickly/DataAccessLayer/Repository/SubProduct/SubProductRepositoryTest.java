package com.chickly.DataAccessLayer.Repository.SubProduct;

import com.chickly.DataAccessLayer.Entities.Product;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Repository.ProductRepository;
import com.chickly.DataAccessLayer.Repository.SubProductRepository;
import com.chickly.Enums.Color;
import com.chickly.Enums.Gender;
import com.chickly.Enums.Size;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SubProductRepositoryTest {

    private static SubProductRepository subProductRepository;
    private static ProductRepository productRepository;
//    private static EntityManagerFactory entityManagerFactory;
    private static Product jeanProduct;

    @BeforeAll
    public static void setUp() {
//        entityManagerFactory = Persistence.createEntityManagerFactory("h2Testing");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
        subProductRepository = new SubProductRepository();
        productRepository = new ProductRepository();

        // Initialize a sample product
        jeanProduct = new Product("Blue Jeans", "Jeans", Gender.valueOf("UNISEX"), "1");
        productRepository.create(jeanProduct);
    }

    @Test
    void testInsertSubProduct() {
        SubProduct subProduct = new SubProduct(Size.MEDIUM, 10, Color.RED, new BigDecimal(100));
        subProduct.setProduct(jeanProduct);
        subProductRepository.create(subProduct);

        SubProduct fetchedSubProduct = subProductRepository.findBy("id", subProduct.getId());
        assertNotNull(fetchedSubProduct);
        assertEquals(subProduct.getId(), fetchedSubProduct.getId());
        assertEquals(subProduct.getStock(), fetchedSubProduct.getStock());
    }

    @Test
    void testInsertEmptySubProduct() {
        SubProduct subProduct = new SubProduct();
        assertThrows(RuntimeException.class, () -> subProductRepository.create(subProduct));
    }

    @Test
    void testFindSubProductsByProductID() {
        SubProduct subProduct1 = new SubProduct(Size.MEDIUM, 10, Color.RED, new BigDecimal(100));
        subProduct1.setProduct(jeanProduct);
        subProductRepository.create(subProduct1);

        SubProduct subProduct2 = new SubProduct(Size.MEDIUM, 10, Color.RED, new BigDecimal(100));
        subProduct2.setProduct(jeanProduct);
        subProductRepository.create(subProduct2);

        List<SubProduct> fetchedSubProducts = subProductRepository.findSubCProductsByProductID(jeanProduct);

        assertNotNull(fetchedSubProducts);
        assertEquals(2, fetchedSubProducts.size());
        assertTrue(fetchedSubProducts.contains(subProduct1));
        assertTrue(fetchedSubProducts.contains(subProduct2));
    }

    @Test
    void testUpdateSubProduct() {
        SubProduct subProduct = new SubProduct(Size.MEDIUM, 10, Color.RED, new BigDecimal(100));
        subProduct.setProduct(jeanProduct);
        subProductRepository.create(subProduct);

        subProduct.setColor(Color.RED);
        subProductRepository.update(subProduct);

        SubProduct updatedSubProduct = subProductRepository.findBy("id", subProduct.getId());
        assertNotNull(updatedSubProduct);
        assertEquals("Yellow", updatedSubProduct.getColor());
    }

    @Test
    void testDeleteSubProduct() {
        SubProduct subProduct = new SubProduct(Size.MEDIUM, 10,Color.RED, new BigDecimal(100));
        subProduct.setProduct(jeanProduct);
        subProductRepository.create(subProduct);

        subProductRepository.deleteById(subProduct.getId());

        assertThrows(RuntimeException.class, () -> subProductRepository.findBy("id", subProduct.getId()));
    }

//    @AfterAll
//    public static void tearDown() {
//        subProductRepository.entityManager.close();
//        productRepository.entityManager.close();
//        entityManagerFactory.close();
//    }
}
