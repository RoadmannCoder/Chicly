package com.chickly.DataAccessLayer.Util;


import com.chickly.DTO.SubProductFilterDTO;
import com.chickly.DataAccessLayer.Entities.SubProduct;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PredicateBuilder {

    public  List<Predicate> buildSubProductPredicates(
            CriteriaBuilder cb, Root<SubProduct> subProductRoot, SubProductFilterDTO filter) {

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getCategoryName()!= null && !filter.getCategoryName().isEmpty()) {
            predicates.add(cb.equal(subProductRoot.get("product").get("subCategory").get("category").get("name"), filter.getCategoryName()));
        }

        if (filter.getProductName() != null && !filter.getProductName().isEmpty()) {
            predicates.add(cb.like(cb.lower(subProductRoot.get("product").get("name")), "%" + filter.getProductName().toLowerCase() + "%"));
        }

        if (filter.getColor() != null) {
            predicates.add(cb.equal(subProductRoot.get("color"), filter.getColor()));
        }
        if (filter.getGender() != null) {
            predicates.add(cb.equal(subProductRoot.get("product").get("gender"), filter.getGender()));
        }
        if (filter.getSize() != null) {
            predicates.add(cb.equal(subProductRoot.get("size"), filter.getSize()));
        }

        if (filter.getMinPrice() != null) {
            predicates.add(cb.greaterThanOrEqualTo(subProductRoot.get("price"), filter.getMinPrice()));
        }

        if (filter.getMaxPrice() != null) {
            predicates.add(cb.lessThanOrEqualTo(subProductRoot.get("price"), filter.getMaxPrice()));
        }

        if (filter.getIsNewArrival() != null) {
            predicates.add(cb.equal(subProductRoot.get("isNewArrival"), filter.getIsNewArrival()));
        }
        if (filter.getIsDeleted() != null) {
            predicates.add(cb.equal(subProductRoot.get("isDeleted"), filter.getIsDeleted()));
        }

        if (filter.getInStock() != null && filter.getInStock() == Boolean.TRUE) {
            predicates.add(cb.notEqual(subProductRoot.get("stock"), 0));
        }

        if (filter.getInStock() != null && filter.getInStock() == Boolean.FALSE) {
            predicates.add(cb.equal(subProductRoot.get("stock"), 0));
        }
        return predicates;
    }
}
