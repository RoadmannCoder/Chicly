package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Embeddable
public class Account {
    private String userName;
    private String password;
}
