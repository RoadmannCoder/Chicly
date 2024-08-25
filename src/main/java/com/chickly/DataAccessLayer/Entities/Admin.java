package com.chickly.DataAccessLayer.Entities;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "admin")
@Getter
@Setter
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Embedded
    private Account account;
}
