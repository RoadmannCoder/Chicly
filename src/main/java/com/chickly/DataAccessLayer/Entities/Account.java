package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@Embeddable
@RequiredArgsConstructor
@NoArgsConstructor
public class Account {
    @NonNull
    @NotEmpty
    @Column(unique = true)
    private String userName;
    @NonNull
    @NotEmpty
    private String password;
}
