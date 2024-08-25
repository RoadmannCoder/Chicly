package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "customer")
@RequiredArgsConstructor
@NoArgsConstructor
public class Customer {
    @Getter
    @Setter
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
    @NonNull
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email address")
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
