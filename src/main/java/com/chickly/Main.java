package com.chickly;


import com.chickly.BussinesLayer.SubProductService;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Repository.SubCategoryRepository;
import com.chickly.DataAccessLayer.Repository.SubProductRepository;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<SubProduct> subProductList = new SubProductRepository().findSubProductsByCategory(1);
        subProductList.forEach(subProduct -> System.out.println(subProduct.getPrice()));
    }
}
