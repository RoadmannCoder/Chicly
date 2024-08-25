package com.chickly.DataAccessLayer.Entities;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "admin")
@Getter
@Setter
@ToString
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Embedded
    private Account account;
}
