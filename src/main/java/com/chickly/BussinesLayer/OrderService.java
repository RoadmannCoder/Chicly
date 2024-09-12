package com.chickly.BussinesLayer;

import com.chickly.DataAccessLayer.Repository.OrderRepository;

public class OrderService {
    private OrderRepository orderRepository;
    public OrderService(){
        this.orderRepository= new OrderRepository();
    }
}
