package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.*;


@Getter
@Setter
@Embeddable
@RequiredArgsConstructor
@NoArgsConstructor
public class Account {
    @NonNull
    @Column(unique = true)
    private String userName;
    @NonNull
    private String password;
}
