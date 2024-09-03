package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Embeddable
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Address {
    @NonNull
    @NotEmpty
    private String street;
    @NonNull
    @NotEmpty
    private String city;

    @NonNull
    @NotEmpty
    private String zip;
    private String description;
}