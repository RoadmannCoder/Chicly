package com.chickly.BussinesLayer;

import com.chickly.DTO.SubProductDTO;
import com.chickly.DTO.SubProductFilterDTO;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Repository.SubProductRepository;
import com.chickly.Mappers.SubProductMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SubProductService {

    private SubProductRepository subProductRepository;
    public  SubProductService(){
        subProductRepository = new SubProductRepository();
    }

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
    public List<SubProductDTO> filterSubProducts(SubProductFilterDTO filterDTO) {
        List<SubProduct> subProducts = subProductRepository.findSubProductsByFilters(
                filterDTO.getCategoryId(),
                filterDTO.getProductName(),
                filterDTO.getColor(),
                filterDTO.getSize(),
                filterDTO.getMinPrice(),
                filterDTO.getMaxPrice(),
                filterDTO.getPageNumber(),
                filterDTO.getPageSize()
        );

        return subProducts.stream()
                .map(SubProductMapper::convertEntityToDTO)
                .collect(Collectors.toList());
    }
    public long countFilteredSubProducts(SubProductFilterDTO filterDTO) {
        return subProductRepository.countSubProductsByFilters(
                filterDTO.getCategoryId(),
                filterDTO.getProductName(),
                filterDTO.getColor(),
                filterDTO.getSize(),
                filterDTO.getMinPrice(),
                filterDTO.getMaxPrice()
        );
    }

}
