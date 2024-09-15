package com.chickly.BussinesLayer;

import com.chickly.DTO.ProductViewDTO;
import com.chickly.DataAccessLayer.Entities.Product;
import com.chickly.DataAccessLayer.Entities.SubCategory;
import com.chickly.DataAccessLayer.Repository.ProductRepository;
import com.chickly.DataAccessLayer.Repository.SubCategoryRepository;
import com.chickly.Enums.Gender;
import com.chickly.Mappers.ProductMapper;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ProductService {
    private ProductRepository productRepository;
    public ProductService(){this.productRepository = new ProductRepository();}

    public List<ProductViewDTO> getAllProdcutsForView() {
        return ProductMapper.fromProductEntityToProductViewDTO(productRepository.findAll());
    }

    public void createProduct(HttpServletRequest req) {
        String subCategoryId = req.getParameter("subCategory");
        String productName = req.getParameter("productName");
        String productDesc = req.getParameter("productDesc");
        String productGender = req.getParameter("productGender");
        System.out.println("**************************"+subCategoryId);
        Product product = new Product();
        product.setGender(Gender.valueOf(productGender));
        product.setDescription(productDesc);
        product.setName(productName);
        product.setIsDeleted("No");
        SubCategoryRepository subCategoryRepository = new SubCategoryRepository();
        ProductRepository productRepository = new ProductRepository();
        SubCategory sub = subCategoryRepository.findBy("id",subCategoryId);
        product.setSubCategory(sub);
        productRepository.create(product);
    }

    public Long getTotalProducts() {
        ProductRepository productRepository = new ProductRepository();
        return productRepository.countAllProducts();
    }
}
