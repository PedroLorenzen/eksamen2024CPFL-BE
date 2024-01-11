package com.example.eksamen2024cpflbe.models;

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
    int phoneNumber;
    String username;
    String password;
    LocalDateTime created;
    LocalDateTime updated;

    @OneToMany(mappedBy = "guest")
    private List<Reservation> reservations;

}
