package com.chickly.DataAccessLayer.Repository;

import com.chickly.DataAccessLayer.DBContext.JpaUtil;
import com.chickly.DataAccessLayer.Entities.Category;
import com.chickly.DataAccessLayer.Entities.SubCategory;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class SubCategoryRepository extends GenericCrudManager<SubCategory, Object>{

    public SubCategoryRepository(EntityManager entityManager) {

        super(entityManager, SubCategory.class);
    }

    public List<SubCategory> findSubCategoriesByCategoryID(Category category){
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<SubCategory> q = cb.createQuery(SubCategory.class);
        Root<SubCategory> sub = q.from(SubCategory.class);
        q.select(sub).where(cb.equal(sub.get("category").get("id"),category.getId()));
        return entityManager.createQuery(q).getResultList();
    }


}
