package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.*;


import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "customer")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Size(min = 2,max = 50 , message = "must be between 2 to 50 characters")
    private String firstName;

    @NonNull
    @Size(min = 2,max = 50 , message = "must be between 2 to 50 characters")
    private String lastName;

    @NonNull
    private BigDecimal creditLimit;

    @NonNull
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Email(message = "Invalid email address")
    @NonNull
    private String email;

    @NonNull
    private String phoneNumber;

    @NonNull
    @Embedded
    private Address address;

    @NonNull
    @Embedded
    private Account account;
}
