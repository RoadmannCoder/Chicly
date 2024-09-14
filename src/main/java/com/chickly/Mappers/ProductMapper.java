package com.chickly.Mappers;
import com.chickly.DTO.ProductViewDTO;
import com.chickly.DataAccessLayer.Entities.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductMapper {
    public static Product fromProductViewDTOToProductEntity(ProductViewDTO dto) throws ParseException {
        if (dto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());

        return product;
    }
    public static List<ProductViewDTO> fromProductEntityToProductViewDTO(Optional<List<Product>> productsOptional) {
        if (productsOptional.isEmpty()) {
            return null;
        }
        List<ProductViewDTO> ProductViewDTOList = new ArrayList<>();

        if (productsOptional.isPresent()) {
            List<Product> productsList = productsOptional.get();

            for (Product product : productsList) {
                ProductViewDTO dto = ProductViewDTO.fromProduct(product);
                ProductViewDTOList.add(dto);
            }
        }

        return ProductViewDTOList;
    }

}
