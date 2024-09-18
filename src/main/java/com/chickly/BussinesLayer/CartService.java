package com.chickly.BussinesLayer;

import com.chickly.DTO.SubProductDTO;
import com.chickly.DataAccessLayer.DBContext.EntityManagerUtil;
import com.chickly.DataAccessLayer.Entities.CartItems;
import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Repository.CartRepository;
import com.chickly.DataAccessLayer.Repository.CustomerRepository;
import com.chickly.Mappers.SubProductMapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CartService implements Serializable {
    private Map<SubProductDTO, Integer> cart = new HashMap<>();


    public void addCartItem(SubProductDTO subProductDTO){
        if(cart.containsKey(subProductDTO)){
            Integer quantity = cart.get(subProductDTO) + 1;
            cart.put(subProductDTO, quantity);
        }else {
            cart.put(subProductDTO,1);
        }
    }
    public void addCartItem(SubProductDTO subProductDTO, Integer quantity){
        cart.put(subProductDTO, quantity);
    }
    public boolean removeCartItem(SubProductDTO subProductDTO){
        try{cart.remove(subProductDTO); return  true;}catch (Exception e){e.printStackTrace(); return false;}
    }
    public int getTotalQuantity(){
         return cart.values().stream().mapToInt(Integer::intValue).sum();
    }
    public BigDecimal getTotalPrice(){
        return cart.entrySet()
                .stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public Map<SubProductDTO, Integer> getItems(){
        return this.cart;
    }
    public void clear(){
        this.cart.clear();
    }
    public Integer getQuantityOfSubProduct(SubProductDTO subProductDTO){
        if(cart.containsKey(subProductDTO))
             return cart.get(subProductDTO);
        else
            return 0;
    }
    public int getTotalCartItems(){
        return this.cart.size();
    }
    public void updateProductQuantity(SubProductDTO subProductDTO, int quantity) {
        if (cart.containsKey(subProductDTO)) {
            if (quantity > 0) {
                cart.put(subProductDTO, quantity);
            } else {
                cart.remove(subProductDTO);
            }
        }
    }
    public void addToDB(List<CartItems> cartItems, Customer customer, CartService cartService){
        CustomerRepository customerRepository = new CustomerRepository();
        CartRepository cartRepository = new CartRepository();
        List<SubProductDTO> subProductList = cartService.getItems().keySet().stream().collect(Collectors.toList());

        cartItems.clear();
        if (!subProductList.isEmpty()) {
//            Set<CartItems> currentCartItems = customer.getShoppingCart();
            List<CartItems> currentCartItems = cartRepository.findAllByID(customer.getId()).get();
            if(currentCartItems.size()==0) {
                Set<Integer> newSubProductIds = subProductList.stream()
                        .map(SubProductDTO::getId)
                        .collect(Collectors.toSet());
                Set<CartItems> itemsToRemove = new HashSet<>(currentCartItems);
                itemsToRemove.forEach(cartItem -> {
                    if (!newSubProductIds.contains(cartItem.getSubProduct().getId())) {
                        currentCartItems.remove(cartItem);
                    }
                });

                subProductList.forEach(subProductDTO -> {
                    CartItems existingCartItem = currentCartItems.stream()
                            .filter(cartItem -> cartItem.getSubProduct().getId().equals(subProductDTO.getId()))
                            .findFirst()
                            .orElse(null);

                    if (existingCartItem != null) {
                        Integer newQuantity = cartService.getQuantityOfSubProduct(subProductDTO);
                        if (!existingCartItem.getQuantity().equals(newQuantity)) {
                            existingCartItem.setQuantity(newQuantity);
                        }
                    } else {
                        CartItems newCartItem = new CartItems();
                        newCartItem.setCustomer(customer);
                        newCartItem.setSubProduct(SubProductMapper.convertSubProductCartDTOToEntity(subProductDTO));
                        newCartItem.setQuantity(cartService.getQuantityOfSubProduct(subProductDTO));
                        currentCartItems.add(newCartItem);
                    }
                });
                customer.setShoppingCart(currentCartItems.stream().collect(Collectors.toSet()));
                customerRepository.merge(customer);
            }else{
                Set<CartItems> currentCartItem = customer.getShoppingCart();
                Set<Integer> newSubProductIds = subProductList.stream()
                        .map(SubProductDTO::getId)
                        .collect(Collectors.toSet());
                Set<CartItems> itemsToRemove = new HashSet<>(currentCartItem);
                itemsToRemove.forEach(cartItem -> {
                    if (!newSubProductIds.contains(cartItem.getSubProduct().getId())) {
                        currentCartItem.remove(cartItem);
                    }
                });

                subProductList.forEach(subProductDTO -> {
                    CartItems existingCartItem = currentCartItem.stream()
                            .filter(cartItem -> cartItem.getSubProduct().getId().equals(subProductDTO.getId()))
                            .findFirst()
                            .orElse(null);

                    if (existingCartItem != null) {
                        Integer newQuantity = cartService.getQuantityOfSubProduct(subProductDTO);
                        if (!existingCartItem.getQuantity().equals(newQuantity)) {
                            existingCartItem.setQuantity(newQuantity);
                        }
                    } else {
                        CartItems newCartItem = new CartItems();
                        newCartItem.setCustomer(customer);
                        newCartItem.setSubProduct(SubProductMapper.convertSubProductCartDTOToEntity(subProductDTO));
                        newCartItem.setQuantity(cartService.getQuantityOfSubProduct(subProductDTO));
                        currentCartItem.add(newCartItem);
                    }
                });
                customerRepository.merge(customer);
            }
        }else{
            Set<CartItems> currentCartItem = customer.getShoppingCart();
            Set<Integer> newSubProductIds = subProductList.stream()
                    .map(SubProductDTO::getId)
                    .collect(Collectors.toSet());
            Set<CartItems> itemsToRemove = new HashSet<>(currentCartItem);
            itemsToRemove.forEach(cartItem -> {
                if (!newSubProductIds.contains(cartItem.getSubProduct().getId())) {
                    currentCartItem.remove(cartItem);
                }
            });
            customerRepository.merge(customer);

        }
    }
    public CartService mergeFromDBToSession( Customer customer, CartService service){
        CartRepository cartRepository = new CartRepository();
        List<CartItems> cartItems = cartRepository.findAllByID(customer.getId()).get();
        if (cartItems.size() != 0) {
            cartItems.forEach(cartItem -> {
                service.addCartItem(SubProductMapper.convertEntityToDTO(cartItem.getSubProduct()));
            });
            addToDB(cartItems,customer,service);

        }else{
            addToDB(cartItems,customer,service);
        }
        return service;
    }

}
