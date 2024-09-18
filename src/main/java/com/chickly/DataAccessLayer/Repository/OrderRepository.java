package com.chickly.DataAccessLayer.Repository;

import com.chickly.DataAccessLayer.DBContext.JpaUtil;
import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.DataAccessLayer.Entities.Order;
import com.chickly.DataAccessLayer.Entities.SubCategory;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.Enums.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Or;

import java.util.Date;
import java.util.List;

public class OrderRepository extends GenericCrudManager<Order ,Object>{
    public OrderRepository() {
        super( Order.class);
    }

    public void merge(Order order){
        getEntityManager().merge(order);
    }


    public List<Order> findOrdersByCustomerId(String id) {
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Order> q = cb.createQuery(Order.class);
        Root<Order> sub = q.from(Order.class);
        q.select(sub).where(cb.equal(sub.get("customer").get("id"), id));

        return getEntityManager().createQuery(q).getResultList();
    }

    public void updateOrderStatus(int id, Status status) {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Order> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Order.class);
        Root<Order> root = criteriaUpdate.from(Order.class);
        criteriaUpdate.set("status", status);
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), id));
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }
}
