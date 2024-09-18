package com.chickly.DataAccessLayer.Repository;


import com.chickly.DTO.OrderItemDTO;
import com.chickly.DataAccessLayer.DBContext.JpaUtil;
import com.chickly.DataAccessLayer.Entities.Order;
import com.chickly.DataAccessLayer.Entities.OrderItem;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class OrderItemRepository extends GenericCrudManager<OrderItem,Object> {
    public OrderItemRepository() {
        super(OrderItem.class);
    }

    public List<OrderItem> getOrderItemsByOrderId(String orderId) {
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<OrderItem> q = cb.createQuery(OrderItem.class);
        Root<OrderItem> sub = q.from(OrderItem.class);
        q.select(sub).where(cb.equal(sub.get("order").get("id"), orderId));
        return getEntityManager().createQuery(q).getResultList();
    }
}