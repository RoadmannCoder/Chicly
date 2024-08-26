package com.chickly.DataAccessLayer.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "order")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Order{
    @Id
    private Integer id;
    @NonNull
    private Status status = Status.PENDING;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt;

    public enum Status {
        PENDING,
        WAITING,
        COMPLETED
    }
}
