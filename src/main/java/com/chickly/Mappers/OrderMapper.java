package com.chickly.Mappers;

import com.chickly.DTO.OrderViewDTO;
import com.chickly.DataAccessLayer.Entities.Order;
import com.chickly.Enums.Status;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderMapper {
    public static List<OrderViewDTO> convertEntityListToDTOList(List<Order> orderList){
        List<OrderViewDTO> orderViewDTOList = new ArrayList<>();
        for(Order order:orderList){
            orderViewDTOList.add(convertEntityToDTO(order));
        }
        return orderViewDTOList;
    }
    public static OrderViewDTO convertEntityToDTO(Order order){
        Calendar date;
        String createdAt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
                .withZone(ZoneId.systemDefault())
                .format(order.getCreatedAt().toInstant());
        String destination = order.getCustomer().getAddress().getCity()
                +"-"+order.getCustomer().getAddress().getStreet()
                +"-"+order.getCustomer().getAddress().getDescription();
        return new OrderViewDTO(order.getId(),order.getStatus(),createdAt,destination);
    }
}
