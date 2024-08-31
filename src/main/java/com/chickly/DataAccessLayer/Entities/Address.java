package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.*;
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
    private String street;
    @NonNull
    private String city;

    @NonNull
    private String zip;
    private String description;
}