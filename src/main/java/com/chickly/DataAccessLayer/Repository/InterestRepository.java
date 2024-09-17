package com.chickly.DataAccessLayer.Repository;

import com.chickly.DataAccessLayer.Entities.Interest;

public class InterestRepository  extends GenericCrudManager<Interest,Object>{

    public InterestRepository() {
        super(Interest.class);
    }
}
