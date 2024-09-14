package com.chickly.DataAccessLayer.Repository;

import com.chickly.DTO.SubProductFilterDTO;
import com.chickly.DataAccessLayer.DBContext.EntityManagerUtil;
import com.chickly.DataAccessLayer.DBContext.JpaUtil;
import com.chickly.DataAccessLayer.Entities.Product;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import com.chickly.DataAccessLayer.Util.PredicateBuilder;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


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


}
