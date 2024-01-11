package com.example.eksamen2024cpflbe.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Guest
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String firstName;
    String lastName;
    String email;
    int phone;
    LocalDateTime created;
    LocalDateTime updated;

    @OneToMany(mappedBy = "guest")
    @JsonManagedReference("guest-reservation")
    private List<Reservation> reservations;

}
