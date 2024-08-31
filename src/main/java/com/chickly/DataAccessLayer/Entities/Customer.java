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
    private String job;
    @NonNull
    @Embedded
    private Address address;

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
