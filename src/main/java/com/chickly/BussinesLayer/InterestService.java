package com.chickly.BussinesLayer;

import com.chickly.DTO.InterestDTO;
import com.chickly.DataAccessLayer.Entities.Interest;
import com.chickly.DataAccessLayer.Repository.InterestRepository;
import com.chickly.Mappers.InterestMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InterestService {
    public List<InterestDTO> getAllInterests (){
        List<Interest> interests = new InterestRepository().findAll().get();
        return interests.stream().map(InterestMapper::convertEntityToDTO).collect(Collectors.toList());
    }
}
