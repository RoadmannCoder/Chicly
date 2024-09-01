package com.chickly.DataAccessLayer.Repository.SubProduct;

import com.chickly.DataAccessLayer.Entities.Category;
import com.chickly.DataAccessLayer.Entities.Product;
import com.chickly.DataAccessLayer.Entities.SubCategory;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Repository.CategoryRepository;
import com.chickly.DataAccessLayer.Repository.ProductRepository;
import com.chickly.DataAccessLayer.Repository.SubCategoryRepository;
import com.chickly.DataAccessLayer.Repository.SubProductRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubProductRepositoryTest {
    private static SubProductRepository subProductRepository;
    private static ProductRepository productRepository;
    private static EntityManagerFactory entityManagerFactory;
    private static Product jeanProduct;
    private static Product shirtProduct;
    private static Product shoesProduct;

    @BeforeAll
    public static void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("h2Testing");
        subProductRepository = new SubProductRepository(entityManagerFactory.createEntityManager());
        productRepository = new ProductRepository(entityManagerFactory.createEntityManager());
        jeanProduct = new Product("Blue Jeans","Jeans","UNISEX","1");
        shirtProduct = new Product("V-Neck Shirt","Shirt","UNIXSEX","1");
        shoesProduct = new Product("Sneakers Shoes","Air Jordan 1","UNISEX","1");
        productRepository.create(jeanProduct);
        productRepository.create(shirtProduct);
        productRepository.create(shoesProduct);

    }
    @Test
    void testInsertSubProductToDB(){

        SubProduct subProduct = new SubProduct(10,"Blue",new BigDecimal(100));
        subProduct.setProduct(jeanProduct);
        subProductRepository.create(subProduct);
        assertNotNull(subProduct.getId());
        assertSame(subProductRepository.findBy("id",subProduct.getId()).getId(),subProduct.getId());
        assertSame(subProductRepository.findBy("stock",subProduct.getStock()).getStock(),subProduct.getStock());

    }

    @Test
    void testInsertEmptySubProductToDB(){
        SubProduct subProduct = new SubProduct();
        assertThrows(RuntimeException.class,()-> subProductRepository.create(subProduct));
    }

    @Test
    void testfindSubProductsByProductID(){

        SubProduct subProduct1 = new SubProduct(10,"Blue",new BigDecimal(100));
        subProduct1.setProduct(jeanProduct);
        subProductRepository.create(subProduct1);
        SubProduct subProduct2 = new SubProduct(10,"Black",new BigDecimal(100));
        subProduct2.setProduct(jeanProduct);
        subProductRepository.create(subProduct2);
        SubProduct subProduct3 = new SubProduct(10,"Green",new BigDecimal(100));
        subProduct3.setProduct(jeanProduct);
        subProductRepository.create(subProduct3);
        SubProduct subProduct4 = new SubProduct(10,"Red",new BigDecimal(100));
        subProduct4.setProduct(jeanProduct);
        subProductRepository.create(subProduct4);
        List<SubProduct> subProducts = new ArrayList<>();
        subProducts.add(subProduct1);
        subProducts.add(subProduct2);
        subProducts.add(subProduct3);
        subProducts.add(subProduct4);
        assertEquals(subProductRepository.findSubCProductsByProductID(jeanProduct),subProducts);

    }
    @Test
    void testUpdateSubProductToDB(){
        SubProduct subProduct = subProductRepository.findBy("id",1);
        SubProduct subProduct1 = new SubProduct(subProduct.getStock(),subProduct.getColor(),subProduct.getPrice());
        subProduct.setColor("Yellow");
        subProductRepository.update(subProduct);
        assertNotSame(subProductRepository.findBy("id",subProduct.getId()).getColor(),subProduct1.getColor());
        assertSame(subProductRepository.findBy("id",subProduct.getId()).getColor(),subProduct.getColor());
    }

    @Test
    void testDeleteSubProductFromDB(){
        subProductRepository.deleteById(1);
        assertThrows(RuntimeException.class,()->subProductRepository.findBy("id",1));

    }




    @AfterAll
    public static void tearDownClass(){
        subProductRepository.entityManager.close();
        productRepository.entityManager.close();
        entityManagerFactory.close();
    }
}
