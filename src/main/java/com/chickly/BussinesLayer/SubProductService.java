package com.chickly.BussinesLayer;

import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Repository.SubProductRepository;

import java.util.ArrayList;
import java.util.List;

public class SubProductService {

    public  List<SubProduct> getAllSubProducts (){
        SubProductRepository subProductRepository = new SubProductRepository();

        List<SubProduct> subProducts = subProductRepository.findAll();
        return subProducts;
    }
    public  List<SubProduct> findSubProductsByCategory (int categoryId){
        SubProductRepository subProductRepository = new SubProductRepository();
        List<SubProduct> subProducts = subProductRepository.findSubProductsByCategory(categoryId);
        return subProducts;
    }
}
