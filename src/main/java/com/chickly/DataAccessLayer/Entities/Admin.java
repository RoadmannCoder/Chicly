package com.chickly.DataAccessLayer.Entities;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "admin")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Embedded
    private Account account;
}
