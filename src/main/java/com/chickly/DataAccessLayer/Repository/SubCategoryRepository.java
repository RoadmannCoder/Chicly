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
import java.util.Optional;

public class SubCategoryRepository extends GenericCrudManager<SubCategory, Object>{

    public SubCategoryRepository() {

        super( SubCategory.class);
    }

    public List<SubCategory> findSubCategoriesByCategoryID(Category category){
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<SubCategory> q = cb.createQuery(SubCategory.class);
        Root<SubCategory> sub = q.from(SubCategory.class);
        q.select(sub).where(cb.equal(sub.get("category").get("id"),category.getId()));
        return getEntityManager().createQuery(q).getResultList();
    }
    public List<SubCategory> findSubCategoriesByCategoryID(Integer category){
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<SubCategory> q = cb.createQuery(SubCategory.class);
        Root<SubCategory> sub = q.from(SubCategory.class);
        q.select(sub).where(cb.equal(sub.get("category").get("id"),category));
        return getEntityManager().createQuery(q).getResultList();
    }
    public Optional<List<SubCategory>> findAllExceptAccessories() {

        String jpql = "SELECT all FROM " + SubCategory.class.getSimpleName() + " all WHERE all.category.id != 3";
        return Optional.ofNullable(getEntityManager().createQuery(jpql, SubCategory.class).getResultList());
    }





}
