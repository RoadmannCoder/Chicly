package com.chickly.DataAccessLayer.Entities;

import com.chickly.DataAccessLayer.EntitiesEmbeddedId.OrderProductId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class OrderItem {

    @EmbeddedId
    private OrderProductId id;
    @NonNull
    private Integer quantity = 1;
    private BigDecimal price;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;


}
