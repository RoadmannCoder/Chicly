package com.chickly.DataAccessLayer.Repository;

import com.chickly.DataAccessLayer.DBContext.JpaUtil;
import com.chickly.DataAccessLayer.Entities.Admin;
import com.chickly.DataAccessLayer.Entities.Customer;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class AdminRepository extends GenericCrudManager<Admin,Object>{

    public AdminRepository() {
        super(Admin.class);
    }



    public boolean checkUserNameIfFound(String userName){
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Admin> q = cb.createQuery(Admin.class);
        Root<Admin> admin = q.from(Admin.class);
        q.select(admin).where(cb.like(admin.get("account").get("userName"),userName));
        List<Admin> result = getEntityManager().createQuery(q).getResultList();
        if(!result.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * This method is used to check if the user is the entered email and password
     * are correct so that it checks the authentication of the user
     * @param userName
     * @param password
     * @return true if the email and password are correct and false if one of them or both of them are incorrect
     */
    public boolean checkUserNameAndPasswordAreValid(String userName,String password){
        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Admin> q = cb.createQuery(Admin.class);
        Root<Admin> admin = q.from(Admin.class);
        q.select(admin)
                .where(cb.and(
                        cb.equal(admin.get("account").get("userName"), userName),
                        cb.equal(admin.get("account").get("password"), password)
                ));
        try {
           getEntityManager().createQuery(q).getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
