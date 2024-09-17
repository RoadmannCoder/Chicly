package com.chickly.DataAccessLayer.Repository;

import com.chickly.DTO.SubProductFilterDTO;
import com.chickly.DataAccessLayer.DBContext.EntityManagerUtil;
import com.chickly.DataAccessLayer.DBContext.JpaUtil;
import com.chickly.DataAccessLayer.Entities.Product;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Util.PredicateBuilder;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
    public List<SubProduct> findSubProductsByFilters(SubProductFilterDTO filterDTO) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubProduct> cq = cb.createQuery(SubProduct.class);
        Root<SubProduct> subProductRoot = cq.from(SubProduct.class);

        List<Predicate> predicates = new PredicateBuilder().buildSubProductPredicates(cb, subProductRoot, filterDTO);

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<SubProduct> query = entityManager.createQuery(cq);
        query.setFirstResult((filterDTO.getPageNumber() - 1) * filterDTO.getPageSize());
        query.setMaxResults(filterDTO.getPageSize());

        return query.getResultList();
    }

    public long countSubProductsByFilters(SubProductFilterDTO filterDTO) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<SubProduct> subProductRoot = cq.from(SubProduct.class);
        List<Predicate> predicates = new PredicateBuilder().buildSubProductPredicates(cb, subProductRoot, filterDTO);

        cq.select(cb.count(subProductRoot));
        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getSingleResult();
    }


    public void updateSubProduct(String subProductId, Integer stock, BigDecimal price,String imageUrl) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<SubProduct> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(SubProduct.class);
        Root<SubProduct> root = criteriaUpdate.from(SubProduct.class);
        if (stock != null) {
            criteriaUpdate.set("stock", stock);
        }
        if (price != null) {
            criteriaUpdate.set("price", price);
        }
        if (imageUrl != null && !imageUrl.isEmpty()) {
            criteriaUpdate.set("imageURL", imageUrl);
        }
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), subProductId));
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    public void deleteSubproductById(String subProductId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<SubProduct> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(SubProduct.class);
        Root<SubProduct> root = criteriaUpdate.from(SubProduct.class);
        criteriaUpdate.set("isDeleted", true);
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), subProductId));
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    public List<SubProduct> findBySubCategoryName(String subcategoryId) {
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<SubProduct> q = cb.createQuery(SubProduct.class);
        Root<SubProduct> sub = q.from(SubProduct.class);
        q.select(sub).where(
                cb.and(
                        cb.equal(sub.get("product").get("subCategory").get("name"), subcategoryId),
                        cb.equal(sub.get("isDeleted"), false)
                )
        );
        return entityManager.createQuery(q).getResultList();
    }

    public SubProduct findSubCategoryById(String searchId) {
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<SubProduct> q = cb.createQuery(SubProduct.class);
        Root<SubProduct> sub = q.from(SubProduct.class);
        q.select(sub).where(
                cb.and(
                        cb.equal(sub.get("id"), searchId),
                        cb.equal(sub.get("isDeleted"), false)
                )
        );
        return entityManager.createQuery(q).getSingleResult();
    }

    public List<SubProduct> findAllSubCategories() {
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<SubProduct> q = cb.createQuery(SubProduct.class);
        Root<SubProduct> sub = q.from(SubProduct.class);
        q.select(sub).where(cb.equal(sub.get("isDeleted"), false));
        return entityManager.createQuery(q).getResultList();
    }

    public Long countAllSubproducts() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<SubProduct> root = criteriaQuery.from(SubProduct.class);
        criteriaQuery.select(criteriaBuilder.count(root));
        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
    public void UpdateStock(SubProduct subProduct){
        try {
            LOGGER.info("Updating......");
            getEntityManager().merge(subProduct);
        } catch (RuntimeException ex) {
            LOGGER.error("Failed to Update SubProduct Stock: ", ex);
            throw ex;
        }
    }
}
