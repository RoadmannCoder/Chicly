package com.chickly.DataAccessLayer.Entities;

import com.chickly.DataAccessLayer.EntitiesEmbeddedId.OrderProductId;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@IdClass(OrderProductId.class)
@NoArgsConstructor
public class OrderItem {

    private Integer quantity = 1;

    private BigDecimal price;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private SubProduct subProduct;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;


}
