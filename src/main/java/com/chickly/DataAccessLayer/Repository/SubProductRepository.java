package com.chickly.DataAccessLayer.Repository;

import com.chickly.DataAccessLayer.DBContext.EntityManagerUtil;
import com.chickly.DataAccessLayer.DBContext.JpaUtil;
import com.chickly.DataAccessLayer.Entities.Category;
import com.chickly.DataAccessLayer.Entities.Product;
import com.chickly.DataAccessLayer.Entities.SubCategory;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
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
}
