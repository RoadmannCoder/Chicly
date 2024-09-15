package com.chickly.BussinesLayer;

import com.chickly.DTO.SubProductDTO;
import com.chickly.DataAccessLayer.Entities.SubProduct;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        return cart.get(subProductDTO);
    }
    public int getTotalCartItems(){
        return this.cart.size();
    }

}
