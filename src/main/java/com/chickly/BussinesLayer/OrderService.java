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
    private BigDecimal decreaseCustomerCreditLimit(Customer customer, CartService cartService){
        return customer.getCreditLimit().subtract(cartService.getTotalPrice());
    }
    private List<SubProductDTO> convertCartServiceMapToList(CartService cartService){
        return cartService.getItems().keySet().stream().collect(Collectors.toList());
    }
    private boolean hasStockErrors(List<SubProductDTO> subProductList, OrderProcessError orderProcessError) {
        for (SubProductDTO subProductDTO : subProductList) {
            SubProduct subProduct = subProductRepository.findBy("id", subProductDTO.getId());
            if (subProductDTO.getQuantity() > subProduct.getStock()) {
                orderProcessError.setSubProductDTO(subProductDTO);
                return true;
            }
        }
        return false;
    }
    private boolean isOrderValid(CartService cartService, BigDecimal remainingCreditLimit) {
        return cartService != null && remainingCreditLimit.compareTo(BigDecimal.ZERO) > 0;
    }
    private Order createPendingOrder(Customer customer) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setCreatedAt(new Date());
        order.setStatus(Status.PENDING);
        orderRepository.create(order);
        return order;
    }

    private Set<OrderItem> createOrderItems(List<SubProductDTO> subProductList, CartService cartService, Order order, OrderProcessError orderProcessError) {
        Set<OrderItem> orderItems = new HashSet<>();

        for (SubProductDTO subProductDTO : subProductList) {
            SubProduct subProduct = subProductRepository.findBy("id", subProductDTO.getId());

            if (subProductDTO.getQuantity() > subProduct.getStock()) {
                orderProcessError.setSubProductDTO(subProductDTO);
                return orderItems;
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setSubProduct(subProduct);
            orderItem.setOrder(order);
            orderItem.setQuantity(cartService.getQuantityOfSubProduct(subProductDTO));
            orderItem.setPrice(subProductDTO.getPrice());
            orderItems.add(orderItem);

            updateSubProductStock(subProduct, subProductDTO, cartService);
        }

        return orderItems;
    }
    private void updateSubProductStock(SubProduct subProduct, SubProductDTO subProductDTO, CartService cartService) {
        int newStock = subProduct.getStock() - cartService.getQuantityOfSubProduct(subProductDTO);
        subProduct.setStock(newStock);
        subProductRepository.update(subProduct);
    }
    private void finalizeOrder(Order order, Set<OrderItem> orderItems, Customer customer, CartService cartService) {
        order.getOrderItems().clear();
        order.getOrderItems().addAll(orderItems);

        BigDecimal totalPrice = cartService.getTotalPrice();
        customer.setCreditLimit(customer.getCreditLimit().subtract(totalPrice));

        clearCustomerShoppingCart(customer);
        customerRepository.merge(customer);
    }

    private void clearCustomerShoppingCart(Customer customer) {
        List<CartItems> cartItems = cartRepository.findAllByID(customer.getId()).orElse(Collections.emptyList());
        if (!cartItems.isEmpty()) {
            customer.getShoppingCart().clear();
        }
    }
    public OrderProcessError createOrder(CartService cartService, Customer customer) {
        BigDecimal remainingCustomerCreditLimit = decreaseCustomerCreditLimit(customer,cartService);
        List<SubProductDTO> subProductList = convertCartServiceMapToList(cartService);
        OrderProcessError orderProcessError = new OrderProcessError();
        if (hasStockErrors(subProductList, orderProcessError)) {
            return orderProcessError;
        }

        if (isOrderValid(cartService, remainingCustomerCreditLimit)) {
            Order order = createPendingOrder(customer);
            Set<OrderItem> orderItems = createOrderItems(subProductList, cartService, order, orderProcessError);

            if (orderProcessError.getSubProductDTO() != null) {
                return orderProcessError;
            }

            finalizeOrder(order, orderItems, customer, cartService);
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




