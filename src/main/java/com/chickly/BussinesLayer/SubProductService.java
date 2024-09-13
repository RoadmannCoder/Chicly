package com.chickly.BussinesLayer;

import com.chickly.DTO.SubProductDTO;
import com.chickly.DTO.SubProductFilterDTO;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Repository.SubProductRepository;
import com.chickly.Mappers.SubProductMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
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
        List<SubProduct> subProducts = subProductRepository.findSubProductsByFilters(filterDTO);

        return subProducts.stream()
                .map(SubProductMapper::convertEntityToDTO)
                .collect(Collectors.toList());
    }
    public long countFilteredSubProducts(SubProductFilterDTO filterDTO) {
        return subProductRepository.countSubProductsByFilters(filterDTO);
    }
    public SubProductDTO convertJsonToSubProductDTO(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }

        String productJson = jsonBuilder.toString();
        System.out.println("Received JSON: " + productJson);

        // Convert JSON string back to a subProduct object using Gson
        Gson gson = new Gson();
        SubProductDTO subProduct = gson.fromJson(productJson, SubProductDTO.class);

        return subProduct;
    }
    public SubProductDTO findSubProductByID(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("product"));
        SubProduct subProduct = subProductRepository.findBy("id",id);
        SubProductDTO subProductDTO = SubProductMapper.convertEntityToDTO(subProduct);
        return subProductDTO;

    }

}
