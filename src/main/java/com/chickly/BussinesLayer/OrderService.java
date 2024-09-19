package com.chickly.BussinesLayer;

import com.chickly.DTO.OrderViewDTO;
import com.chickly.DTO.SubProductDTO;
import com.chickly.DataAccessLayer.Entities.*;
import com.chickly.DataAccessLayer.Repository.CartRepository;
import com.chickly.DataAccessLayer.Repository.CustomerRepository;
import com.chickly.DataAccessLayer.Repository.OrderRepository;
import com.chickly.DataAccessLayer.Repository.SubProductRepository;
import com.chickly.Enums.Status;
import com.chickly.Mappers.OrderMapper;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class OrderService {
    OrderRepository orderRepository = new OrderRepository();
    SubProductRepository subProductRepository = new SubProductRepository();
    CustomerRepository customerRepository = new CustomerRepository();
    CartRepository cartRepository = new CartRepository();
    public OrderProcessError createOrder(CartService cartService, Customer customer) {
        BigDecimal subTotal = customer.getCreditLimit().subtract(cartService.getTotalPrice());
        List<SubProductDTO> subProductList = cartService.getItems().keySet().stream().collect(Collectors.toList());
        AtomicBoolean redirect = new AtomicBoolean(false);
        Order order = new Order();
        OrderProcessError orderProcessError = new OrderProcessError();
        subProductList.forEach(subProductDTO -> {
                    SubProduct subProduct = subProductRepository.findBy("id", subProductDTO.getId());
                    if (subProductDTO.getQuantity() > subProduct.getStock()) {
                        orderProcessError.setSubProductDTO(subProductDTO);
                        redirect.set(true);
                        return;
                    }
        });
        if(redirect.get()) {
            orderProcessError.setOrder(null);
            return orderProcessError;
        }
        if (cartService != null && subTotal.compareTo(BigDecimal.ZERO) > 0) {
            order.setCustomer(customer);
            order.setCreatedAt(new Date());
            order.setStatus(Status.PENDING);
            orderRepository.create(order);
            Set<OrderItem> orderItems = new HashSet<>();
            subProductList.forEach(subProductDTO -> {
                SubProduct subProduct = subProductRepository.findBy("id", subProductDTO.getId());
                if (subProductDTO.getQuantity() > subProduct.getStock()) {
                    orderProcessError.setSubProductDTO(subProductDTO);
                    redirect.set(true);
                    return;
                }
                OrderItem orderItem = new OrderItem();
                orderItem.setSubProduct(subProduct);
                orderItem.setOrder(order);
                orderItem.setQuantity(cartService.getQuantityOfSubProduct(subProductDTO));
                orderItem.setPrice(subProductDTO.getPrice());
                orderItems.add(orderItem);
                subProduct.setStock(subProductDTO.getStock() - cartService.getQuantityOfSubProduct(subProductDTO));
                subProductRepository.update(subProduct);
            });
            if(redirect.get()) {
                orderProcessError.setOrder(null);
                return orderProcessError;
            }
            order.getOrderItems().clear();
            order.getOrderItems().addAll(orderItems);
            customer.setCreditLimit(customer.getCreditLimit().subtract(cartService.getTotalPrice()));
            List<CartItems> testCart = cartRepository.findAllByID(customer.getId()).get();
            if(!testCart.isEmpty()) {
                Iterator<CartItems> iterator = customer.getShoppingCart().iterator();
                while (iterator.hasNext()) {
                    CartItems cartItem = iterator.next();
                    iterator.remove(); // Safely remove the item from the cart
                }
            }
            customerRepository.merge(customer);
            orderProcessError.setOrder(order);
            orderProcessError.setSubProductDTO(null);
        }
        return orderProcessError;
    }
    public List<OrderViewDTO> getAllOrdersOfSpecificCustomer(String id) {
        OrderRepository orderRepository1 = new OrderRepository();
        return OrderMapper.convertEntityListToDTOList(orderRepository1.findOrdersByCustomerId(id));
    }
    public void updateOrderStatus(int id, Status status) {
        OrderRepository orderRepository1 = new OrderRepository();
        orderRepository1.updateOrderStatus(id, status);
    }

    public Order getOrderById(String orderId) {
        OrderRepository orderRepository1 = new OrderRepository();
        return orderRepository1.findBy("id",orderId);
    }
}




