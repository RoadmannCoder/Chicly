package com.chickly.DataAccessLayer.Repository;

import com.chickly.DataAccessLayer.DBContext.EntityManagerUtil;
import com.chickly.DataAccessLayer.DBContext.JpaUtil;
import com.chickly.DataAccessLayer.Entities.Category;
import com.chickly.DataAccessLayer.Entities.Product;
import com.chickly.DataAccessLayer.Entities.SubCategory;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.Enums.Color;
import com.chickly.Enums.Size;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SubProductRepository extends GenericCrudManager<SubProduct,Object> {
    public SubProductRepository() {
        super( SubProduct.class);
    }
    public List<SubProduct> findSubCProductsByProductID(Product product){
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<SubProduct> q = cb.createQuery(SubProduct.class);
        Root<SubProduct> sub = q.from(SubProduct.class);
        q.select(sub).where(cb.equal(sub.get("product").get("id"),product.getId()));
        return entityManager.createQuery(q).getResultList();
    }
    public List<SubProduct> findSubProductsByCategory(int categoryId) {

        String jpql = "SELECT sp FROM SubProduct sp WHERE sp.product.subCategory.category.id = :categoryId";
        TypedQuery<SubProduct> query = EntityManagerUtil.getEntityManager().createQuery(jpql, SubProduct.class);
        query.setParameter("categoryId", categoryId);
        return query.getResultList();
    }
    public List<SubProduct> findSubProductsByFilters(
            Integer categoryId, String productName, Color color, Size size, BigDecimal minPrice, BigDecimal maxPrice, int pageNumber, int pageSize) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubProduct> cq = cb.createQuery(SubProduct.class);
        Root<SubProduct> subProductRoot = cq.from(SubProduct.class);

        List<Predicate> predicates = new ArrayList<>();

        if (categoryId != null) {
            predicates.add(cb.equal(subProductRoot.get("product").get("subCategory").get("category").get("id"), categoryId));
        }

        if (productName != null && !productName.isEmpty()) {
            predicates.add(cb.like(cb.lower(subProductRoot.get("product").get("name")), "%" + productName.toLowerCase() + "%"));
        }

        if (color != null) {
            predicates.add(cb.equal(subProductRoot.get("color"), color));
        }
        if (size != null) {
            predicates.add(cb.equal(subProductRoot.get("size"), size));
        }

        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(subProductRoot.get("price"), minPrice));
        }

        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(subProductRoot.get("price"), maxPrice));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        //cq.orderBy(cb.asc(subProductRoot.get("price")));

        TypedQuery<SubProduct> query = entityManager.createQuery(cq);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public long countSubProductsByFilters(Integer categoryId, String productName,Color color,Size size, BigDecimal minPrice, BigDecimal maxPrice) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<SubProduct> subProductRoot = cq.from(SubProduct.class);

        cq.select(cb.count(subProductRoot));

        List<Predicate> predicates = new ArrayList<>();

        // Apply the same filter conditions as in the previous method
        if (categoryId != null) {
            predicates.add(cb.equal(subProductRoot.get("category").get("id"), categoryId));
        }
        if (productName != null && !productName.isEmpty()) {
            predicates.add(cb.like(cb.lower(subProductRoot.get("product").get("name")), "%" + productName.toLowerCase() + "%"));
        }
        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(subProductRoot.get("price"), minPrice));
        }
        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(subProductRoot.get("price"), maxPrice));
        }

        if (color != null) {
            predicates.add(cb.equal(subProductRoot.get("color"), color));
        }
        if (size != null) {
            predicates.add(cb.equal(subProductRoot.get("size"), size));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getSingleResult();
    }
}
