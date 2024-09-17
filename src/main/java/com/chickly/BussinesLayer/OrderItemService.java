package com.chickly.BussinesLayer;

import com.chickly.DTO.OrderItemDTO;
import com.chickly.DataAccessLayer.Repository.OrderItemRepository;
import com.chickly.Mappers.OrderItemMapper;
import com.chickly.Mappers.OrderMapper;

import java.util.List;

public class OrderItemService {
    public List<OrderItemDTO> getAllOrderItemsByOrderId(String orderId) {
        OrderItemRepository orderItemRepository = new OrderItemRepository();
        return OrderItemMapper.convertEntityListToDTOList(orderItemRepository.getOrderItemsByOrderId(orderId));
    }
}
