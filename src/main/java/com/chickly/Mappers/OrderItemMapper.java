package com.chickly.Mappers;

import com.chickly.DTO.OrderItemDTO;
import com.chickly.DTO.OrderViewDTO;
import com.chickly.DataAccessLayer.Entities.Order;
import com.chickly.DataAccessLayer.Entities.OrderItem;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderItemMapper {
    public static List<OrderItemDTO> convertEntityListToDTOList(List<OrderItem> orderItemList){
        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
        for(OrderItem orderItem:orderItemList){
            orderItemDTOList.add(convertEntityToDTO(orderItem));
        }
        return orderItemDTOList;
    }
    public static OrderItemDTO convertEntityToDTO(OrderItem order){

        return new OrderItemDTO(order.getQuantity().toString(),order.getPrice().toString(),order.getSubProduct().getProduct().getName());
    }

}
