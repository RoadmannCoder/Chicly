package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.*;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "category")
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NonNull
    @Column(unique = true)
    private String name;


}
