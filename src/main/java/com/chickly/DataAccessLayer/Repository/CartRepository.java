package com.chickly.DataAccessLayer.Repository;

import com.chickly.DataAccessLayer.Entities.CartItems;
import com.mysql.cj.exceptions.CJPacketTooBigException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class CartRepository extends GenericCrudManager<CartItems,Object> {

    public CartRepository() {
        super(CartItems.class);
    }

    public Optional<List<CartItems>> findAllByID(int id) {

        String jpql = "FROM CartItems record WHERE record.customer.id = :id";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("id", id);
        return Optional.ofNullable(query.getResultList());
    }
    public void delteAllByID(int id) {

        String jpql = "DELETE FROM CartItems record WHERE record.customer.id = :id";
        Query query = getEntityManager().createQuery(jpql);
        query.setParameter("id", id);
        query.executeUpdate();
    }



}
