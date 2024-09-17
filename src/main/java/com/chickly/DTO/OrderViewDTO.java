package com.chickly.DTO;

import com.chickly.DataAccessLayer.Entities.Customer;
import com.chickly.Enums.Status;
import jakarta.persistence.*;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

public class OrderViewDTO {
    private Integer id;
    private Status status ;
    private String createdAt;
    private String  destination;

    public OrderViewDTO(Integer id, Status status, String createdAt, String destination) {
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
        this.destination = destination;
    }

    public OrderViewDTO() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
