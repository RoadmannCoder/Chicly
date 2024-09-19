package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty
    @NonNull
    private String firstName;

    @NotEmpty
    @NonNull
    private String lastName;



    @NotNull
    @NonNull
    private BigDecimal creditLimit;

    @NotNull
    @NonNull
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Email(message = "Invalid email address")
    @NotEmpty
    @NonNull
    @Column(unique = true)
    private String email;

    @NotEmpty
    @NonNull
    @Column(unique = true)
    private String phoneNumber;

    @NotEmpty
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
    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<CartItems> shoppingCart = new HashSet<>();



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_interest",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id")
    )
    private Set<Interest> interests = new HashSet<>();


    public void addOrder(Order order){
        order.setCustomer(this);
        orders.add(order);
    }
    public void addInterest(Interest interest) {
        interests.add(interest);
    }


}
