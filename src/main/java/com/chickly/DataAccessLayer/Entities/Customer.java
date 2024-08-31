package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.*;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NonNull
    @Size(min = 2,max = 50 , message = "must be between 2 to 50 characters")
    private String firstName;

    @NotNull
    @NonNull
    @Size(min = 2,max = 50 , message = "must be between 2 to 50 characters")
    private String lastName;

    @NotNull
    @NonNull
    private BigDecimal creditLimit;

    @NotNull
    @NonNull
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Email(message = "Invalid email address")
    @NotNull
    @NonNull
    @Column(unique = true)
    private String email;

    @NotNull
    @NonNull
    @Column(unique = true)
    private String phoneNumber;

    @NotNull
    @NonNull
    private String job;

    @NotNull
    @NonNull
    @Embedded
    private Address address;

    @NotNull
    @NonNull
    @Embedded
    private Account account;

    @OneToMany(mappedBy ="customer")
    private Set<Order> orders = new HashSet<>();
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItems> shoppingCart = new HashSet<>();

    public void addOrder(Order order){
        order.setCustomer(this);
        orders.add(order);
    }


}
