package com.chickly.BussinesLayer;

import com.chickly.DTO.SubProductDTO;
import com.chickly.DTO.SubProductFilterDTO;
import com.chickly.DTO.SubProductForAdminDTO;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Repository.SubProductRepository;
import com.chickly.Mappers.SubProductMapper;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.BufferedReader;
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
    public List<SubProductDTO> getAllSubProductDTOs (){
        SubProductRepository subProductRepository = new SubProductRepository();

        List<SubProductDTO> subProducts = SubProductMapper.fromSubProductEntityToSubProductViewDTO(subProductRepository.findAll());
        return subProducts;
    }
//    public List<SubProductForAdminDTO> getAllSubProductForAdminDTOs (HttpServletRequest req){
//        SubProductRepository subProductRepository = new SubProductRepository();
//
//        List<SubProductForAdminDTO> subProducts = SubProductMapper.fromSubProductEntityToSubProductForAdminDTO(subProductRepository.findAll());
//        return subProducts;
//    }
    public List<SubProductForAdminDTO> getAllSubProductForAdminDTOs (HttpServletRequest req){
        SubProductRepository subProductRepository = new SubProductRepository();
        String subcategoryId = req.getParameter("subcategory");
        System.out.println(subcategoryId);
        String searchId = req.getParameter("searchId");
        List<SubProductForAdminDTO> subProducts = new ArrayList<>();
        if(subcategoryId !=null){

            subProducts = SubProductMapper.
                    fromSubProductEntityToSubProductForAdminDTO(Optional.ofNullable(subProductRepository.findBySubCategoryName(subcategoryId)));
        }else if(searchId !=null){
            subProducts.add(SubProductMapper.convertEntityToSubProdcutAdminDTO(subProductRepository.findBy("id",searchId)));
        }else{
            subProducts = SubProductMapper.
                    fromSubProductEntityToSubProductForAdminDTO(subProductRepository.findAll());
        }

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
    public void addSubProduct(SubProductDTO subProduct,String mainProductId) {
        SubProduct subProductEntity = SubProductMapper.converSubProductDTOToEntity(subProduct,mainProductId);
        subProductRepository.create(subProductEntity);
    }

    public SubProductDTO createSubProductDTO(HttpServletRequest request) throws ServletException, IOException {
        String colorParam = request.getParameter("color");
        String mainProductId = request.getParameter("mainProduct");
        String size = request.getParameter("size");
//        String productName = request.getParameter("productName");
        int stock = Integer.parseInt(request.getParameter("stock"));
        BigDecimal price = new BigDecimal(request.getParameter("price"));

        SubProductDTO subProduct = new SubProductDTO();
        subProduct.setProductName(mainProductId);
        subProduct.setStock(stock);
        subProduct.setPrice(price);
        subProduct.setColor(colorParam);
        subProduct.setSize(size);

        Part imagePart = request.getPart("image");
        if (imagePart != null && imagePart.getSize() > 0) {
            String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
            String uploadDir = "uploads/"; // Define the directory to store uploaded images
            File uploads = new File(request.getServletContext().getRealPath("") + File.separator + uploadDir);
            if (!uploads.exists()) {
                uploads.mkdirs(); // Create the directory if it doesn't exist
            }
            File file = new File(uploads, fileName);
            try (InputStream input = imagePart.getInputStream()) {
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            subProduct.setImageURL(uploadDir + fileName);
        }
        return subProduct;
    }

    public SubProductForAdminDTO getSubProductById(String subproductId) {
        return SubProductMapper.convertEntityToSubProdcutAdminDTO(
                (subProductRepository.findBy("id",subproductId)));
    }

    public void updateSubProduct(HttpServletRequest request) {
        String subProductId = request.getParameter("subProductId");
        String stockStr = request.getParameter("quantity");
        String priceStr = request.getParameter("price");

        Integer stock = Integer.parseInt(stockStr);
        BigDecimal price = new BigDecimal(priceStr);
        subProductRepository.updateSubProductQuantityAndPrice(subProductId,stock,price);
    }
    public void deleteSubProduct(HttpServletRequest request) {
        String subProductId = request.getParameter("subProductId");
        subProductRepository.deleteSubproductById(subProductId);
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
