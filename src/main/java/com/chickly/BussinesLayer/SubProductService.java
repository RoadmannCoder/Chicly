package com.chickly.BussinesLayer;

import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Repository.SubProductRepository;

import java.util.List;
import java.util.Optional;

public class SubProductService {

    public Optional<List<SubProduct>> getAllSubProducts (){
        SubProductRepository subProductRepository = new SubProductRepository();

        Optional<List<SubProduct>> subProducts = subProductRepository.findAll();
        return subProducts;
    }
    public Optional<List<SubProduct>> findSubProductsByCategory (int categoryId){
        SubProductRepository subProductRepository = new SubProductRepository();
        List<SubProduct> subProducts = subProductRepository.findSubProductsByCategory(categoryId);
        return Optional.ofNullable(subProducts);
    }
}
