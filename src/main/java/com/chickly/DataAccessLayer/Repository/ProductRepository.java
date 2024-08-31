package com.chickly.DataAccessLayer.Repository;

import com.chickly.DataAccessLayer.DBContext.JpaUtil;
import com.chickly.DataAccessLayer.Entities.Product;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ProductRepository extends GenericCrudManager<Product,Object> {
    public ProductRepository(EntityManager entityManager) {
        super(entityManager, Product.class);
    }
    public List<SubProduct> readSubProducts(Product product){
        CriteriaBuilder query = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<SubProduct> subProductCriteriaQuery = query.createQuery(SubProduct.class);
        Root<SubProduct> subProductRoot = subProductCriteriaQuery.from(SubProduct.class);
        subProductCriteriaQuery.select(subProductRoot).where(query.equal(subProductRoot.get("product").get("id"),product.getId()));
        return entityManager.createQuery(subProductCriteriaQuery).getResultList();
    }
}