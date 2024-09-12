package com.chickly.BussinesLayer;

import com.chickly.DTO.ProductViewDTO;
import com.chickly.DataAccessLayer.Repository.ProductRepository;
import com.chickly.Mappers.ProductMapper;

import java.util.List;

public class ProductService {
    private ProductRepository productRepository;
    public ProductService(){this.productRepository = new ProductRepository();}

    public List<ProductViewDTO> getAllProdcutsForView() {
        return ProductMapper.fromProductEntityToProductViewDTO(productRepository.findAll());
    }

}
