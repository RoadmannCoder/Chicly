package com.chickly.BussinesLayer;

import com.chickly.DTO.CartItemDTO;
import com.chickly.DTO.SubProductDTO;
import com.chickly.DataAccessLayer.Entities.CartItems;
import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Repository.CartRepository;
import com.chickly.DataAccessLayer.Repository.CustomerRepository;
import com.chickly.Mappers.SubProductMapper;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CartService implements Serializable {

    @JsonIgnore
    private Map<SubProductDTO, Integer> cart = new HashMap<>();


    public CartService(){


    }
    @JsonCreator
    public CartService(@JsonProperty("cartItems") List<CartItemDTO> cartItems) {
        this.cart = cartItems.stream()
                .collect(Collectors.toMap(CartItemDTO::getSubProduct, CartItemDTO::getQuantity));
    }
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

    @JsonIgnore
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
    public int getTotalCartItems() {
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
    @JsonProperty("cartItems")
    public List<CartItemDTO> getCartItemsForJson() {
        return cart.entrySet().stream()
                .map(entry -> new CartItemDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }


    //Cart Operations

    public CartService mergeFromDBToSession( Customer customer, CartService service){
        CartRepository cartRepository = new CartRepository();
        List<CartItems> cartItems = getCartItemFromDB(customer,cartRepository);
        if (!cartItems.isEmpty())
            mergeCartItemToSession(cartItems,service);

        addToDB(cartItems,customer,service);
        return service;
    }

    private List<CartItems> getCartItemFromDB(Customer customer, CartRepository cartRepository){
        return cartRepository.findAllByID(customer.getId()).orElse(Collections.EMPTY_LIST);
    }
    private void mergeCartItemToSession(List<CartItems> cartItems, CartService service){
        cartItems.forEach(cartItem -> {
            service.addCartItem(SubProductMapper.convertEntityToDTO(cartItem.getSubProduct())
                                ,cartItem.getQuantity()
            );
        });
    }

    private List<SubProductDTO> convertCartServiceMapToList(CartService cartService){
        return cartService.getItems().keySet().stream().collect(Collectors.toList());
    }
    private void removeOldCartItemsFromDB(Customer customer,List<SubProductDTO> subProductList, CustomerRepository customerRepository){
        Set<CartItems> currentCartItem = customer.getShoppingCart();
        Set<Integer> newSubProductIds = getSubProductIds(subProductList);
        removeUnwantedCartItems(currentCartItem, newSubProductIds);
        customerRepository.merge(customer);
    }
    private Set<Integer> getSubProductIds(List<SubProductDTO> subProductList) {
        return subProductList.stream()
                .map(SubProductDTO::getId)
                .collect(Collectors.toSet());
    }
    private void removeUnwantedCartItems(Set<CartItems> currentCartItem, Set<Integer> newSubProductIds) {
        currentCartItem.removeIf(cartItem -> !newSubProductIds.contains(cartItem.getSubProduct().getId()));
    }
    private void addNewCartItemsToDB(List<SubProductDTO> subProductList, Customer customer,CartService cartService,List<CartItems> currentCartItems){
        subProductList.forEach(subProductDTO -> {
            CartItems newCartItem = new CartItems();
            newCartItem.setCustomer(customer);
            newCartItem.setSubProduct(SubProductMapper.convertSubProductCartDTOToEntity(subProductDTO));
            newCartItem.setQuantity(cartService.getQuantityOfSubProduct(subProductDTO));
            currentCartItems.add(newCartItem);
        });
        customer.getShoppingCart().clear();
        customer.getShoppingCart().addAll(currentCartItems);
    }
    private  void updateExistingCartItems (List<SubProductDTO> subProductList, Customer customer,CartService cartService,List<CartItems> currentCartItems){
        Set<CartItems> currentCartItem = customer.getShoppingCart();
        Set<Integer> newSubProductIds = getSubProductIds(subProductList);
        removeUnwantedCartItems(currentCartItem, newSubProductIds);

        subProductList.forEach(subProductDTO -> {
            CartItems existingCartItem = findCartItemBySubProductId(currentCartItem, subProductDTO.getId());

            if (existingCartItem != null) {
                updateCartItemQuantity(existingCartItem, cartService, subProductDTO);
            } else {
                addNewCartItem(currentCartItem, customer, cartService, subProductDTO);
            }
        });
    }
    private CartItems findCartItemBySubProductId(Set<CartItems> cartItems, Integer subProductId) {
        return cartItems.stream()
                .filter(cartItem -> cartItem.getSubProduct().getId().equals(subProductId))
                .findFirst()
                .orElse(null);
    }

    private void updateCartItemQuantity(CartItems cartItem, CartService cartService, SubProductDTO subProductDTO) {
        Integer newQuantity = cartService.getQuantityOfSubProduct(subProductDTO);
        if (!cartItem.getQuantity().equals(newQuantity)) {
            cartItem.setQuantity(newQuantity);
        }
    }

    private void addNewCartItem(Set<CartItems> currentCartItem, Customer customer, CartService cartService, SubProductDTO subProductDTO) {
        CartItems newCartItem = new CartItems();
        newCartItem.setCustomer(customer);
        newCartItem.setSubProduct(SubProductMapper.convertSubProductCartDTOToEntity(subProductDTO));
        newCartItem.setQuantity(cartService.getQuantityOfSubProduct(subProductDTO));
        currentCartItem.add(newCartItem);
    }
    public void addToDB(List<CartItems> cartItems, Customer customer, CartService cartService){
        CustomerRepository customerRepository = new CustomerRepository();
        CartRepository cartRepository = new CartRepository();
        List<SubProductDTO> subProductList = convertCartServiceMapToList(cartService);
        if(subProductList.isEmpty()) {
            removeOldCartItemsFromDB(customer, subProductList, customerRepository);
            return;
        }
        List<CartItems> currentCartItems = getCartItemFromDB(customer,cartRepository);
        if (currentCartItems.isEmpty()) {
            addNewCartItemsToDB(subProductList, customer, cartService, currentCartItems);
        } else {
            updateExistingCartItems(subProductList, customer, cartService, currentCartItems);
        }
        customerRepository.merge(customer);


    }

}
