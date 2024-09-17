package com.chickly.BussinesLayer;

import com.chickly.DTO.SubProductDTO;
import com.chickly.DataAccessLayer.Entities.Order;

public class OrderProcessError {
    private Order order;
    private SubProductDTO subProductDTO;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public SubProductDTO getSubProductDTO() {
        return subProductDTO;
    }

    public void setSubProductDTO(SubProductDTO subProductDTO) {
        this.subProductDTO = subProductDTO;
    }
}
