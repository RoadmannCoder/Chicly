package com.chickly.BussinesLayer;

import com.chickly.DataAccessLayer.Repository.AdminRepository;
import jakarta.servlet.http.HttpServletRequest;

public class AdminService {

    public boolean isAdminAuthenticated(HttpServletRequest req){
        AdminRepository adminRepository = new AdminRepository();
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        return adminRepository.checkUserNameAndPasswordAreValid(userName,password);
    }
}
